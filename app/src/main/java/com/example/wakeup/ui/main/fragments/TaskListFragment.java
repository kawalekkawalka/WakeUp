package com.example.wakeup.ui.main.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.database.viewmodels.TaskViewModel;
import com.example.wakeup.ui.main.models.Task;
import com.example.wakeup.ui.main.models.TaskFinished;
import com.example.wakeup.ui.main.models.TaskInProgress;
import com.example.wakeup.ui.main.models.TaskOpen;
import com.example.wakeup.ui.main.utils.command.AddCommand;
import com.example.wakeup.ui.main.utils.command.CommandHistory;
import com.example.wakeup.ui.main.utils.command.EditCommand;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TaskListFragment extends Fragment {

    private Task newTask;
    private TaskAdapter adapter;
    private TextView dateTextView;
    private ImageView prevDateArrow;
    private ImageView nextDateArrow;
    private RecyclerView recyclerView;
    private TaskViewModel taskViewModel;
    private LocalDate currDate;
    private FloatingActionButton fabAdd;
    private FloatingActionButton fabUndo;
    private FloatingActionButton fabMenu;
    private Boolean isFabMenuVisible = false;
    private final Calendar calendar = Calendar.getInstance();
    private static final String KEY_CURRENT_DATE = "currentDate";
    private final CommandHistory commandHistory = CommandHistory.getInstance();

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int taskId = item.getIntent().getIntExtra("taskId", -1);
        Task task = adapter.getTask(taskId);
        if (item.getTitle() == "In progress") {
            task.setState(new TaskInProgress(task));
            taskViewModel.update(task);
        } else if (item.getTitle() == "Done") {
            task.setState(new TaskFinished(task));
            taskViewModel.update(task);
        } else if (item.getTitle() == "Delete") {
            taskViewModel.delete(task);
        }
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateList();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CURRENT_DATE,currDate.toString());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_task_list,container, false);
        currDate = LocalDate.now();
        if(savedInstanceState != null){
            currDate = LocalDate.parse(savedInstanceState.getString(KEY_CURRENT_DATE));
        }
        dateTextView = view.findViewById(R.id.date_text_view);
        prevDateArrow = view.findViewById(R.id.previous_day_arrow);
        nextDateArrow = view.findViewById(R.id.next_day_arrow);
        dateTextView.setText(currDate.toString());
        recyclerView = view.findViewById(R.id.task_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);

        fabAdd = view.findViewById(R.id.fab);
        fabUndo = view.findViewById(R.id.fab_undo);
        fabMenu = view.findViewById(R.id.menu_fab);
        fabUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!commandHistory.isEmpty()){
                    commandHistory.getTop().undo(taskViewModel);
                    commandHistory.pop();
                }
            }
        });

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFabMenuVisible) {
                    fabAdd.hide();
                    fabUndo.hide();
                    isFabMenuVisible = false;
                } else {
                    fabAdd.show();
                    fabUndo.show();
                    isFabMenuVisible = true;
                }
            }
        });
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTask = new Task();
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_add_task);
                final EditText title = dialog.findViewById(R.id.new_task_title);
                final EditText details = dialog.findViewById(R.id.new_task_details);
                final TextView dueDateText = dialog.findViewById(R.id.due_date_text);
                final CheckBox hasReminder = dialog.findViewById(R.id.has_reminder);
                final TextView dueHourText = dialog.findViewById(R.id.due_hour_text);
                Button addTaskBtn = dialog.findViewById(R.id.btn_add_task);

                DatePickerDialog.OnDateSetListener date = (view1,year, month, day) -> {
                    calendar.set(Calendar.YEAR,year);
                    calendar.set(Calendar.MONTH,month);
                    calendar.set(Calendar.DAY_OF_MONTH,day);
                    dueDateText.setText(LocalDate.of(year,month+1,day).toString());
                    newTask.setDueDate(LocalDate.of(year,month+1,day));
                };

                dueHourText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                                dueHourText.setText(String.format("%02d:%02d", hourOfDay, minutes));
                                newTask.setDueTime(LocalTime.parse(String.format("%02d:%02d", hourOfDay, minutes)));
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true)
                                .show();
                    }
                });

                dueDateText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new DatePickerDialog(getContext(), date, calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                                .show();
                    }
                });

                addTaskBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newTask.setTitle(title.getText().toString());
                        newTask.setDetails(details.getText().toString());
                        newTask.setHasReminder(hasReminder.isChecked());
                        newTask.setState(new TaskOpen(newTask));
                        List<Task> tasks = adapter.getTasks();
                        newTask.setId(tasks.get(adapter.getItemCount()-1).getId()+1);
                        commandHistory.add(new AddCommand(newTask));
                        taskViewModel.insert(newTask);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        updateList();

        prevDateArrow.setOnClickListener(v -> {
            currDate = currDate.minusDays(1);
            dateTextView.setText(currDate.toString());
            updateList();
        });

        nextDateArrow.setOnClickListener(v -> {
            currDate = currDate.plusDays(1);
            dateTextView.setText(currDate.toString());
            updateList();
        });



        return view;
    }

    private void updateList() {
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getTaskForDate(currDate).observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.setTasks(tasks);
            }
        });
    }

    private class TaskHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private TextView titleTextView;
        private TextView detailsTextView;
        private TextView timeTextView;
        private ImageView reminderIcon;
        private Task task;
        private FloatingActionButton fab;

        public TaskHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_task, parent, false));
            reminderIcon = itemView.findViewById(R.id.task_reminder_icon);
            timeTextView = itemView.findViewById(R.id.task_item_time);
            titleTextView = itemView.findViewById(R.id.task_item_title);
            detailsTextView = itemView.findViewById(R.id.task_item_details);
            cardView = itemView.findViewById(R.id.task_cardview);
        }

        public void bind(Task task) {
            this.task = task;
            titleTextView.setText(task.getTitle());
            detailsTextView.setText(task.getDetails());
            timeTextView.setText(task.getDueTime().toString());
            if(task.getHasReminder()){
                reminderIcon.setVisibility(View.VISIBLE);
            }else{
                reminderIcon.setVisibility(View.INVISIBLE);
            }
            if(task.getState() instanceof TaskInProgress){
                cardView.setCardBackgroundColor(Color.parseColor("#F3F781"));
            }else if(task.getState() instanceof  TaskFinished){
                cardView.setCardBackgroundColor(Color.parseColor("#58FA82"));
            }
        }


    }


    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> tasks = new ArrayList<>();
        private final CommandHistory commandHistory = CommandHistory.getInstance();

        public TaskAdapter(){
            this.tasks = new ArrayList<>();
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TaskHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position){
            Task task = tasks.get(position);
            holder.bind(task);

            holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                    menu.setHeaderTitle("Choose action");
                    menu.add(0, 1, 0, "In progress");
                    menu.add(0,2,0,"Done");
                    menu.add(0, 3, 0, "Delete");
                    MenuItem inProgress = menu.findItem(1);
                    MenuItem done = menu.findItem(2);
                    MenuItem delete = menu.findItem(3);
                    Intent intent = new Intent();
                    intent.putExtra("taskId",position);
                    inProgress.setIntent(intent);
                    done.setIntent(intent);
                    delete.setIntent(intent);
                }
            });



            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commandHistory.add(new EditCommand(new Task(task)));
                    task.getState().edit(taskViewModel,calendar,getActivity(),task);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    v.showContextMenu();
                    return true;
                }
            });
        }

        void setTasks(List<Task> tasks) {
            if (tasks == null || tasks.isEmpty()) {
                this.tasks = new ArrayList<>();
            } else {
                this.tasks = tasks;
            }
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount(){
            return tasks.size();
        }

        public Task getTask(int taskId) {
            return tasks.get(taskId);
        }

        public List<Task> getTasks(){
            return tasks;
        }
    }
}

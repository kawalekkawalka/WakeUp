package com.example.wakeup.ui.main.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wakeup.MainActivity;
import com.example.wakeup.R;
import com.example.wakeup.ui.main.activities.TaskListActivity;
import com.example.wakeup.ui.main.database.viewmodels.TaskViewModel;
import com.example.wakeup.ui.main.models.Task;
import com.example.wakeup.ui.main.models.TaskState;
import com.google.android.material.datepicker.DateSelector;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    private FloatingActionButton fab;
    private final Calendar calendar = Calendar.getInstance();

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int taskId = item.getIntent().getIntExtra("taskId", -1);
        Task task = adapter.getTask(taskId);
        if (item.getTitle() == "In progress") {
            System.out.println("Clicked in progress");
        } else if (item.getTitle() == "Done") {
            System.out.println("Clicked done");
        } else if (item.getTitle() == "Delete") {
            taskViewModel.delete(task);
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_task_list,container, false);

        currDate = LocalDate.now();

        dateTextView = view.findViewById(R.id.date_text_view);
        prevDateArrow = view.findViewById(R.id.previous_day_arrow);
        nextDateArrow = view.findViewById(R.id.next_day_arrow);
        dateTextView.setText(currDate.toString());
        recyclerView = view.findViewById(R.id.task_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
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
                        taskViewModel.insert(newTask);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        prevDateArrow.setOnClickListener(v -> {
            currDate = currDate.minusDays(1);
            dateTextView.setText(currDate.toString());
            taskViewModel.getTaskForDate(currDate).observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
                @Override
                public void onChanged(List<Task> tasks) {
                    adapter.setTasks(tasks);
                }
            });
        });

        nextDateArrow.setOnClickListener(v -> {
            currDate = currDate.plusDays(1);
            dateTextView.setText(currDate.toString());
            taskViewModel.getTaskForDate(currDate).observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
                @Override
                public void onChanged(List<Task> tasks) {
                    adapter.setTasks(tasks);
                }
            });
        });

        taskViewModel.getTaskForDate(currDate).observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.setTasks(tasks);
            }
        });

        return view;
    }

    private class TaskHolder extends RecyclerView.ViewHolder{

        private TextView titleTextView;
        private TextView detailsTextView;
        private TextView timeTextView;
        private ImageView reminderIcon;
        private Task task;
        private FloatingActionButton fab;

        public TextView getTitleTextView() {
            return titleTextView;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public TaskHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_task, parent, false));
            reminderIcon = itemView.findViewById(R.id.task_reminder_icon);
            timeTextView = itemView.findViewById(R.id.task_item_time);
            titleTextView = itemView.findViewById(R.id.task_item_title);
            detailsTextView = itemView.findViewById(R.id.task_item_details);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
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
        }


    }


    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> tasks = new ArrayList<>();

        public TaskAdapter(){
            this.tasks = new ArrayList<>();
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TaskHolder(layoutInflater, parent);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
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
                    openEditDialog(task);
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

        @RequiresApi(api = Build.VERSION_CODES.O)
        private void openEditDialog(Task task){
            Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.dialog_add_task);
            final EditText title = dialog.findViewById(R.id.new_task_title);
            final EditText details = dialog.findViewById(R.id.new_task_details);
            final TextView dueDateText = dialog.findViewById(R.id.due_date_text);
            final CheckBox hasReminder = dialog.findViewById(R.id.has_reminder);
            final TextView dueHourText = dialog.findViewById(R.id.due_hour_text);
            final TextView dialogTitle = dialog.findViewById(R.id.dialog_title);
            dialogTitle.setText("Edit task");
            Button addTaskBtn = dialog.findViewById(R.id.btn_add_task);
            addTaskBtn.setText("Edit");
            title.setText(task.getTitle());
            details.setText(task.getDetails());
            dueDateText.setText(task.getDueDate().toString());
            hasReminder.setChecked(task.getHasReminder());
            dueHourText.setText(task.getDueTime().toString());
            DatePickerDialog.OnDateSetListener date = (view1,year, month, day) -> {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,day);
                dueDateText.setText(LocalDate.of(year,month+1,day).toString());
                task.setDueDate(LocalDate.of(year,month+1,day));
            };
            dueHourText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                            dueHourText.setText(String.format("%02d:%02d", hourOfDay, minutes));
                            task.setDueTime(LocalTime.parse(String.format("%02d:%02d", hourOfDay, minutes)));
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
                    task.setTitle(title.getText().toString());
                    task.setDetails(details.getText().toString());
                    task.setHasReminder(hasReminder.isChecked());
                    taskViewModel.update(task);
                    dialog.dismiss();
                }
            });

            dialog.show();
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
    }
}

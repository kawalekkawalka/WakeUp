package com.example.wakeup.ui.main.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
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
    public static final String KEY_EXTRA_TASK_ID = "KEY_EXTRA_TASK_ID";

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
                final TextView txtDueDate = dialog.findViewById(R.id.due_date_text);
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
                                dueHourText.setText(hourOfDay + ":" + minutes);
                                newTask.setDueTime(LocalTime.parse(hourOfDay + ":" + minutes));
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
//                        System.out.println(newTask.toString());
                        taskViewModel.insert(newTask);
                    }
                });

                dialog.show();
            }
        });

        prevDateArrow.setOnClickListener(v -> {
            currDate = currDate.minusDays(1);
            dateTextView.setText(currDate.toString());
        });

        nextDateArrow.setOnClickListener(v -> {
            currDate = currDate.plusDays(1);
            dateTextView.setText(currDate.toString());
        });
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
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
        private Task task;
        private FloatingActionButton fab;

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TaskHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_task, parent, false));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return true;
                }
            });

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
        }



    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder>{
        private List<Task> tasks = new ArrayList<>();

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TaskHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position){
            Task task = tasks.get(position);
            TextView titleTextView = holder.getTitleTextView();
            holder.bind(task);
        }

        void setTasks(List<Task> tasks) {
            this.tasks = tasks;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount(){
            return tasks.size();
        }

    }
}

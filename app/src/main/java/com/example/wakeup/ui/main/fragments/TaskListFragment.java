package com.example.wakeup.ui.main.fragments;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wakeup.MainActivity;
import com.example.wakeup.R;
import com.example.wakeup.ui.main.models.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TaskListFragment extends Fragment {
    private TextView dateTextView;
    private RecyclerView recyclerView;
    public static final String KEY_EXTRA_TASK_ID = "KEY_EXTRA_TASK_ID";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_task_list,container, false);
        dateTextView = view.findViewById(R.id.date_text_view);
        recyclerView = view.findViewById(R.id.task_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView titleTextView;
        private TextView detailsTextView;
        private Task task;
        private FloatingActionButton fab;

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TaskHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_task, parent, false));
            itemView.setOnClickListener(this);

            fab = itemView.findViewById(R.id.fab);
            titleTextView = itemView.findViewById(R.id.task_item_title);
            detailsTextView = itemView.findViewById(R.id.task_item_details);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("FAB DZIALA");
                }
            });
        }

        public void bind(Task task) {
            this.task = task;
            titleTextView.setText(task.getTitle());
            detailsTextView.setText(task.getDetails());
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra(KEY_EXTRA_TASK_ID, task.getId());
            startActivity(intent);
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder>{
        private List<Task> tasks;

        public TaskAdapter(List<Task> tasks){
            this.tasks = tasks;
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
            TextView titleTextView = holder.getTitleTextView();
            holder.bind(task);
        }

        @Override
        public int getItemCount(){
            return tasks.size();
        }

    }
}

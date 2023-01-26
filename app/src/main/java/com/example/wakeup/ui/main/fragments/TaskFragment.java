//package com.example.wakeup.ui.main.fragments;
//
//import android.app.DatePickerDialog;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.example.wakeup.R;
//import com.example.wakeup.ui.main.models.Task;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Locale;
//import java.util.UUID;
//
//public class TaskFragment  extends Fragment {
//    private Task task;
//    private TextView detailsLabel;
//    private EditText nameField;
//    private EditText dateField;
//    private CheckBox doneCheckBox;
//    private final Calendar calendar = Calendar.getInstance();
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        this.task = new Task();
//    }
//
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
//        super.onCreateView(inflater,container,savedInstanceState);
//        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
//        nameField = view.findViewById(R.id.task_name);
//        dateField = view.findViewById(R.id.task_date);
//        doneCheckBox = view.findViewById(R.id.task_done);
//
//        DatePickerDialog.OnDateSetListener date = (view1, year, month, day) -> {
//            calendar.set(Calendar.YEAR,year);
//            calendar.set(Calendar.MONTH,month);
//            calendar.set(Calendar.DAY_OF_MONTH,day);
//            setupDateFieldValue(calendar.getTime());
//            task.setDate(calendar.getTime());
//        };
//        dateField.setOnClickListener(view1 ->
//                new DatePickerDialog(getContext(), date, calendar.get(Calendar.YEAR),
//                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
//                        .show());
//        setupDateFieldValue(task.getDate());
//
//        nameField.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                task.setName(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        dateField.setText(task.getDate().toString());
//
//        doneCheckBox.setChecked(task.isDone());
//        doneCheckBox.setOnCheckedChangeListener((buttonView, isChecked)-> task.setDone(isChecked));
//
//        nameLabel = view.findViewById(R.id.task_name);
//        nameLabel.setText(task.getName());
//
//        return view;
//    }
//    private void setupDateFieldValue(Date date){
//        Locale locale = new Locale("pl","PL");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", locale);
//        dateField.setText(dateFormat.format(date));
//    }
//    public static TaskFragment newInstance(UUID taskId){
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(ARG_TASK_ID, taskId);
//        TaskFragment taskFragment = new TaskFragment();
//        taskFragment.setArguments(bundle);
//        return taskFragment;
//    }
//
//
//
//}

package com.example.wakeup.ui.main.utils;

import androidx.room.TypeConverter;

import com.example.wakeup.ui.main.models.TaskFinished;
import com.example.wakeup.ui.main.models.TaskInProgress;
import com.example.wakeup.ui.main.models.TaskOpen;
import com.example.wakeup.ui.main.models.TaskFinished;
import com.example.wakeup.ui.main.models.TaskState;

public class StateConverter {
    @TypeConverter
    public static TaskState toState(int stateType) {
        switch (stateType) {
            case 1:
                return new TaskOpen();
            case 2:
                return new TaskInProgress();
            case 3:
                return new TaskFinished();
            default:
                throw new IllegalArgumentException("Invalid state type");
        }
    }

    @TypeConverter
    public static int toInteger(TaskState state) {
        if (state instanceof TaskOpen) {
            return 1;
        } else if (state instanceof TaskInProgress) {
            return 2;
        } else if(state instanceof TaskFinished){
            return 3;
        }else {
            throw new IllegalArgumentException("Invalid state object");
        }
    }
}
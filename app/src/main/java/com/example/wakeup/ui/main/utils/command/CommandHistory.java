package com.example.wakeup.ui.main.utils.command;

import java.util.Stack;

public class CommandHistory {
    private static CommandHistory instance;

    private Stack<Command> history = new Stack<>();

    public static CommandHistory getInstance() {
        if (instance == null) {
            instance = new CommandHistory();
        }
        return instance;
    }

    public void add(Command command){
        history.push(command);
    }

    public Command getTop(){
        return history.peek();
    }

    public void pop() {
        history.pop();
    }

    public boolean isEmpty(){
        return history.isEmpty();
    }
}

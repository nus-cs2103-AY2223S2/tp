package vimification.model.task;

import java.time.LocalDateTime;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public Todo(String description) {
        this(description, false);
    }

    @Override
    public Todo clone() {
        return new Todo(getDescription(), isDone());
    }

    @Override
    public boolean isDeadline() {
        return false;
    }

    public String toString() {
        return "Todo " + super.toString();
    }
}

package vimification.model.task;

import java.time.LocalDateTime;

public class Todo extends Task {

    public Todo(String description, Status status, Priority priority) {
        super(description, status, priority);
    }

    public Todo(String description) {
        super(description);
    }


    @Override
    public Todo clone() {
        return new Todo(getDescription(), getStatus(), getPriority());
    }

    @Override
    public boolean isDeadline() {
        return false;
    }

    public String toString() {
        return "Todo " + super.toString();
    }
}

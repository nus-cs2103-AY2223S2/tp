package vimification.model.oldcode;

import vimification.model.task.Priority;
import vimification.model.task.Status;
import vimification.model.task.Task;

import java.time.LocalDateTime;

public class Todo {
    /**
    public Todo(String description, Status status, Priority priority) {
        super(description, status, priority);
    }


    public Todo(String description) {
        super(description);
    }
    */
    public LocalDateTime getDeadline() {
        return null;
    }
    public void setDeadline(LocalDateTime deadline) {};

    /**
    @Override
    public Todo clone() {
        return new Todo(getDescription(), getStatus(), getPriority());
    }

    @Override
    public boolean isDeadline() {
        return false;
    }
    */

    public String toString() {
        return "Todo " + super.toString();
    }
}

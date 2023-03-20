package vimification.model.task;

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

    public String toString() {
        return "Todo " + super.toString();
    }
}

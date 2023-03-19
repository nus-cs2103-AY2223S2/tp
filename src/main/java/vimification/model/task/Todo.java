package vimification.model.task;

public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }

    public Todo(String description) {
        this(description, false);
    }

    @Override
    public Todo clone() {
        return new Todo(getDescription(), isDone());
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getType())
                .append(" ")
                .append(super.toString());
        return builder.toString();
    }
}

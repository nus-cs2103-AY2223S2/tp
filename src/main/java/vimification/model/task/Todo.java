package vimification.model.task;

public class Todo extends Task{

    public Todo(Description description, Status status) {
        super(description, status, new Type("Todo"));
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getType())
                .append(" ")
                .append(super.toString());
        return builder.toString();
    }
}

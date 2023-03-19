package vimification.model.task;

import vimification.model.task.components.Description;

public class Todo extends Task {
    private static int numOfComponents = 1;

    public Todo(Description description, Status status) {
        super(description, status, TaskType.str2type("Todo"));
    }

    public static Todo createTask(Description description) {
        return new Todo(description, new Status(false));
    }

    public static Todo createTask(String... taskComponents) {
        if (taskComponents.length != numOfComponents) {
            throw new IllegalArgumentException("Invalid number of task components");
        }
        return createTask(new Description(taskComponents[0]));
    }


    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getType())
                .append(" ")
                .append(super.toString());
        return builder.toString();
    }
}

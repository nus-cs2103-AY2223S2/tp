package vimification.model.task;

import vimification.model.task.components.DateTime;
import vimification.model.task.components.Description;

public class Deadline extends Task {
    private static int numOfComponents = 2;
    private final DateTime deadline;

    public Deadline(Description description, Status status, DateTime deadline) {
        super(description, status, TaskType.str2type("Deadline"));
        this.deadline = deadline;
    }

    public static Deadline createTask(Description description, DateTime deadline) {
        return new Deadline(description, new Status(false), deadline);
    }

    public static Deadline createTask(String... taskComponents) {
        if (taskComponents.length != numOfComponents) {
            throw new IllegalArgumentException("Invalid number of task components");
        }
        return createTask(new Description(taskComponents[0]), new DateTime(taskComponents[1]));
    }

    public DateTime getDeadline() {
        return deadline;
    }


    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getType())
                .append(" ")
                .append(super.toString())
                .append("; by: ")
                .append(getDeadline());
        return builder.toString();
    }
}

package vimification.model.task;

import vimification.model.task.components.DateTime;
import vimification.model.task.components.Description;

public class Event extends Task {
    private static int numOfComponents = 3;
    private final DateTime startDate;
    private final DateTime endDate;

    public Event(Description description, Status status, DateTime startDate, DateTime endDate) {
        super(description, status, TaskType.str2type("Event"));
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Event createTask(Description description, DateTime startDate, DateTime endDate) {
        return new Event(description, new Status(false), startDate, endDate);
    }

    public static Event createTask(String... taskComponents) {
        if (taskComponents.length != numOfComponents) {
            throw new IllegalArgumentException("Invalid number of task components");
        }
        return createTask(new Description(taskComponents[0]), new DateTime(taskComponents[1]),
                new DateTime(taskComponents[2]));
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getType())
                .append(" ")
                .append(super.toString())
                .append("; from: ")
                .append(getStartDate())
                .append(" to: ")
                .append(getEndDate());
        return builder.toString();
    }
}

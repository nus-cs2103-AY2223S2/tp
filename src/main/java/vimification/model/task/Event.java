package vimification.model.task;

public class Event extends Task {

    private final DateTime startDate;
    private final DateTime endDate;


    public Event(Description description, Status status, DateTime startDate, DateTime endDate) {
        super(description, status, new Type("Event"));
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DateTime getStartDate() { return startDate; }

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

package vimification.model.task;

public class Event extends Task {

    private final Date startDate;
    private final Date endDate;


    public Event(Description description, Status status, Date startDate, Date endDate) {
        super(description, status, new Type("Event"));
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() { return startDate; }

    public Date getEndDate() {
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

package vimification.model.task;

public class Deadline extends Task {

    private final DateTime deadline;

    public Deadline(Description description, Status status, DateTime deadline) {
        super(description, status, new Type("Deadline"));
        this.deadline = deadline;
    }

    public DateTime getDeadline() { return deadline; }


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

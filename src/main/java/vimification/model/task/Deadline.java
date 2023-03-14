package vimification.model.task;

public class Deadline extends Task {

    private final Date deadline;

    public Deadline(Description description, Status status, Date deadline) {
        super(description, status, new Type("Deadline"));
        this.deadline = deadline;
    }

    public Date getDeadline() { return deadline; }


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

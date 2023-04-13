package seedu.address.model.task;

/**
 * This class is for tasks that have a deadline.
 */
public class DeadlineTask extends Task {

    private final Date deadlineDate;

    /**
     * Constructor for a DeadlineTask that takes in a String description as well as String date.
     *
     * @param description The description of the DeadlineTask.
     * @param date The date of the deadline.
     */
    public DeadlineTask(TaskDescription description, Date date) {
        super(description, String.format("Due by: %s", date.toString()), "D");
        this.deadlineDate = date;
    }


    /**
     * Supplies deadline date of the current task when requested.
     *
     * @return Date deadline date of the task
     */
    public Date getDeadlineDate() {
        return this.deadlineDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (Before: " + deadlineDate + ")";
    }

    /**
     * Compares this object to the specified object.
     *
     * @param obj the object to compare with.
     * @return true if the objects are the same; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeadlineTask)) {
            return false;
        }
        DeadlineTask checkedObj = (DeadlineTask) obj;
        boolean isSameDescription = this.getDescription().equals(checkedObj.getDescription());
        boolean isSameDate = this.getDate().equals(checkedObj.getDate());
        boolean isSame = isSameDescription && isSameDate;

        if (isSame) {
            return true;
        }

        return false;
    }
}

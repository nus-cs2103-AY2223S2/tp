package seedu.address.model.task;

/**
 * This class is for tasks that are events.
 */
public class EventTask extends Task {

    private final Date startDate;
    private final Date endDate;

    /**
     * Constructor for a EventTask that takes in a String description as well as String dates.
     *
     *
     * @param description The description of the EventTask.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     */
    public EventTask(TaskDescription description, Date startDate, Date endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Supplies start date of the current task when requested.
     *
     * @return Date deadline date of the task
     */
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * Supplies end date of the current task when requested.
     *
     * @return Date deadline date of the task
     */
    public Date getEndDate() {
        return this.endDate;
    }



    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + startDate + " To: " + endDate + ")";
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
        if (!(obj instanceof EventTask)) {
            return false;
        }
        EventTask checkedObj = (EventTask) obj;
        boolean isSameDescription = this.getDescription().equals(checkedObj.getDescription());
        boolean isSameStartDate = this.getStartDate().equals(checkedObj.getStartDate());
        boolean isSameEndDate = this.getEndDate().equals(checkedObj.getEndDate());
        boolean isSameDuration = isSameStartDate && isSameEndDate;
        boolean isSame = isSameDescription && isSameDuration;

        if (isSame) {
            return true;
        }

        return false;
    }
}

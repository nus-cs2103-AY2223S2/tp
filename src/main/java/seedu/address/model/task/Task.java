package seedu.address.model.task;


/**
 * This class is the parent class of tasks that make up the tasklist.
 */

public class Task {

    protected boolean isDone;
    private final TaskDescription description;

    /**
     * The constructor of the Task that takes in description of the task.
     */
    public Task(TaskDescription description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Supplies description of the current task when requested.
     *
     * @return TaskDescription description of the task
     */
    public TaskDescription getDescription() {
        return this.description;
    }

    /**
     * Supplies description of the completeness of current task when requested.
     *
     * @return String description of the completeness of current task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Changes status of current task as done by assigning isDone as true.
     */
    public String mark() {
        this.isDone = true;
        return "This task has been marked as completed:\n" + this + "\n";
    }

    /**
     * Changes status of current task as not done by assigning isDone as false.
     */
    public String unmark() {
        this.isDone = false;
        return "This task has been marked as uncompleted:\n" + this + "\n";
    }

    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        String str = "";
        str = String.format("[" + statusIcon + "] " + this.description);
        return str;
    }

    /**
     * Returns true if both tasks have the same description.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDescription().equals(getDescription());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        Task checkedObj = (Task) obj;
        boolean isSameDescription = this.description.equals(checkedObj.description);

        if (isSameDescription) {
            return true;
        }

        return false;
    }


}

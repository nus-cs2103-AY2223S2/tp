package seedu.address.model.task;

import java.util.Objects;

import seedu.address.model.tank.Tank;
import seedu.address.model.task.exceptions.TaskHasNoPriorityException;
import seedu.address.model.task.exceptions.TaskHasNoTankException;

/**
 *  Represents a Task of the user
 */
public class Task {
    protected boolean isReminder;

    private final Description description;
    private final boolean isTankRelatedTask;
    private Tank tank;
    private Priority priority;
    private boolean hasPriority;

    /**
     * Constructor for a Task
     * @param description Description of task
     * @param tank Tank (if applicable) tied to this task
     * @param priority Priority (if applicable) tied to this task
     */
    public Task(Description description, Tank tank, Priority priority) {
        this.description = description;
        this.tank = tank;
        this.isTankRelatedTask = this.tank != null;
        this.priority = priority;
        this.hasPriority = this.priority != null;
        this.isReminder = false;
    }

    public Description getDescription() {
        return this.description;
    }

    public Priority getPriority() {
        if (!hasPriority) {
            throw new TaskHasNoPriorityException(this);
        }
        return this.priority;
    }

    public boolean hasPriority() {
        return this.hasPriority;
    }

    public boolean getIsReminder() {
        return this.isReminder;
    }

    public Tank getTank() {
        if (!isTankRelatedTask) {
            throw new TaskHasNoTankException(this);
        }
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public boolean isTankRelatedTask() {
        return isTankRelatedTask;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        boolean bothHasTank = isTankRelatedTask() && otherTask.isTankRelatedTask();
        boolean bothNoTank = !isTankRelatedTask() && !otherTask.isTankRelatedTask();
        boolean hasSameTank = (bothHasTank && (tank.equals(otherTask.getTank()))) || bothNoTank;
        return otherTask.getDescription().equals(description)
                && hasSameTank;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription());

        return builder.toString();
    }
}

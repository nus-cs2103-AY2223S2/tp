package seedu.calidr.model.task;

import seedu.calidr.model.task.params.Priority;
import seedu.calidr.model.task.params.Title;

/**
 * Represents a task with the title of the task and
 * the status of the task - whether it is done.
 */
public abstract class Task {

    private Title title;
    private boolean isDone;
    private Priority priority;

    /**
     * Creates a Task object with the given title and MEDIUM priority.
     *
     * @param title The title of the Task.
     */
    public Task(Title title) {
        assert title != null;

        this.title = title;
        this.isDone = false;
        this.priority = Priority.MEDIUM;
    }

    /**
     * Creates a Task object with the given title and priority.
     *
     * @param title The title of the Task.
     * @param priority The priority associated with the Task.
     */
    public Task(Title title, Priority priority) {
        assert title != null;
        assert priority != null;

        this.title = title;
        this.isDone = false;
        this.priority = priority;
    }

    public Title getTitle() {
        return this.title;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public void setPriority(Priority p) {
        this.priority = p;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns true if both Tasks have the same details.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return equals(otherTask);
    }

    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return "{" + this.priority.toString().toLowerCase() + "}[" + mark + "] " + this.title;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && title.equals(((Task) other).title));
    }

}

package seedu.task.model.task;

import static seedu.task.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.task.commons.core.index.Index;
import seedu.task.model.tag.Tag;

/**
 * Represents a Task in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Task implements Comparable<Task> {

    // Identity fields
    protected Name name;
    protected Description description;
    protected Duration alertWindow;

    // Data fields
    protected final Set<Tag> tags = new HashSet<>();
    protected Effort effort;
    protected UniqueSubtaskList subtasks = new UniqueSubtaskList();

    /**
     * Name, Description and Tag must be present and not null.
     * Effort may or may not be null. If null, default value will be used.
     * alertWindow is generated with default values.
     * plannedDate will always be initialised with a placeholder value, indicating that value is not ready.
     */
    public Task(Name name, Description description, Set<Tag> tags, Effort effort) {
        requireAllNonNull(name, description, tags);
        this.name = name;
        this.description = description;
        this.tags.addAll(tags);
        this.effort = effort;
        this.alertWindow = Duration.ofHours(24);
    }

    /**
     * Overload the constructor with an additional field of subtasks
     */
    public Task(Name name, Description description, Set<Tag> tags, Effort effort, List<Subtask> subtasks) {
        requireAllNonNull(name, description, tags);
        this.name = name;
        this.description = description;
        this.tags.addAll(tags);
        this.effort = effort;
        this.alertWindow = Duration.ofHours(24);
        this.subtasks.setSubtasks(subtasks);

    }

    public Name getName() {
        return name;
    }

    public boolean hasDescription() {
        return description.getHasDescription();
    }

    public Description getDescription() {
        return description;
    }

    public Duration getAlertWindow() {
        return alertWindow;
    }

    public void setAlertWindow(Duration newAlertWindow) {
        this.alertWindow = newAlertWindow;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Adds a non-null subtask to the list
     * @param subtask A subtask to be added
     */
    public void addSubtask(Subtask subtask) {
        requireAllNonNull(subtask);
        subtasks.addSubtask(subtask);
    }

    /**
     * Removes a subtask from the list
     * @param index The position where the subtask is
     */
    public void removeSubtask(Index index) {
        requireAllNonNull(index);
        Subtask subtask = this.subtasks.getSubtask(index.getZeroBased());
        subtasks.remove(subtask);
    }

    /**
     * Gets the subtasks as an {@code unmodifiableObservableList}
     * @return An UnmodifiableObservableList
     */
    public ObservableList<Subtask> getSubtasks() {
        return this.subtasks.asUnmodifiableObservableList();
    }

    /**
     * Gets the subtasks as the {@code uniqueSubtaskList}
     * @return
     */
    public UniqueSubtaskList getSubtasksAsOriginal() {
        return this.subtasks;
    }

    /**
     * Change the subtask list to String format to be displayed later
     * @return A string description of subtasks
     */
    public String formatSubtasks() {
        if (hasSubtasks()) {
            StringBuilder output = new StringBuilder();
            int index = 1;
            output.append("\n");
            for (Subtask subtask: subtasks) {
                output.append(index);
                output.append(". ");
                output.append(subtask);
                output.append("\n");
                index += 1;
            }
            return output.toString();
        }
        return "There is no subsection";
    }

    /**
     * Checks if there is at least one task in the list of subtasks
     * @return True or False
     */
    public boolean hasSubtasks() {
        if (this.subtasks != null) {
            if (subtasks.size() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the size of the subtask list in this task
     * @return An integer representing the subtask size
     */
    public int getSubtaskSize() {
        return this.subtasks.size();
    }

    public Effort getEffort() {
        return this.effort;
    }

    public long getEffortValue() {
        return this.effort.getEffort();
    }

    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && this.name.hasSameName(otherTask.name);
    }

    public abstract boolean isSimpleTask();

    public abstract boolean isDeadline();

    public abstract boolean isEvent();


    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getName().equals(getName())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getTags().equals(getTags())
                && otherTask.getEffort().equals(getEffort());
    }

    /**
     * Returns true if end time of task is before {@code alertTime}.
     */
    public abstract boolean isComingUp();

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, tags, effort);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Description: ")
                .append(getDescription());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    @Override
    public abstract int compareTo(Task task);
}

package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.Relationship;
import seedu.address.model.shared.Id;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task implements Relationship<Task> {

    // Identity fields
    private final Id id;

    // Data fields
    private final Title title;
    private final Content content;
    private final Status status;

    /**
     * Every field must be present and not null.
     */
    public Task(Title title, Content content, Status status) {
        requireAllNonNull(title, content, status);
        this.title = title;
        this.content = content;
        this.status = status;
        this.id = new Id();

    }

    /**
     * Every field must be present and not null.
     * ID must be specific when loading from local storage
     */
    public Task(Title title, Content content, Status status, Id id) {
        requireAllNonNull(title, content, status);
        this.title = title;
        this.content = content;
        this.status = status;
        this.id = id;

    }

    public Id getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public Content getContent() {
        return content;
    }

    public Status getStatus() {
        return status;
    }


    /**
     * Returns true if both tasks have the same title.
     * This defines a weaker notion of equality between two tasks.
     */
    @Override
    public boolean isSame(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
            && otherTask.getTitle().equals(getTitle());
    }

    @Override
    public boolean hasSameId(Task otherTask) {
        return otherTask.getId().equals(getId());
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
        return otherTask.getTitle().equals(getTitle())
            && otherTask.getContent().equals(getContent())
            && otherTask.getStatus().equals(getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, status);
    }

    @Override
    public String toString() {

        return getTitle()
            + "; Status: "
            + getStatus()
            + "; Content: "
            + getContent();
    }

}

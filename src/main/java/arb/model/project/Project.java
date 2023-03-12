package arb.model.project;

import static arb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a Project in the address book.
 * Guarantees: title and status is present and not null; field values are validated & immutable.
 */
public class Project {

    // Details fields. Deadline is optional.
    private final Title title;
    private final Optional<Deadline> deadline;
    private final Status status;

    /**
     * Constructs a {@code Project}.
     * Title must be present and not null.
     */
    public Project(Title title, Deadline deadline) {
        requireAllNonNull(title);
        this.title = title;
        this.deadline = Optional.ofNullable(deadline);
        status = new Status();
    }

    public Title getTitle() {
        return title;
    }

    /**
     * Returns true if this project has a deadline.
     */
    public boolean isDeadlinePresent() {
        return deadline.isPresent();
    }

    public Deadline getDeadline() {
        return deadline.orElse(null);
    }

    public Status getStatus() {
        return status;
    }

    public void markAsDone() {
        this.status.setTrue();
    }

    /**
     * Returns true if both projects have the same title.
     * This defines a weaker notion of equality between two projects.
     */
    public boolean isSameProject(Project otherProject) {
        if (otherProject == this) {
            return true;
        }

        return otherProject != null
                && otherProject.getTitle().equals(getTitle());
    }

    @Override
    public String toString() {
        return getTitle() + ", due by: " + getDeadline();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Project)) {
            return false;
        }

        Project otherProject = (Project) other;

        boolean isTitleEqual = otherProject.getTitle().equals(getTitle());
        boolean isStatusEqual = otherProject.getStatus().equals(getStatus());

        boolean isDeadlineEqual;
        if (!isDeadlinePresent()) {
            isDeadlineEqual = otherProject.getDeadline() == null;
        } else {
            isDeadlineEqual = getDeadline().equals(otherProject.getDeadline());
        }

        return isTitleEqual && isStatusEqual && isDeadlineEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, deadline, status);
    }
}

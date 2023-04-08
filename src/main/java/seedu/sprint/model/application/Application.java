package seedu.sprint.model.application;

import static seedu.sprint.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.sprint.model.tag.Tag;
import seedu.sprint.model.task.Task;


/**
 * Represents an Application in the internship book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Application {
    private final Role role;
    private final CompanyName companyName;
    private final CompanyEmail companyEmail;
    private final Status status;
    private final Task task;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Application(Role role, CompanyName companyName, CompanyEmail companyEmail,
                       Status status, Task task, Set<Tag> tags) {
        requireAllNonNull(role, companyName, companyEmail, status, tags);
        this.role = role;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.status = status;
        this.task = task;
        this.tags.addAll(tags);
    }

    public Role getRole() {
        return this.role;
    }

    public CompanyName getCompanyName() {
        return this.companyName;
    }

    public CompanyEmail getCompanyEmail() {
        return this.companyEmail;
    }

    public Status getStatus() {
        return this.status;
    }

    public Task getTask() {
        return this.task;
    }

    public boolean hasTask() {
        return getTask() != null;
    }

    public boolean hasOutdatedTask() {
        return !this.task.hasValidDeadline();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both applications have the same role, company name and company email fields.
     * Equality of status is not checked since both applications are considered to be equal regardless
     * of whether they have the same status or not.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Application)) {
            return false;
        }

        Application otherApplication = (Application) other;
        boolean hasTask = this.hasTask();
        boolean otherHasTask = otherApplication.hasTask();
        boolean tasksEqual = hasTask && otherHasTask && this.getTask().equals(otherApplication.getTask());

        return otherApplication.getRole().equals(this.getRole())
                && otherApplication.getCompanyName().equals(this.getCompanyName())
                && otherApplication.getCompanyEmail().equals(this.getCompanyEmail())
                && otherApplication.getTags().equals(this.getTags())
                && (tasksEqual || (!hasTask && !otherHasTask));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(role, companyName, companyEmail, status, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Role: ").append(getRole())
                .append("; Company: ")
                .append(getCompanyName())
                .append("; Company Email: ")
                .append(getCompanyEmail())
                .append("; Status: ")
                .append(getStatus());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}

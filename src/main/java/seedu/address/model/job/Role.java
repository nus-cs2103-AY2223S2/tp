package seedu.address.model.job;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Role in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Role {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final JobDescription jobDescription;
    private final Set<Tag> tags = new HashSet<>();
    private final Salary salary;
    private final Deadline deadline;

    /**
     * Every field must be present and not null.
     */
    public Role(Name name, Phone phone, Email email, Address address, JobDescription jd, Set<Tag> tags,
                Salary salary, Deadline deadline) {
        requireAllNonNull(name, phone, email, address, jd, tags, salary, deadline);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.jobDescription = jd;
        this.tags.addAll(tags);
        this.salary = salary;
        this.deadline = deadline;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public JobDescription getJobDescription() {
        return jobDescription;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Salary getSalary() {
        return salary;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    /**
     * Returns true if both roles have the same name.
     * This defines a weaker notion of equality between two roles.
     */
    public boolean isSameRole(Role otherRole) {
        if (otherRole == this) {
            return true;
        }

        return otherRole != null
                && otherRole.getName().equals(getName());
    }

    /**
     * Returns true if both roles have the same identity and data fields.
     * This defines a stronger notion of equality between two roles.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Role)) {
            return false;
        }

        Role otherRole = (Role) other;
        return otherRole.getName().equals(getName())
                && otherRole.getPhone().equals(getPhone())
                && otherRole.getEmail().equals(getEmail())
                && otherRole.getAddress().equals(getAddress())
                && otherRole.getJobDescription().equals(getJobDescription())
                && otherRole.getTags().equals(getTags())
                && otherRole.getSalary().equals(getSalary())
                && otherRole.getDeadline().equals(getDeadline());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, jobDescription, tags, salary, deadline);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Job Description: ")
                .append(getJobDescription());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        builder.append("; Salary: ").append(getSalary());
        builder.append("; Deadline: ").append(getDeadline());
        return builder.toString();
    }

}

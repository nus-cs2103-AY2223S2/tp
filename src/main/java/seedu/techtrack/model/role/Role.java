package seedu.techtrack.model.role;

import static seedu.techtrack.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.techtrack.model.util.tag.Tag;

/**
 * Represents a Role in the company book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Role {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Website website;

    // Data fields
    private final Company company;
    private final JobDescription jobDescription;
    private final Set<Tag> tags = new HashSet<>();
    private final Salary salary;
    private final Deadline deadline;
    private final Experience experience;

    /**
     * Every field must be present and not null.
     */
    public Role(Name name, Phone phone, Email email, Company company, JobDescription jd, Set<Tag> tags,
                Website website, Salary salary, Deadline deadline, Experience experience) {
        requireAllNonNull(name, phone, email, company, jd, tags, website, salary, deadline, experience);

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.company = company;
        this.jobDescription = jd;
        this.tags.addAll(tags);
        this.website = website;
        this.salary = salary;
        this.deadline = deadline;
        this.experience = experience;
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

    public Company getCompany() {
        return company;
    }

    public Website getWebsite() {
        return website;
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

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public Salary getSalary() {
        return salary;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Experience getExperience() {
        return experience;
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
                && otherRole.getName().equals(getName())
                && otherRole.getCompany().equals(getCompany());
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
                && otherRole.getCompany().equals(getCompany())
                && otherRole.getJobDescription().equals(getJobDescription())
                && otherRole.getTags().equals(getTags())
                && otherRole.getWebsite().equals(getWebsite())
                && otherRole.getSalary().equals(getSalary())
                && otherRole.getDeadline().equals(getDeadline())
                && otherRole.getExperience().equals(getExperience());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, company, jobDescription, tags, website, salary, deadline, experience);

    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\n")
                .append("Role: ").append(getName())
                .append("\n")
                .append("Company: ")
                .append(getCompany())
                .append("\n");

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("Tags: ");
            tags.forEach(builder::append);
        }
        builder.append("\n")
                .append("Salary: ").append(getSalary())
                .append("\n")
                .append("Deadline: ").append(getDeadline())
                .append("\n")
                .append("Experience: ").append(getExperience())
                .append("\n")
                .append("Phone: ").append(getPhone())
                .append("\n")
                .append("Email: ").append(getEmail())
                .append("\n")
                .append("Website: ").append(getWebsite())
                .append("\n")
                .append("Job Description: ").append(getJobDescription())
                .append("\n");

        return builder.toString();
    }
}

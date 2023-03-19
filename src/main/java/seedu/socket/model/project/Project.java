package seedu.socket.model.project;

import static seedu.socket.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.socket.model.person.Person;

/**
 * Represents a {@code Project} in SOCket.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {
    public static final String PROJ_NAME = "name";
    public static final String PROJ_REPO_HOST = "repohost";
    public static final String PROJ_REPO_NAME = "reponame";
    public static final String PROJ_DEADLINE = "deadline";
    private static final String[] categories = {PROJ_NAME, PROJ_REPO_HOST,
        PROJ_REPO_NAME, PROJ_DEADLINE};

    public static final List<String> CATEGORIES = Arrays.asList(categories);

    // Identity fields
    /** {@code ProjectName} associated with the {@code Project} instance. */
    private final ProjectName name;

    // Data fields
    /** {@code ProjectRepoHost} associated with the {@code Project} instance. */
    private final ProjectRepoHost repoHost;
    /** {@code ProjectRepoName} associated with the {@code Project} instance. */
    private final ProjectRepoName repoName;
    /** {@code ProjectDeadline} associated with the {@code Project} instance. */
    private final ProjectDeadline deadline;
    /** {@code Set<Person>} associated with the {@code Project} instance. */
    private final Set<Person> members = new HashSet<>();

    /** {@code ProjectName} associated with the {@code Person} instance. */
    public Project(ProjectName name, ProjectRepoHost repoHost, ProjectRepoName repoName,
                   ProjectDeadline deadline, Set<Person> members) {
        requireAllNonNull(name, repoHost, repoName, deadline, members);
        this.name = name;
        this.repoHost = repoHost;
        this.repoName = repoName;
        this.deadline = deadline;
        this.members.addAll(members);
    }

    public ProjectName getName() {
        return name;
    }

    public ProjectRepoHost getRepoHost() {
        return repoHost;
    }

    public ProjectRepoName getRepoName() {
        return repoName;
    }

    public ProjectDeadline getDeadline() {
        return deadline;
    }

    /**
     * Returns an immutable {@code Person} set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return An immutable {@code Person} set.
     */
    public Set<Person> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    /**
     * Returns true if both projects have the same name.
     * This defines a weaker notion of equality between two projects.
     *
     * @param otherProject {@code Project} to compare with.
     * @return {@code true} if both persons have the same name.
     */
    public boolean isSameProject(Project otherProject) {
        if (otherProject == this) {
            return true;
        }
        return otherProject != null
            && otherProject.getName().equals(getName());
    }

    /**
     * Returns true if both projects have the same identity and data fields.
     * This defines a stronger notion of equality between two projects.
     *
     * @param other {@code Object} to compare with.
     * @return {@code true} if both projects have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Project)) {
            return false;
        }

        Project otherProject = (Project) other;
        return otherProject.getName().equals(getName())
            && otherProject.getDeadline().equals(getDeadline())
            && otherProject.getRepoHost().equals(getRepoHost())
            && otherProject.getRepoName().equals(getRepoName())
            && otherProject.getMembers().equals(getMembers());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, repoHost, repoName, deadline, members);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append("; Repo Host: ")
            .append(getRepoHost())
            .append("; Repo Name: ")
            .append(getRepoName())
            .append("; Deadline: ")
            .append(getDeadline());

        Set<Person> members = getMembers();
        if (!members.isEmpty()) {
            builder.append("; Members: \n");
            for (Person member : members) {
                builder.append(member.getName());
                builder.append("\n");
            }
        }

        return builder.toString();
    }
}

package seedu.socket.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.socket.commons.util.AppUtil.checkArgument;

/**
 * Represents a Project's Repository Name in SOCket.
 * Guarantees: immutable; s valid as declared n {@link #isValidProjectRepoName(String)}
 */
public class ProjectRepoName {
    /** As specified in form validation on <a href="https://github.com/join">Join GitHub</a> */
    public static final String MESSAGE_CONSTRAINTS =
        "Repo name may only contain alphanumeric characters or single hyphens, cannot begin or end with a "
            + "hyphen, and may not exceed 39 characters.";
    /**
     * May only contain alphanumeric characters or single hyphens. May be empty.
     */
    public static final String VALIDATION_REGEX = "^(?=.{1,39}$)(?!-)(?!.*--)[-a-zA-Z0-9]+(?<!-)$|^$";

    public final String value;

    /**
     * Constructs a {@code ProjectRepoName}.
     *
     * @param name A valid ProjectRepoName
     */
    public ProjectRepoName(String name) {
        requireNonNull(name);
        checkArgument(isValidProjectRepoName(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidProjectRepoName(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    /**
     * Returns true if repository name is empty.
     *
     * @return {@code true} if repository name is empty.
     */
    public boolean isEmptyRepoName() {
        return value.equals("");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ProjectRepoName // instanceof handles nulls
            && value.equals(((ProjectRepoName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

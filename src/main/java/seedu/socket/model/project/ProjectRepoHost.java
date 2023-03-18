package seedu.socket.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.socket.commons.util.AppUtil.checkArgument;

/**
 * Represents a Project's Repository Host in SOCket.
 * Guarantees: immutable; is valid as declared in {@link #isValidProjectRepoHost(String)}
 */
public class ProjectRepoHost {
    /**
     * As specified in form validation on
     * <a href="https://github.com/account/organizations/new?plan=free">Set up your organization</a>
     * */
    public static final String MESSAGE_CONSTRAINTS =
        "Repository name may only contain alphanumeric characters or single hyphens, cannot begin or end with a "
            + "hyphen, and may not exceed 39 characters.";
    /**
     * May only contain alphanumeric characters or single hyphens, and cannot begin or end with a hyphen. Maximum 39
     * characters. May be empty.
     */
    public static final String VALIDATION_REGEX = "^(?=.{1,39}$)(?!-)(?!.*--)[-a-zA-Z0-9]+(?<!-)$|^$";

    public final String value;

    /**
     * Constructs a {@code ProjectRepoHost}.
     * @param host A valid ProjectRepoHost
     */
    public ProjectRepoHost(String host) {
        requireNonNull(host);
        checkArgument(isValidProjectRepoHost(host), MESSAGE_CONSTRAINTS);
        value = host;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidProjectRepoHost(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    /**
     * Returns true if repository host is empty.
     *
     * @return {@code true} if repository host is empty.
     */
    public boolean isEmptyRepoHost() {
        return value.equals("");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ProjectRepoHost // instanceof handles nulls
            && value.equals(((ProjectRepoHost) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

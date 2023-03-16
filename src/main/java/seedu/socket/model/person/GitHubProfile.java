package seedu.socket.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.socket.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's GitHub profile in SOCket.
 * Guarantees: immutable; is valid as declared in {@link #isValidProfile(String)}
 */
public class GitHubProfile {
    /** As specified in form validation on <a href="https://github.com/join">Join GitHub</a> */
    public static final String MESSAGE_CONSTRAINTS =
            "Username may only contain alphanumeric characters or single hyphens, cannot begin or end with a "
            + "hyphen, and may not exceed 39 characters.";
    /**
     * May only contain alphanumeric characters or single hyphens, and cannot begin or end with a hyphen. Maximum 39
     * characters. May be empty.
     */
    public static final String VALIDATION_REGEX = "^(?=.{1,39}$)(?!-)(?!.*--)[-a-zA-Z0-9]+(?<!-)$|^$";

    public final String value;

    /**
     * Constructs a {@code GitHubProfile}.
     *
     * @param profile A valid GitHubProfile.
     */
    public GitHubProfile(String profile) {
        requireNonNull(profile);
        checkArgument(isValidProfile(profile), MESSAGE_CONSTRAINTS);
        value = profile;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidProfile(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if GitHub username is empty.
     *
     * @return {@code true} if GitHub username is empty.
     */
    public boolean isEmptyProfile() {
        return value.equals("");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GitHubProfile // instanceof handles nulls
                && value.equals(((GitHubProfile) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

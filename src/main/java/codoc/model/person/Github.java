package codoc.model.person;

import static codoc.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's GitHub username in CoDoc. Guarantees: immutable; is valid as declared in
 * {@link #isValidGithub(String)}
 */
public class Github {


    public static final String MESSAGE_CONSTRAINTS =
            "GitHub username must\n"
                    + "    - be from 1 to 39 characters long\n"
                    + "    - only contain alphanumeric characters and hyphens (-)\n"
                    + "    - not begin or end with a hyphen, or include consecutive hyphens.";
    public static final String VALIDATION_REGEX = "^(?!-)(?!.*--)[\\w-]{1,39}(?<!-)$";
    public final String value;

    /**
     * Constructs a {@code Github}.
     *
     * @param github A valid GitHub username.
     */
    public Github(String github) {
        if (github != null) {
            checkArgument(isValidGithub(github), MESSAGE_CONSTRAINTS);
        }
        value = github;
    }

    /**
     * Returns true if a given string is a valid GitHub username.
     */
    public static boolean isValidGithub(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        if (value != null) {
            return value;
        }
        return "<not added>";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Github // instanceof handles nulls
                && value.equals(((Github) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

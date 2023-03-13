package codoc.model.person;

import codoc.commons.util.AppUtil;

/**
 * Represents a Person's linkedin in CoDoc.
 * Guarantees: immutable; is valid as declared in {@link #isValidLinkedin(String)}
 */
public class Linkedin {

    public static final String MESSAGE_CONSTRAINTS = "Linkedin URLs can take any values, and it should not be blank";

    /*
     * The first character of the linkedin must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Linkedin}.
     *
     * @param linkedin A valid linkedin.
     */
    public Linkedin(String linkedin) {
        if (linkedin != null) {
            AppUtil.checkArgument(isValidLinkedin(linkedin), MESSAGE_CONSTRAINTS);
        }
        value = linkedin;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidLinkedin(String test) {
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
                || (other instanceof Linkedin // instanceof handles nulls
                && value.equals(((Linkedin) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

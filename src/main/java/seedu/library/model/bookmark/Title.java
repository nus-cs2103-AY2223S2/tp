package seedu.library.model.bookmark;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.util.AppUtil.checkArgument;

/**
 * Represents a Bookmark's title in the library.
 * Guarantees: immutable; is valid as declared in {@link #isValidTitle(String)}
 */
public class Title {

    public static final int TITLE_MAX_LENGTH = 180;

    public static final String MESSAGE_CONSTRAINTS =
            "Title can take any values, it should not be blank and length of title should not be longer than "
                    + TITLE_MAX_LENGTH
                    + "characters";

    /*
     * The first character of the title must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code title}.
     *
     * @param title A valid title.
     */
    public Title(String title) {
        requireNonNull(title);
        checkArgument(isValidTitle(title), MESSAGE_CONSTRAINTS);
        value = title;
    }

    /**
     * Returns true if a given string is a valid title.
     */
    public static boolean isValidTitle(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= TITLE_MAX_LENGTH;
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Title // instanceof handles nulls
                && value.equals(((Title) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

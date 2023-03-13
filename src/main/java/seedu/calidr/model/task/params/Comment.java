package seedu.calidr.model.task.params;

import static java.util.Objects.requireNonNull;
import static seedu.calidr.commons.util.AppUtil.checkArgument;

/**
 * Represents the comment given to a Task.
 */
public class Comment {

    public static final String MESSAGE_CONSTRAINTS = "Comments can take any values, and it should not be blank";

    /*
     * The first character of the comment must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code Comment}.
     *
     * @param comment A valid comment.
     */
    public Comment(String comment) {
        requireNonNull(comment);
        checkArgument(isValidComment(comment), MESSAGE_CONSTRAINTS);
        value = comment;
    }

    /**
     * Returns true if a given string is a valid title.
     */
    public static boolean isValidComment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Comment // instanceof handles nulls
                && value.equals(((Comment) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

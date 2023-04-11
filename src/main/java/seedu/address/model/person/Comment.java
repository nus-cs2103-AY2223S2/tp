package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's comment in the address book.
 */
public class Comment {

    public static final String VALIDATION_REGEX = "";
    public static final String MESSAGE_CONSTRAINTS = "Comment is invalid. Please try again.";
    /** Represents the comment of a person */
    public final String value;

    /**
     * Constructs a Comment.
     * @param comment A valid comment.
     */
    public Comment(String comment) {
        requireNonNull(comment);
        value = comment;
    }

    /**
     * Constructs a Comment with no comment.
     */
    public Comment() {
        value = "No comment";
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

    /**
     * Returns true if a given string is the default string given when the comment isn't given by user.
     *
     * @param test String value to test.
     * @return Boolean value true if the string given is the default string by the system.
     */
    public static boolean isDefaultComment(String test) {
        if (test.equals("Insert student comment here!")) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

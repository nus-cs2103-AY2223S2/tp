package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's comment in the address book.
 */
public class Comment {

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

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

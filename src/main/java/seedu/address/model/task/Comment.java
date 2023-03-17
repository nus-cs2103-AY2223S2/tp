package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a comment for a task in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidComment(String)}
 */
public class Comment {

    public static final String MESSAGE_CONSTRAINTS =
            "Comments should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullComment;

    /**
     * Constructs a {@code Comment}.
     *
     * @param comment A valid comment.
     */
    public Comment(String comment) {
        requireNonNull(comment);
        checkArgument(isValidComment(comment), MESSAGE_CONSTRAINTS);
        fullComment = comment;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidComment(String test) {
        return test.matches(VALIDATION_REGEX);
    }




    @Override
    public String toString() {
        return fullComment;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Comment // instanceof handles nulls
                && fullComment.equals(((seedu.address.model.task.Comment) other).fullComment)); // state check
    }

    @Override
    public int hashCode() {
        return fullComment.hashCode();
    }

}


package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a comment in InternBuddy.
 * Guarantees: immutable; name is valid as declared in {@link #isValidComment(String)}
 */
public class Comment {

    public static final String MESSAGE_CONSTRAINTS = "Comments should not blank.";
    public static final String VALIDATION_REGEX = ".*";

    public final String commentContent;

    /**
     * Constructs a {@code Comment}
     *
     * @param commentContent The string content of the comment
     */
    public Comment(String commentContent) {
        requireNonNull(commentContent);
        checkArgument(isValidComment(commentContent), MESSAGE_CONSTRAINTS);
        this.commentContent = commentContent;
    }

    /**
     * Returns true if a given string is valid content for a comment
     *
     * @param test The string to be tested for comment validity.
     */
    public static boolean isValidComment(String test) {
        if (test.isEmpty() || !test.matches(VALIDATION_REGEX)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Comment // instanceof handles nulls
                && commentContent.equals(((Comment) other).commentContent)); // state check
    }

    @Override
    public int hashCode() {
        return commentContent.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + commentContent + ']';
    }

}

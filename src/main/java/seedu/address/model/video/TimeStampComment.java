package seedu.address.model.video;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a comment of a video at a specific time.
 * Guarantees: immutable
 */
public class TimeStampComment {


    public final String comment;

    /**
     * Constructs a {@code TimeStampComment}.
     *
     * @param comment A time stamp comment.
     */
    public TimeStampComment(String comment) {
        requireNonNull(comment);
        this.comment = comment;
    }

    /**
     * Return the comment of a {@code TimeStampComment}
     * @return the comment
     */

    public String getComment() {
        return comment;
    }


    /**
     * Returns true if both TimeStampComment have the same {@code comment}.<p>
     * This defines a stronger notion of equality between two modules.
     *
     * @param other The module to check if it is equivalent to this module.
     * @return True if both modules have the same fields. Otherwise, false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TimeStampComment)) {
            return false;
        }

        TimeStampComment otherTag = (TimeStampComment) other;
        return otherTag.getComment().equals(this.getComment());
    }


    @Override
    public int hashCode() {
        return comment.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + comment + ']';
    }

}

package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a NoteContent in {@code InternshipTodo} or {@code Note}.
 * Guarantees: immutable; is valid as declared in {@link #isValidContent(String)}
 */
public class NoteContent {

    public static final String MESSAGE_CONSTRAINTS = "NoteList content can contain at most 55 words. "
            + "It should not be blank.";

    /*
     * The first character of the content must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^.+$";

    public final String content;

    /**
     * Constructs a {@code NoteContent} with {@code note}
     */
    public NoteContent(String note) {
        requireNonNull(note);
        checkArgument(isValidContent(note), MESSAGE_CONSTRAINTS);
        content = note;
    }

    /**
     * Returns true if {@code test} is a valid name and is within 1 to 55 characters long.
     */
    public static boolean isValidContent(String test) {
        return test.length() <= 55 && test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.task.NoteContent // instanceof handles nulls
                && content.equals(((seedu.address.model.task.NoteContent) other).content)); // state check
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

}

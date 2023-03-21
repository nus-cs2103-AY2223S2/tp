package seedu.address.model.todo;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a QuickNote's content in the planner.
 * Guarantees: immutable; is valid as declared in {@link #isValidContent(String)}
 */
public class NoteContent {

    public static final String MESSAGE_CONSTRAINTS = "Note content should not be blank.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^.+$";

    public final String content;

    /**
     * Constructs a {@code NoteContent}.
     *
     * @param note A valid note.
     */
    public NoteContent(String note) {
        requireNonNull(note);
        checkArgument(isValidContent(note), MESSAGE_CONSTRAINTS);
        content = note;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidContent(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.todo.NoteContent // instanceof handles nulls
                && content.equals(((seedu.address.model.todo.NoteContent) other).content)); // state check
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}

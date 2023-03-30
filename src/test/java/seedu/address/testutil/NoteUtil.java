package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_CONTENT;

import seedu.address.model.task.Note;



/**
 * A utility class for Note.
 */
public class NoteUtil {

    /**
     * Returns the part of command string for the given {@code note}'s details.
     */
    public static String getNoteDetails(Note note) {
        return PREFIX_NOTE_CONTENT + note.getNote().content;
    }
}

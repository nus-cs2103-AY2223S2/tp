package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.NoteList;
import seedu.address.model.task.Note;

/**
 * A utility class containing a list of {@code Note} objects to be used in tests.
 */
public class TypicalNotes {
    public static final Note NOTE_1 = new NoteBuilder().withNote("Today").build();
    public static final Note NOTE_2 = new NoteBuilder().withNote("is").build();
    public static final Note NOTE_3 = new NoteBuilder().withNote("a").build();
    public static final Note NOTE_4 = new NoteBuilder().withNote("nice").build();
    public static final Note NOTE_5 = new NoteBuilder().withNote("day").build();
    public static final Note NOTE_6 = new NoteBuilder().withNote("?").build();

    /**
     * Returns {@code NoteList} with all the typical notes.
     */
    public static NoteList getTypicalNoteList() {
        NoteList nl = new NoteList();
        for (Note note : getNotes()) {
            nl.addNote(note);
        }
        return nl;
    }

    public static List<Note> getNotes() {
        return new ArrayList<>(Arrays.asList(NOTE_1, NOTE_2, NOTE_3, NOTE_4, NOTE_5, NOTE_6));
    }
}

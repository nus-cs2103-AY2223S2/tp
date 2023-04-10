package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.NoteBuilder;

/**
 * Contains tests for {@code NoteList}.
 */
public class NoteListTest {
    private final NoteList noteList = new NoteList();

    @Test
    public void addNote_addSuccessful() {
        noteList.addNote(new NoteBuilder().build());
        assertTrue(noteList.hasNote(new NoteBuilder().build()));
    }

    @Test
    public void setNote_setSuccessful() {
        noteList.addNote(new NoteBuilder().build());
        noteList.setNote(new NoteBuilder().build(), new NoteBuilder().withNote("changed").build());
        assertFalse(noteList.hasNote(new NoteBuilder().build()));
        assertTrue(noteList.hasNote(new NoteBuilder().withNote("changed").build()));
    }

    @Test
    public void equals_returnsTrue() {
        noteList.addNote(new NoteBuilder().build());
        NoteList other = new NoteList();
        other.addNote(new NoteBuilder().build());
        assertTrue(noteList.equals(other));
    }
}

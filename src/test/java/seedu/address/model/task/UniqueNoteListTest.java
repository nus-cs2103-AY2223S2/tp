package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateNoteException;
import seedu.address.model.task.exceptions.NoteNotFoundException;
import seedu.address.testutil.NoteBuilder;

/**
 * Contains tests for {@code UniqueNoteList}.
 */
public class UniqueNoteListTest {

    private final UniqueNoteList uniqueNoteList = new UniqueNoteList();

    @Test
    public void addNote_duplicateNotes_alertDuplicates() {
        uniqueNoteList.addNote(new NoteBuilder().build());
        assertThrows(DuplicateNoteException.class, () -> {
            uniqueNoteList.addNote(new NoteBuilder().build());
        });
    }

    @Test
    public void setNotes_newNotes_setSuccessful() {
        uniqueNoteList.addNote(new NoteBuilder().withNote("test 1").build());
        uniqueNoteList.setNotes(new NoteBuilder().withNote("test 1").build(),
                new NoteBuilder().withNote("test 2").build());
        assertTrue(uniqueNoteList.containsNote(new NoteBuilder().withNote("test 2").build()));
        assertFalse(uniqueNoteList.containsNote(new NoteBuilder().withNote("test 1").build()));
    }

    @Test
    public void setNotes_duplicateNotes_alertDuplicates() {
        uniqueNoteList.addNote(new NoteBuilder().withNote("test 1").build());
        uniqueNoteList.addNote(new NoteBuilder().withNote("test 2").build());
        assertThrows(DuplicateNoteException.class, () -> {
            uniqueNoteList.setNotes(new NoteBuilder().withNote("test 2").build(),
                    new NoteBuilder().withNote("test 1").build());
        });
    }

    @Test
    public void setNotes_noteNotExists_alertNotFound() {
        uniqueNoteList.addNote(new NoteBuilder().withNote("test 1").build());
        assertThrows(NoteNotFoundException.class, () -> {
            uniqueNoteList.setNotes(new NoteBuilder().withNote("test 2").build(),
                    new NoteBuilder().withNote("test 1").build());
        });
    }

    @Test
    public void setNotes_noteList_setSuccessful() {
        uniqueNoteList.addNote(new NoteBuilder().withNote("test 1").build());
        UniqueNoteList replacement = new UniqueNoteList();
        replacement.addNote(new NoteBuilder().withNote("repl 1").build());
        replacement.addNote(new NoteBuilder().withNote("repl 2").build());
        uniqueNoteList.setNotes(replacement);
        assertEquals(uniqueNoteList, replacement);
    }

    @Test
    public void remove_notes_alertNotFound() {
        uniqueNoteList.addNote(new NoteBuilder().withNote("test 1").build());
        assertThrows(NoteNotFoundException.class, () -> {
            uniqueNoteList.remove(new NoteBuilder().withNote("test 2").build());
        });
    }

    @Test
    public void equals_uniqueNoteList_returnsTrue() {
        UniqueNoteList replacement = new UniqueNoteList();
        assertTrue(replacement.equals(uniqueNoteList));
    }
}

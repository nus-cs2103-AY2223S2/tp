package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class NoteListTest {

    private static final Note note1 = new Note();
    private static final Note note2 = new Note("Hello this is my note");
    private static final Note note3 = new Note("Remind me to submit report tmr.");
    private static final Note[] notes = new Note[]{ note1, note2, note3 };

    @Test
    public void noteList_validInput_createsNonemptyObject() {
        NoteList noteList = new NoteList(Arrays.asList(notes));
        assertArrayEquals(notes, noteList.getNotes().toArray(new Note[0]));
        noteList.clear();
    }

    @Test
    public void add_validInputNotes_insertsNotesSequence() {
        NoteList noteList = new NoteList(new LinkedList<>());
        noteList.add(note1);
        noteList.add(note2);
        noteList.add(note3);
        assertArrayEquals(notes, noteList.getNotes().toArray(new Note[0]));
        noteList.clear();
    }

    @Test
    public void addAll_validInputNoteList_addsEntireList() {
        NoteList noteList = new NoteList(Arrays.asList(notes));
        NoteList newNoteList = new NoteList(new LinkedList<>());
        newNoteList.addAll(noteList);
        assertArrayEquals(notes, newNoteList.getNotes().toArray(new Note[0]));
    }

    @Test
    public void merge_mergeTwoValidNoteLists_returnsOneObj() {
        NoteList firstNoteList = new NoteList(Arrays.asList(notes));
        NoteList secondNoteList = new NoteList(Arrays.asList(Arrays.copyOfRange(notes, 1, 2)));
        NoteList mergedList = firstNoteList.merge(secondNoteList);

        List<Note> expectedResult = new ArrayList<>(Arrays.asList(notes));
        expectedResult.addAll(
                Arrays.asList(Arrays.copyOfRange(notes, 1, 2))
        );

        assertArrayEquals(expectedResult.toArray(), mergedList.getNotes().toArray(new Note[0]));
    }

    @Test
    public void remove_invalidIndexOrNullObject_zeroOrThrowsIndexOutOfBoundsException() throws
            IndexOutOfBoundsException {
        NoteList noteList = new NoteList(Arrays.asList(notes));
        assertThrows(IndexOutOfBoundsException.class, () -> noteList.remove(noteList.len() + 1));
        assertFalse(noteList.remove(new Note("Totally new note.")));
    }
}

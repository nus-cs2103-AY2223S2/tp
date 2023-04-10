package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NoteTest {
    @Test
    void testEquals() {
        Note note1 = new Note("This is a note.");
        Note note2 = new Note("This is a note.");
        Note note3 = new Note("This is another note.");
        String string = "This is a note.";

        // same note value, should be equal
        assertTrue(note1.equals(note2));
        // different note value, should not be equal
        assertFalse(note1.equals(note3));
        // different type, should not be equal
        assertFalse(note1.equals(string));
    }
}

package seedu.address.model.note;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void constructor_invalidNoteName_throwsIllegalArgumentException() {
        String invalidNoteName = "";
        assertThrows(IllegalArgumentException.class, () -> new Note(invalidNoteName));
    }

    @Test
    public void isValidNoteName() {
        // null note name
        assertThrows(NullPointerException.class, () -> Note.isValidNoteName(null));
    }

}

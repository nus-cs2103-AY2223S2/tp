package seedu.quickcontacts.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.quickcontacts.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TitleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        String invalidTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new Title(invalidTitle));
    }

    @Test
    public void isValidTitle() {
        // null title
        assertThrows(NullPointerException.class, () -> Title.isValidTitle(null));

        // invalid titles
        assertFalse(Title.isValidTitle("")); // empty string
        assertFalse(Title.isValidTitle(" ")); // spaces only
        assertFalse(Title.isValidTitle(" Meetup with Bob")); // starts with a space

        // valid titles
        assertTrue(Title.isValidTitle("Meetup with Bob"));
        assertTrue(Title.isValidTitle("A")); // one character
        assertTrue(Title.isValidTitle("Group project meeting for CS2103T")); // long title
    }
}

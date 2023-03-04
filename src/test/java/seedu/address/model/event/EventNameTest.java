package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new EventName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> EventName.isValidName(null));

        // invalid name
        assertFalse(EventName.isValidName("")); // empty string
        assertFalse(EventName.isValidName(" ")); // spaces only
        assertFalse(EventName.isValidName("!!")); // only non-alphanumeric characters
        assertFalse(EventName.isValidName("!EFG Concert!")); // starts with non-alphanumeric character

        // valid name
        assertTrue(EventName.isValidName("sports day")); // alphabets only
        assertTrue(EventName.isValidName("54")); // numbers only
        assertTrue(EventName.isValidName("20-24th carnival")); // alphanumeric characters
        assertTrue(EventName.isValidName("Wedding Dinner")); // with capital letters
        assertTrue(EventName.isValidName("Company XYZ 20th Anniversary Dinner")); // long names
        assertTrue(EventName.isValidName("22nd Marathon @ Marina")); // contains punctuations and alphanumeric
    }
}

package seedu.quickcontacts.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.testutil.Assert;

public class DescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidTitle() {
        // null description
        Assert.assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid descriptions
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only
        assertFalse(Description.isValidDescription(" This is a test description")); // starts with a space

        // valid descriptions
        assertTrue(Description.isValidDescription("Purpose: Onboard everyone onto QuickContacts"));
        assertTrue(Description.isValidDescription("A")); // one character
        assertTrue(Description.isValidDescription(
                "This meeting is created so that everyone can be understand the workflow of "
                        + "QuickContacts with ease. The meeting should last for 2 hours.")); // long description
    }
}

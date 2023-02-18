package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null Description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // blank Description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // valid Description
        assertTrue(Description.isValidDescription("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(Description.isValidDescription("PeterJack.1190@example.com")); // period in local part
        assertTrue(Description.isValidDescription("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(Description.isValidDescription("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(Description.isValidDescription("a@bc")); // minimal
        assertTrue(Description.isValidDescription("test@localhost")); // alphabets only
        assertTrue(Description.isValidDescription("123@145")); // numeric local part
        assertTrue(Description.isValidDescription("a1+be.d@example1.com")); // alphanumeric and special characters
        assertTrue(Description.isValidDescription("peter_jack@very-very-very-long-example.com")); // long desc
        assertTrue(Description.isValidDescription("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(Description.isValidDescription("e1234567@u.nus.edu")); // more than one period in description
    }
}

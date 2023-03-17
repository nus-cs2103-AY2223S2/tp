package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    //    @Test
    //    public void constructor_invalidDescription_throwsIllegalArgumentException() {
    //        String invalidDescription = "";
    //        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    //    }

    @Test
    public void isValidDescription() {
        // null descriptions
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid descriptions
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // valid descriptions
        assertTrue(Description.isValidDescription("Sample transaction"));
        assertTrue(Description.isValidDescription(
                "Alice bought many boxes of apple; Bob bought bananas")); // long descriptions
    }
}


package seedu.address.model.listing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
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
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only
        assertFalse(Description.isValidDescription("^")); // only non-alphanumeric characters
        assertFalse(Description.isValidDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
                + "Aenean commodo ligula eget dolor. Aenean massa. "
                + "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. "
                + "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. "
                + "Nulla consequat massa quis enim. "
                + "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. "
                + "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. "
                + "Nullam dictum felis eu pede mollis pretium. "
                + "Integer tincidunt. Cras dapibus")); // 501 characters fail

        // valid description
        assertTrue(Description.isValidDescription("professor*")); // contains non-alphanumeric characters
        assertTrue(Description.isValidDescription("chicken rice seller")); // alphabets only
        assertTrue(Description.isValidDescription("12345")); // numbers only
        assertTrue(Description.isValidDescription("astronaut 2")); // alphanumeric characters
        assertTrue(Description.isValidDescription("Capital Captain")); // with capital letters
        assertTrue(Description.isValidDescription("Staff Software Engineer for CS2103T, yes")); // long descriptions
    }

    @Test
    public void stringTest() {
        Description description = new Description(VALID_DESCRIPTION);
        assertTrue(description.toString().equals(VALID_DESCRIPTION));
    }

    @Test
    public void hashCodeTest() {
        Description description = new Description(VALID_DESCRIPTION);
        assertTrue(description.hashCode() == VALID_DESCRIPTION.hashCode());
    }
}

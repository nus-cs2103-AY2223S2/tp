package seedu.address.model.listing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class JobDescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JobDescription(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new JobDescription(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> JobDescription.isValidDescription(null));

        // invalid description
        assertFalse(JobDescription.isValidDescription("")); // empty string
        assertFalse(JobDescription.isValidDescription(" ")); // spaces only
        assertFalse(JobDescription.isValidDescription("^")); // only non-alphanumeric characters
        assertFalse(JobDescription.isValidDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
                + "Aenean commodo ligula eget dolor. Aenean massa. "
                + "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. "
                + "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. "
                + "Nulla consequat massa quis enim. "
                + "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. "
                + "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. "
                + "Nullam dictum felis eu pede mollis pretium. "
                + "Integer tincidunt. Cras dapibus")); // 501 characters fail

        // valid description
        assertTrue(JobDescription.isValidDescription("professor*")); // contains non-alphanumeric characters
        assertTrue(JobDescription.isValidDescription("chicken rice seller")); // alphabets only
        assertTrue(JobDescription.isValidDescription("12345")); // numbers only
        assertTrue(JobDescription.isValidDescription("astronaut 2")); // alphanumeric characters
        assertTrue(JobDescription.isValidDescription("Capital Captain")); // with capital letters
        assertTrue(JobDescription.isValidDescription("Staff Software Engineer for CS2103T, yes")); // long descriptions
    }

    @Test
    public void stringTest() {
        JobDescription jobDescription = new JobDescription(VALID_DESCRIPTION);
        assertTrue(jobDescription.toString().equals(VALID_DESCRIPTION));
    }

    @Test
    public void hashCodeTest() {
        JobDescription jobDescription = new JobDescription(VALID_DESCRIPTION);
        assertTrue(jobDescription.hashCode() == VALID_DESCRIPTION.hashCode());
    }
}

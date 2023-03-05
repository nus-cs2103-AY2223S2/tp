package seedu.address.model.listing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class JobTitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JobTitle(null));
    }

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        String invalidTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new JobTitle(invalidTitle));
    }

    @Test
    public void isValidTitle() {
        // null title
        assertThrows(NullPointerException.class, () -> JobTitle.isValidTitle(null));

        // invalid title
        assertFalse(JobTitle.isValidTitle("")); // empty string
        assertFalse(JobTitle.isValidTitle(" ")); // spaces only
        assertFalse(JobTitle.isValidTitle("^")); // only non-alphanumeric characters
        assertFalse(JobTitle.isValidTitle("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
                + "Aenean commodo ligula eget dolor. Aenean ma")); // 101 characters fail
        assertFalse(JobTitle.isValidTitle("\nsomething")); // disallowed characters
        assertFalse(JobTitle.isValidTitle("????")); // 4 or more consecutive special characters

        // valid title
        assertTrue(JobTitle.isValidTitle("professor*")); // contains non-alphanumeric characters
        assertTrue(JobTitle.isValidTitle("chicken rice seller")); // alphabets only
        assertTrue(JobTitle.isValidTitle("12345")); // numbers only
        assertTrue(JobTitle.isValidTitle("astronaut 2")); // alphanumeric characters
        assertTrue(JobTitle.isValidTitle("Capital Captain")); // with capital letters
        assertTrue(JobTitle.isValidTitle("Staff Software Engineer for CS2103T")); // long titles
    }

    @Test
    public void stringTest() {
        JobTitle jobTitle = new JobTitle(VALID_TITLE);
        assertTrue(jobTitle.toString().equals(VALID_TITLE));
    }

    @Test
    public void hashCodeTest() {
        JobTitle jobTitle = new JobTitle(VALID_TITLE);
        assertTrue(jobTitle.hashCode() == VALID_TITLE.hashCode());
    }
}

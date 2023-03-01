package seedu.address.model.listing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TitleTest {
    private static String CHARACTER_LIMIT = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
            + "Aenean commodo ligula eget dolor. Aenean ma";

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

        // invalid title
        assertFalse(Title.isValidTitle("")); // empty string
        assertFalse(Title.isValidTitle(" ")); // spaces only
        assertFalse(Title.isValidTitle("^")); // only non-alphanumeric characters
        assertFalse(Title.isValidTitle(CHARACTER_LIMIT)); // 101 characters fail
        assertFalse(Title.isValidTitle("\nsomething")); // disallowed characters
        assertFalse(Title.isValidTitle("????")); // 4 or more consecutive special characters

        // valid title
        assertTrue(Title.isValidTitle("professor*")); // contains non-alphanumeric characters
        assertTrue(Title.isValidTitle("chicken rice seller")); // alphabets only
        assertTrue(Title.isValidTitle("12345")); // numbers only
        assertTrue(Title.isValidTitle("astronaut 2")); // alphanumeric characters
        assertTrue(Title.isValidTitle("Capital Captain")); // with capital letters
        assertTrue(Title.isValidTitle("Staff Software Engineer for CS2103T")); // long titles
    }

    @Test
    public void stringTest() {
        Title title = new Title(VALID_TITLE);
        assertTrue(title.toString().equals(VALID_TITLE));
    }

    @Test
    public void hashCodeTest() {
        Title title = new Title(VALID_TITLE);
        assertTrue(title.hashCode() == VALID_TITLE.hashCode());
    }
}

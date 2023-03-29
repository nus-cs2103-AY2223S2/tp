package seedu.address.model.opening;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.ultron.model.opening.Keydate;

public class KeydateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Keydate(null, "2023-01-01"));
        assertThrows(NullPointerException.class, () -> new Keydate("OA", null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidKey = "";
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Keydate("OA", invalidDate));
        assertThrows(IllegalArgumentException.class, () -> new Keydate(invalidKey, "2023-01-01"));
    }

    @Test
    public void isValidDate() {
        // null keydate
        assertThrows(NullPointerException.class, () -> Keydate.isValidKeydate(null));

//        // invalid keydate
//        assertFalse(Keydate.isValidKeydate("")); // empty string
//        assertFalse(Keydate.isValidKeydate(" ")); // spaces only
//        assertFalse(Keydate.isValidKeydate("@2023-01-01")); // missing key
//        assertFalse(Keydate.isValidKeydate(" @2023-01-01")); // missing key
//
//        assertFalse(Keydate.isValidKeydate("OA@")); // missing date
//        assertFalse(Keydate.isValidKeydate("OA@ ")); // missing date
//        assertFalse(Keydate.isValidKeydate("OA2023-01-01")); // missing @
//        assertFalse(Keydate.isValidKeydate("OA 2023-01-01")); // @ replaced by whitespace
//
//        // invalid key
//        assertFalse(Keydate.isValidKeydate("")); //
//        assertFalse(Keydate.isValidKeydate(" ")); //
//        assertFalse(Keydate.isValidKeydate(" ")); //
//        assertFalse(Keydate.isValidKeydate(" ")); //
//        assertFalse(Keydate.isValidKeydate(" ")); //
//
//        // invalid date
//
//        assertFalse(Keydate.isValidKeydate("&")); // only non-alphanumeric characters
//        assertFalse(Keydate.isValidKeydate("123")); // numbers only
//        assertFalse(Keydate.isValidKeydate("Engineer 10")); // with numbers
//        assertFalse(Keydate.isValidKeydate("Data-analyst 10")); // with non-alphanumeric characters and numbers
//
//        // valid keydate
//        assertTrue(Keydate.isValidKeydate("analyst")); // alphabets only
//        assertTrue(Keydate.isValidKeydate("Intern")); // with capital letters
//        assertTrue(Keydate.isValidKeydate("data-analyst")); // with non-alphanumeric characters, but no numbers
//        assertTrue(Keydate.isValidKeydate("analyst & engineer")); // non-alphanumeric characters, no numbers
//        assertTrue(Keydate.isValidKeydate("software engineer for integrated processing")); // long keydates
    }
}

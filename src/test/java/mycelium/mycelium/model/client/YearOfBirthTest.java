package mycelium.mycelium.model.client;

import static mycelium.mycelium.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class YearOfBirthTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new YearOfBirth(null));
    }

    @Test
    public void constructor_invalidYearOfBirth_throwsIllegalArgumentException() {
        String invalidYearOfBirth = "";
        assertThrows(IllegalArgumentException.class, () -> new YearOfBirth(invalidYearOfBirth));
    }

    @Test
    public void isValidYearOfBirth() {
        // null years
        assertThrows(NullPointerException.class, () -> YearOfBirth.isValidYearOfBirth(null));

        // invalid years
        assertFalse(YearOfBirth.isValidYearOfBirth("")); // empty string
        assertFalse(YearOfBirth.isValidYearOfBirth(" ")); // spaces only
        assertFalse(YearOfBirth.isValidYearOfBirth("91")); // less than 4 numbers
        assertFalse(YearOfBirth.isValidYearOfBirth("9 1")); // spaces between digits
        assertFalse(YearOfBirth.isValidYearOfBirth("91333")); // more than 4 numbers
        assertFalse(YearOfBirth.isValidYearOfBirth("phone")); // non-numeric
        assertFalse(YearOfBirth.isValidYearOfBirth("9011p041")); // alphabets within digits

        // valid years
        assertTrue(YearOfBirth.isValidYearOfBirth("2004")); // exactly 4 numbers
        assertTrue(YearOfBirth.isValidYearOfBirth("1534"));
    }

    @Test
    public void isCorrectString() {
        YearOfBirth year = new YearOfBirth("2099");
        assertEquals(year.toString(), "2099");
        assertNotEquals(year.toString(), "0");
    }
}

package seedu.fitbook.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CalorieTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Calorie(null));
    }

    @Test
    public void constructor_invalidCalorie_throwsIllegalArgumentException() {
        String invalidCalorie = "";
        assertThrows(IllegalArgumentException.class, () -> new Calorie(invalidCalorie));
    }

    @Test
    public void isValidCalorie() {
        // null calorie number
        assertThrows(NullPointerException.class, () -> Calorie.isValidCalorie(null));

        // invalid calorie numbers
        assertFalse(Calorie.isValidCalorie("")); // empty string
        assertFalse(Calorie.isValidCalorie(" ")); // spaces only
        assertFalse(Calorie.isValidCalorie("91")); // less than 4 numbers
        assertFalse(Calorie.isValidCalorie("phone")); // non-numeric
        assertFalse(Calorie.isValidCalorie("9011p041")); // alphabets within digits
        assertFalse(Calorie.isValidCalorie("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Calorie.isValidCalorie("2500")); // exactly 4 numbers
        assertTrue(Calorie.isValidCalorie("124293842033123")); // long calorie numbers
    }

    @Test
    public void test_equalsSymmetric() {
        Calorie calorieA = new Calorie("1300");
        Calorie calorieB = new Calorie("1300");
        assertTrue(calorieA.equals(calorieB) && calorieB.equals(calorieA));
        assertTrue(calorieA.hashCode() == calorieB.hashCode());
    }
}

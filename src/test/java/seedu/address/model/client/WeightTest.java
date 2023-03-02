package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class WeightTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Weight(null));
    }

    @Test
    public void constructor_invalidWeight_throwsIllegalArgumentException() {
        String invalidWeight = "";
        assertThrows(IllegalArgumentException.class, () -> new Weight(invalidWeight));
    }

    @Test
    public void isValidWeight() {
        // null gender
        assertThrows(NullPointerException.class, () -> Weight.isValidWeight(null));

        // invalid genders
        assertFalse(Weight.isValidWeight("")); // empty string
        assertFalse(Weight.isValidWeight("-23")); // negative weight

        // valid genders
        assertTrue(Weight.isValidWeight("23")); // whole number weight
        assertTrue(Weight.isValidWeight("233.0")); // weight to 1 decimal
        assertTrue(Weight.isValidWeight("23.00")); // weight to 2 decimal
    }
}

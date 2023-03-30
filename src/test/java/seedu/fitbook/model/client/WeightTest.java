package seedu.fitbook.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.testutil.Assert.assertThrows;

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
    }

    @Test
    public void test_equalsSymmetric() {
        Weight weightA = new Weight("23");
        Weight weightB = new Weight("23");
        assertTrue(weightA.equals(weightB) && weightB.equals(weightA));
        assertTrue(weightA.hashCode() == weightB.hashCode());
    }
}

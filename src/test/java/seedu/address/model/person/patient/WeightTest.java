package seedu.address.model.person.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WeightTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Weight(null));
    }

    @Test
    public void constructor_invalidWeight_throwsIllegalArgumentException() {
        String invalidWeight = "70.000";
        assertThrows(IllegalArgumentException.class, () -> new Weight(invalidWeight));
    }

    @Test
    public void isValidWeight() {

        assertThrows(NullPointerException.class, () -> Weight.isValidWeight(null));

        // invalid weights
        assertFalse(Weight.isValidWeight("")); // empty string
        assertFalse(Weight.isValidWeight(" ")); // spaces only
        assertFalse(Weight.isValidWeight("1.000")); // more than two decimal places
        assertFalse(Weight.isValidWeight("0.0")); // single digit decimal fraction
        assertFalse(Weight.isValidWeight("0.000")); // three digit decimal fraction
        assertFalse(Weight.isValidWeight("1.234")); // more than two decimal places
        assertFalse(Weight.isValidWeight("-60.00")); // negative weight
        assertFalse(Weight.isValidWeight("0")); // 0 weight

        // valid weights
        assertTrue(Weight.isValidWeight("0.1")); // smallest possible value
        assertTrue(Weight.isValidWeight("1.0")); // whole number
        assertTrue(Weight.isValidWeight("60")); // no decimal point
        assertTrue(Weight.isValidWeight("600000")); // large numbers
        assertTrue(Weight.isValidWeight("60.1")); // nonzero decimal fraction
        assertTrue(Weight.isValidWeight("100.5")); // larger value
    }

    @Test
    public void getValue_validWeight_returnsWeight() {
        //valid weights
        Weight weight1 = new Weight("0.1");
        Weight weight2 = new Weight("1.0");
        Weight weight3 = new Weight("60");
        Weight weight4 = new Weight("600000");
        Weight weight5 = new Weight("60.1");
        Weight weight6 = new Weight("100.5");

        assertEquals("0.1", weight1.getValue());
        assertEquals("1.0", weight2.getValue());
        assertEquals("60", weight3.getValue());
        assertEquals("600000", weight4.getValue());
        assertEquals("60.1", weight5.getValue());
        assertEquals("100.5", weight6.getValue());
    }
}

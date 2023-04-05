package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class CostTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cost(null));
    }

    @Test
    public void constructor_invalidMedication_throwsIllegalArgumentException() {
        String invalidMedication = "-";
        assertThrows(IllegalArgumentException.class, () -> new Cost(invalidMedication));
    }


    @Test
    public void isValidCost_valid_returnsTrue() {
        assertTrue(Cost.isValidCost("0"));
        assertTrue(Cost.isValidCost("1"));
        assertTrue(Cost.isValidCost("1.0"));
        assertTrue(Cost.isValidCost("1.00"));
    }

    @Test
    public void isValidCost_invalid_returnsFalse() {
        assertFalse(Cost.isValidCost("0.001"));
        assertFalse(Cost.isValidCost("1."));
        assertFalse(Cost.isValidCost("-1"));
    }

    @Test
    public void getvalue() {
        Double value = 10.0;
        Cost validCost = new Cost(value.toString());
        assertEquals(validCost.getValue(), value);
    }

}

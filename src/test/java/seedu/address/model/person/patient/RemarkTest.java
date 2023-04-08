package seedu.address.model.person.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void constructor_invalidRemark_throwsIllegalArgumentException() {
        String invalidRemark = "";
        assertThrows(IllegalArgumentException.class, () -> new Remark(invalidRemark));
    }

    @Test
    public void isValidRemark() {

        assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));

        // invalid remark
        assertFalse(Remark.isValidRemark("")); // empty string
        assertFalse(Remark.isValidRemark(" ")); // spaces only
        assertFalse(Remark.isValidRemark("       ")); // many spaces

        // valid remark
        assertTrue(Remark.isValidRemark("@#$%")); // special characters
        assertTrue(Remark.isValidRemark("-123")); // numbers
        assertTrue(Remark.isValidRemark("remark with spaces ")); // ends with spaces
        assertTrue(Remark.isValidRemark(" remark with spaces")); // starts with spaces
        assertTrue(Remark.isValidRemark("Diabetes")); // alphanumeric only
        assertTrue(Remark.isValidRemark("Asthma and allergies")); // alphanumeric and spaces
        assertTrue(Remark.isValidRemark("Acute Upper Respiratory Infection")); // long remark
    }

    @Test
    public void getValue_validRemark_returnsRemark() {
        String remark = "Asthma";
        Remark validRemark = new Remark(remark);
        assertEquals(validRemark.getValue(), remark);
    }
}

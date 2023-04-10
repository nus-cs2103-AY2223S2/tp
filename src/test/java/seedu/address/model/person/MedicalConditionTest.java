package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class MedicalConditionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MedicalCondition(null));
    }

    @Test
    public void isValidCondition() {
        // null age
        assertThrows(NullPointerException.class, () -> MedicalCondition.isValidCondition(null));

        assertFalse(MedicalCondition.isValidCondition(" ")); // spaces only

        // valid ages
        assertTrue(MedicalCondition.isValidCondition("Asthma")); // string
        assertTrue(MedicalCondition.isValidCondition("")); // empty string
        assertTrue(MedicalCondition.isValidCondition("Flu")); // string
    }

}

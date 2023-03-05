package seedu.vms.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.vms.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class VaccineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Vaccine(null));
    }

    @Test
    public void constructor_invalidVaccineName_throwsIllegalArgumentException() {
        String invalidVaccineName = "";
        assertThrows(IllegalArgumentException.class, () -> new Vaccine(invalidVaccineName));
    }

    @Test
    public void isValidVaccineName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Vaccine.isValidVaccineName(null));

        // valid
        assertTrue(Vaccine.isValidVaccineName("test"));

        // invalid
        assertFalse(Vaccine.isValidVaccineName("%1"));
    }

}

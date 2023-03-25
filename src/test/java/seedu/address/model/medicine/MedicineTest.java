package seedu.address.model.medicine;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MedicineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Medicine(null));
    }

    @Test
    public void constructor_invalidMedicineName_throwsIllegalArgumentException() {
        String invalidMedicineName = "";
        assertThrows(IllegalArgumentException.class, () -> new Medicine(invalidMedicineName));
    }

    @Test
    public void isValidMedicineName() {
        // null medicine name
        assertThrows(NullPointerException.class, () -> Medicine.isValidMedicineName(null));
    }

}

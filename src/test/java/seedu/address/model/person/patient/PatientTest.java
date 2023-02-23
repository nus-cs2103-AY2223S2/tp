package seedu.address.model.person.patient;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PatientTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Patient(null, null, null, null, null, null, null));
    }
}

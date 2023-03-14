package seedu.careflow.model.person;

import static seedu.careflow.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PatientTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Patient(null, null, null, null, null, null, null));
    }


}

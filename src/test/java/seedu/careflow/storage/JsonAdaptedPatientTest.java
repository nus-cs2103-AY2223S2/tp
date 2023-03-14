package seedu.careflow.storage;

import org.junit.jupiter.api.Test;

import seedu.careflow.model.person.Patient;
import seedu.careflow.model.util.SampleDataUtil;

class JsonAdaptedPatientTest {
    @Test
    public void constructorTest() {
        Patient p = SampleDataUtil.getSamplePatients()[1];
        new JsonAdaptedPatient(p);
    }

}

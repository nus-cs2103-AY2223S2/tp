package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Patient;
import seedu.address.model.util.SampleDataUtil;

import static org.junit.jupiter.api.Assertions.*;

class JsonAdaptedPatientTest {
    @Test
    public void constructorTest() {
        Patient p = SampleDataUtil.getSamplePatients()[1];
        new JsonAdaptedPatient(p);
    }

}

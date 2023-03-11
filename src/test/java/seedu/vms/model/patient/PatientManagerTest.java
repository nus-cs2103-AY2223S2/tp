package seedu.vms.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.vms.testutil.Assert.assertThrows;
import static seedu.vms.testutil.TypicalPatients.getTypicalPatientManager;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class PatientManagerTest {

    private final PatientManager patientManager = new PatientManager();

    @Test
    public void constructor() {
        Map<?, ?> map = patientManager.getMapView();
        assertEquals(true, map.isEmpty());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> patientManager.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPatientManager_replacesData() {
        PatientManager newData = getTypicalPatientManager();
        patientManager.resetData(newData);
        assertEquals(newData, patientManager);
    }

    @Test
    public void getPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> getTypicalPatientManager().getMapView().remove(0));
    }

}

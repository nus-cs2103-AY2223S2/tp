package seedu.careflow.model.readonly;

import javafx.collections.ObservableList;
import seedu.careflow.model.person.Patient;

/**
 * Unmodifiable view of a patient records
 */
public interface ReadOnlyPatientRecord {
    ObservableList<Patient> getPatientList();
}

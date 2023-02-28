package seedu.address.model.readonly;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.address.model.person.patient.Patient;

/**
 * Unmodifiable view of a patient records
 */
public interface ReadOnlyPatientRecord {
    ObservableList<Patient> getPatientList();
}

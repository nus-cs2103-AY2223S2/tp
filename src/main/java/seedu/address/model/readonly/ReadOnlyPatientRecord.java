package seedu.address.model.readonly;

import javafx.collections.ObservableList;
import seedu.address.model.person.Patient;

/**
 * Unmodifiable view of a patient records
 */
public interface ReadOnlyPatientRecord {
    ObservableList<Patient> getPatientList();
}

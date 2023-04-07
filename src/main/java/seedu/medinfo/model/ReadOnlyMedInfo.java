package seedu.medinfo.model;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.model.ward.Ward;


/**
 * Unmodifiable view of a MedInfo
 */
public interface ReadOnlyMedInfo {

    void sortPatients(Comparator<Patient> comparator);

    /**
     * Returns an unmodifiable view of the patients list.
     * This list will not contain any duplicate patients.
     */
    ObservableList<Patient> getPatientList();

    ObservableList<Ward> getWardList();

    List<String> getStatsInfo();
}

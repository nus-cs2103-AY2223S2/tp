package seedu.address.model;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.patient.Patient;
import seedu.address.model.ward.Ward;


/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    void sortPatients(Comparator<Patient> comparator);

    /**
     * Returns an unmodifiable view of the patients list.
     * This list will not contain any duplicate patients.
     */
    ObservableList<Patient> getPatientList();

    ObservableList<Ward> getWardList();
    List<String> getStatsInfo();
}

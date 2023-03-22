package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();
    /**
     * Returns an unmodifiable view of the doctors list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Doctor> getDoctorList();
    /**
     * Returns an unmodifiable view of the patients list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Patient> getPatientList();

}

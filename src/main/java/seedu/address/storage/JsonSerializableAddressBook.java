package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "docedex")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_DOCTOR = "Doctors list contains duplicate doctors(s).";
    public static final String MESSAGE_DUPLICATE_PATIENT = "Patients list contains duplicate patient(s).";

    private final List<JsonAdaptedDoctor> doctors = new ArrayList<>();
    private final List<JsonAdaptedPatient> unassignedPatients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given doctors and patients.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("doctors") List<JsonAdaptedDoctor> doctors,
                                       @JsonProperty("unassignedPatients")
                                               List<JsonAdaptedPatient> unassignedPatients) {
        this.doctors.addAll(doctors);
        this.unassignedPatients.addAll(unassignedPatients);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        doctors.addAll(source.getDoctorList().stream()
                .map(JsonAdaptedDoctor::new)
                .collect(Collectors.toList()));
        unassignedPatients.addAll(source.getUnassignedPatientList().stream()
                .map(JsonAdaptedPatient::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        loadDoctorsAndTheirAssignedPatients(addressBook);
        loadAssignmentOfPatientsToDoctors(addressBook);
        loadUnassignedPatients(addressBook);
        return addressBook;
    }

    /**
     * Load the doctors and their assigned patients into the address book.
     *
     * @param addressBook the address book to load
     * @throws IllegalValueException when doctor or patients have illegal values as attributes
     */
    private void loadDoctorsAndTheirAssignedPatients(AddressBook addressBook)
            throws IllegalValueException {
        for (JsonAdaptedDoctor jsonAdaptedDoctor : doctors) {
            Doctor doctor = jsonAdaptedDoctor.toModelType();
            if (addressBook.hasDoctor(doctor)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DOCTOR);
            }
            addressBook.addDoctor(doctor);

            Set<Patient> patientsAdded = new HashSet<>();
            for (Patient patient : doctor.getPatients()) {
                // Do not perform a check for duplicate patients here
                // as the patients are bound to be stored multiple
                // times under the different doctors they are assigned to.
                if (patientsAdded.contains(patient)) {
                    throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT);
                }
                if (!addressBook.hasPatient(patient)) {
                    addressBook.addPatient(patient);
                }
                patientsAdded.add(patient);
            }
        }
    }

    /**
     * Load the assignment of patients to their respective doctors into the address book.
     *
     * @param addressBook the address book to load
     */
    private void loadAssignmentOfPatientsToDoctors(AddressBook addressBook) {
        ObservableList<Patient> addressBookPatientList = addressBook.getPatientList();
        ObservableList<Doctor> addressBookDoctorList = addressBook.getDoctorList();
        for (Patient patient : addressBookPatientList) {
            for (Doctor doctor : addressBookDoctorList) {
                if (doctor.hasPatient(patient)) {
                    patient.assignDoctor(doctor);
                }
            }
        }
    }

    /**
     * Load the unassigned patients into the address book.
     *
     * @param addressBook the address book to load
     * @throws IllegalValueException when the patients have illegal values as attributes
     */
    private void loadUnassignedPatients(AddressBook addressBook)
            throws IllegalValueException {
        for (JsonAdaptedPatient jsonAdaptedPatient : unassignedPatients) {
            Patient patient = jsonAdaptedPatient.toModelType();
            if (addressBook.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT);
            }
            addressBook.addPatient(patient);
        }
    }

}

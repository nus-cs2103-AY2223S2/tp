package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

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
                .map(JsonSerializableAddressBook::convertToJsonAdaptedDoctor).collect(Collectors.toList()));
        unassignedPatients.addAll(source.getUnassignedPatientList().stream()
                .map(JsonSerializableAddressBook::convertToJsonAdaptedPatient).collect(Collectors.toList()));
    }

    /**
     * Converts a given {@code Doctor} into a JsonAdaptedDoctor.
     *
     * @param doctor a doctor object.
     * @return a JsonAdaptedDoctor.
     */
    private static JsonAdaptedDoctor convertToJsonAdaptedDoctor(Doctor doctor) {
        return new JsonAdaptedDoctor(doctor);
    }

    /**
     * Converts a given {@code Patient} into a JsonAdaptedPatient.
     *
     * @param patient a patient object.
     * @return a JsonAdaptedPatient.
     */
    private static JsonAdaptedPatient convertToJsonAdaptedPatient(Patient patient) {
        return new JsonAdaptedPatient(patient);
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedDoctor jsonAdaptedDoctor : doctors) {
            Doctor doctor = jsonAdaptedDoctor.toModelType();
            if (addressBook.hasDoctor(doctor)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DOCTOR);
            }
            addressBook.addDoctor(doctor);

            // Add Patients of the doctor into single patient list in AddressBook
            if (doctor.hasPatients()) {
                doctor.getPatients().forEach((Patient patient) -> {
                    // Assign doctor to patient
                    // This is done here as doctorsAssigned is not stored
                    // within each patient json object
                    patient.assignDoctor(doctor);

                    if (!addressBook.hasPatient(patient)) {
                        addressBook.addPatient(patient);
                    }
                });
            }
        }

        // Add unassigned patients
        for (JsonAdaptedPatient jsonAdaptedPatient : unassignedPatients) {
            Patient patient = jsonAdaptedPatient.toModelType();
            if (addressBook.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT);
            }
            addressBook.addPatient(patient);
        }

        return addressBook;
    }

}

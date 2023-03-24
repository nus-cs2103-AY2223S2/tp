package seedu.patientist.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.patientist.commons.exceptions.IllegalValueException;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.staff.Staff;
import seedu.patientist.model.ward.Ward;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Jackson-friendly version of {@link Ward}.
 */
public class JsonAdaptedWard {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Ward's %s field is missing!";
    private final String name;
    private final List<JsonAdaptedPerson> patients = new ArrayList<>();
    private final List<JsonAdaptedPerson> staffs = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedWard(@JsonProperty("name") String name,
                           @JsonProperty("patients") List<JsonAdaptedPerson> patients,
                           @JsonProperty("staffs") List<JsonAdaptedPerson> staffs) {
        this.name = name;
        if (patients != null) {
            this.patients.addAll(patients);
        }
        if (staffs != null) {
            this.staffs.addAll(staffs);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedWard(Ward source) {
        name = source.getWardName();
        patients.addAll(source.getPatientsAsUnmodifiableObservableList().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
        staffs.addAll(source.getStaffsAsUnmodifiableObservableList().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Ward toModelType() throws IllegalValueException {
        final List<Patient> wardPatients = new ArrayList<>();
        final List<Staff> wardStaffs = new ArrayList<>();

        for (JsonAdaptedPerson patient : patients) {
            wardPatients.add((Patient) patient.toModelType());
        }

        for (JsonAdaptedPerson staff : patients) {
            wardStaffs.add((Staff) staff.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }

        Ward ward = new Ward(name);
        for (Patient patient : wardPatients) {
            ward.addPatient(patient);
        }

        for (Staff staff : wardStaffs) {
            ward.addStaff(staff);
        }

        return ward;
    }
}

package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.doctor.Specialty;
import seedu.address.model.person.doctor.Yoe;
import seedu.address.model.person.patient.Patient;

/**
 * Jackson-friendly version of {@link Doctor}.
 */
class JsonAdaptedDoctor extends JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Doctor's %s field is missing!";

    private final String specialty;
    private final String yearsOfExperience;
    private final List<JsonAdaptedPatient> patients = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedDoctor} with the given doctor details.
     */
    @JsonCreator
    public JsonAdaptedDoctor(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("specialty") String specialty,
                             @JsonProperty("yearsOfExperience") String yearsOfExperience,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("patients") List<JsonAdaptedPatient> patients) {
        super(name, phone, email, tagged);
        this.specialty = specialty;
        this.yearsOfExperience = yearsOfExperience;
        if (patients != null) {
            this.patients.addAll(patients);
        }
    }

    /**
     * Converts a given {@code Doctor} into this class for Jackson use.
     */
    public JsonAdaptedDoctor(Doctor source) {
        super(source);
        yearsOfExperience = source.getYoe().getValue();
        specialty = source.getSpecialty().getValue();
        patients.addAll(source.getPatients().stream()
                .map(JsonAdaptedPatient::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Doctor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted doctor.
     */
    public Doctor toModelType() throws IllegalValueException {
        final Specialty modelSpecialty = validateSpecialty();
        final Yoe modelYearsOfExperience = validateYearsOfExperience();
        final Set<Patient> modelPatients = validatePatient();

        Person doctorPerson = super.toModelType();
        return new Doctor(doctorPerson.getName(), doctorPerson.getPhone(), doctorPerson.getEmail(),
                modelSpecialty, modelYearsOfExperience, doctorPerson.getTags(), modelPatients);
    }

    /**
     * Validate the specialty supplied from storage.
     *
     * @return a valid specialty object.
     * @throws IllegalValueException if specialty supplied is not valid.
     */
    private Specialty validateSpecialty() throws IllegalValueException {
        if (specialty == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Specialty.class.getSimpleName()));
        }
        if (!Specialty.isValidSpecialty(specialty)) {
            throw new IllegalValueException(Specialty.MESSAGE_CONSTRAINTS);
        }
        return new Specialty(specialty);
    }

    /**
     * Validate the years of experience supplied from storage.
     *
     * @return a valid years of experience object.
     * @throws IllegalValueException if years of experience supplied is not valid.
     */
    private Yoe validateYearsOfExperience() throws IllegalValueException {
        if (yearsOfExperience == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Yoe.class.getSimpleName()));
        }
        if (!Yoe.isValidYoe(yearsOfExperience)) {
            throw new IllegalValueException(Yoe.MESSAGE_CONSTRAINTS);
        }
        return new Yoe(yearsOfExperience);
    }

    /**
     * Validate the patients supplied from storage.
     *
     * @return a valid set of patients
     * @throws IllegalValueException if patients supplied are not valid.
     */
    private Set<Patient> validatePatient() throws IllegalValueException {
        final List<Patient> patientsList = new ArrayList<>();
        for (JsonAdaptedPatient patient : patients) {
            patientsList.add(patient.toModelType());
        }
        return new HashSet<>(patientsList);
    }

}

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
        yearsOfExperience = source.getYoe().value;
        specialty = source.getSpecialty().specialty;
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
        if (specialty == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Specialty.class.getSimpleName()));
        }
        if (!Specialty.isValidSpecialty(specialty)) {
            throw new IllegalValueException(Specialty.MESSAGE_CONSTRAINTS);
        }
        final Specialty modelSpecialty = new Specialty(specialty);

        if (yearsOfExperience == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Yoe.class.getSimpleName()));
        }
        if (!Yoe.isValidYoe(yearsOfExperience)) {
            throw new IllegalValueException(Yoe.MESSAGE_CONSTRAINTS);
        }
        final Yoe modelYearsOfExperience = new Yoe(yearsOfExperience);

        final List<Patient> patientsList = new ArrayList<>();
        for (JsonAdaptedPatient patient : patients) {
            patientsList.add(patient.toModelType());
        }
        final Set<Patient> modelPatients = new HashSet<>(patientsList);
        // final Set<Patient> modelPatients = new HashSet<>(List.of(new PatientStub()));

        Person doctorPerson = super.toModelType();
        return new Doctor(doctorPerson.getName(), doctorPerson.getPhone(), doctorPerson.getEmail(),
                modelSpecialty, modelYearsOfExperience, doctorPerson.getTags(), modelPatients);
    }

}

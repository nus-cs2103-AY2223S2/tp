package seedu.vms.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.GroupName;
import seedu.vms.model.patient.BloodType;
import seedu.vms.model.patient.Dob;
import seedu.vms.model.patient.Name;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.Phone;

/**
 * Jackson-friendly version of {@link Patient}.
 */
class JsonAdaptedPatient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final String name;
    private final String phone;
    private final String dob;
    private final String bloodType;
    private final List<JsonAdaptedGroupName> allergies = new ArrayList<>();
    private final List<JsonAdaptedGroupName> vaccines = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPatient} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("name") String name,
            @JsonProperty("phone") String phone,
            @JsonProperty("dob") String dob,
            @JsonProperty("bloodType") String bloodType,
            @JsonProperty("allergies") List<JsonAdaptedGroupName> allergies,
            @JsonProperty("vaccines") List<JsonAdaptedGroupName> vaccines) {
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.bloodType = bloodType;
        if (allergies != null) {
            this.allergies.addAll(allergies);
        }
        if (vaccines != null) {
            this.vaccines.addAll(vaccines);
        }
    }

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedPatient(Patient source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        dob = source.getDob().toString();
        bloodType = source.getBloodType().toString();
        allergies.addAll(source.getAllergy().stream()
                .map(JsonAdaptedGroupName::fromModelType)
                .collect(Collectors.toList()));
        vaccines.addAll(source.getVaccine().stream()
                .map(JsonAdaptedGroupName::fromModelType)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's
     * {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted patient.
     */
    public Patient toModelType() throws IllegalValueException {
        final List<GroupName> patientAllergies = new ArrayList<>();
        for (JsonAdaptedGroupName allergy : allergies) {
            patientAllergies.add(allergy.toModelType());
        }
        final List<GroupName> patientVaccines = new ArrayList<>();
        for (JsonAdaptedGroupName vaccine : vaccines) {
            patientVaccines.add(vaccine.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (dob == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Dob.class.getSimpleName()));
        }
        if (!Dob.isValidDob(dob)) {
            throw new IllegalValueException(Dob.MESSAGE_CONSTRAINTS);
        }
        final Dob modelDob = new Dob(dob);

        if (bloodType == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, BloodType.class.getSimpleName()));
        }
        if (!BloodType.isValidBloodType(bloodType)) {
            throw new IllegalValueException(BloodType.MESSAGE_CONSTRAINTS);
        }
        final BloodType modelBloodType = new BloodType(bloodType);

        final Set<GroupName> modelAllergies = new HashSet<>(patientAllergies);
        final Set<GroupName> modelVaccines = new HashSet<>(patientVaccines);
        return new Patient(modelName, modelPhone, modelDob, modelBloodType, modelAllergies, modelVaccines);
    }

}

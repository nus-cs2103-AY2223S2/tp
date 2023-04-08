package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.person.patient.Diagnosis;
import seedu.address.model.person.patient.Height;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.person.patient.Remark;
import seedu.address.model.person.patient.Status;
import seedu.address.model.person.patient.Weight;

/**
 * Jackson-friendly version of {@link Patient}.
 */
class JsonAdaptedPatient extends JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final String height;
    private final String weight;
    private final String diagnosis;
    private final String status;
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedPatient} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("height") String height,
                             @JsonProperty("weight") String weight, @JsonProperty("diagnosis") String diagnosis,
                             @JsonProperty("status") String status, @JsonProperty("remark") String remark,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        super(name, phone, email, tagged);
        this.height = height;
        this.weight = weight;
        this.diagnosis = diagnosis;
        this.status = status;
        this.remark = remark;
    }

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedPatient(Patient source) {
        super(source);
        this.height = source.getHeight().getValue();
        this.weight = source.getWeight().getValue();
        this.diagnosis = source.getDiagnosis().getValue();
        this.status = source.getStatus().toString();
        this.remark = source.getRemark().toString();
    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted doctor.
     */
    public Patient toModelType() throws IllegalValueException {
        final Height modelHeight = validateHeight();
        final Weight modelWeight = validateWeight();
        final Diagnosis modelDiagnosis = validateDiagnosis();
        final Status modelStatus = validateStatus();
        final Remark modelRemark = validateRemark();

        Person patientPerson = super.toModelType();
        return new Patient(patientPerson.getName(), patientPerson.getPhone(),
                patientPerson.getEmail(), modelHeight, modelWeight,
                modelDiagnosis, modelStatus, modelRemark, patientPerson.getTags());
    }

    /**
     * Validate the height supplied from storage.
     *
     * @return a valid height object.
     * @throws IllegalValueException if height supplied is not valid.
     */
    private Height validateHeight() throws IllegalValueException {
        if (height == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName()));
        }
        if (!Height.isValidHeight(height)) {
            throw new IllegalValueException(Height.MESSAGE_CONSTRAINTS);
        }
        return new Height(height);
    }

    /**
     * Validate the weight supplied from storage.
     *
     * @return a valid weight object.
     * @throws IllegalValueException if weight supplied is not valid.
     */
    private Weight validateWeight() throws IllegalValueException {
        if (weight == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName()));
        }
        if (!Weight.isValidWeight(weight)) {
            throw new IllegalValueException(Weight.MESSAGE_CONSTRAINTS);
        }
        return new Weight(weight);
    }

    /**
     * Validate the diagnosis supplied from storage.
     *
     * @return a valid diagnosis object.
     * @throws IllegalValueException if diagnosis supplied is not valid.
     */
    private Diagnosis validateDiagnosis() throws IllegalValueException {
        if (diagnosis == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Diagnosis.class.getSimpleName()));
        }
        if (!Diagnosis.isValidDiagnosis(diagnosis)) {
            throw new IllegalValueException(Diagnosis.MESSAGE_CONSTRAINTS);
        }
        return new Diagnosis(diagnosis);
    }

    /**
     * Validate the status supplied from storage.
     *
     * @return a valid status object.
     * @throws IllegalValueException if status supplied is not valid.
     */
    private Status validateStatus() throws IllegalValueException {
        if (status == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        return new Status(status);
    }

    /**
     * Validate the remark supplied from storage.
     *
     * @return a valid remark object.
     * @throws IllegalValueException if remark supplied is not valid.
     */
    private Remark validateRemark() throws IllegalValueException {
        if (remark == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        if (!Remark.isValidRemark(remark)) {
            throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(remark);
    }
}

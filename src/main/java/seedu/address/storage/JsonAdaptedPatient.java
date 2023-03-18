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
        this.height = source.getHeight().height;
        this.weight = source.getWeight().weight;
        this.diagnosis = source.getDiagnosis().diagnosis;
        this.status = source.getStatus().toString();
        this.remark = source.getRemark().toString();
    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted doctor.
     */
    public Patient toModelType() throws IllegalValueException {
        if (height == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName()));
        }
        if (!Height.isValidHeight(height)) {
            throw new IllegalValueException(Height.MESSAGE_CONSTRAINTS);
        }
        final Height modelHeight = new Height(height);

        if (weight == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName()));
        }
        if (!Weight.isValidWeight(weight)) {
            throw new IllegalValueException(Weight.MESSAGE_CONSTRAINTS);
        }
        final Weight modelWeight = new Weight(weight);

        if (diagnosis == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Diagnosis.class.getSimpleName()));
        }
        if (!Diagnosis.isValidDiagnosis(diagnosis)) {
            throw new IllegalValueException(Diagnosis.MESSAGE_CONSTRAINTS);
        }
        final Diagnosis modelDiagnosis = new Diagnosis(diagnosis);

        if (status == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);

        if (remark == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        if (!Remark.isValidRemark(remark)) {
            throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
        }
        final Remark modelRemark = new Remark(remark);

        Person patientPerson = super.toModelType();
        return new Patient(patientPerson.getName(), patientPerson.getPhone(),
                patientPerson.getEmail(), modelHeight, modelWeight,
                modelDiagnosis, modelStatus, modelRemark, patientPerson.getTags());
    }

}

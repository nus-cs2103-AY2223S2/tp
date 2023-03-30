package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.prescription.Cost;
import seedu.address.model.prescription.Medication;
import seedu.address.model.prescription.Prescription;

/**
 * Jackson-friendly version of {@link Prescription}.
 */
public class JsonAdaptedPrescription {

    private final String medication;
    private final String cost;


    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedPrescription(
            @JsonProperty("medication") String medicationName,
            @JsonProperty("cost") String costName) {
        this.medication = medicationName;
        this.cost = costName;
    }

    /**
     * Converts a given {@code Prescription} into this class for Jackson use.
     */
    public JsonAdaptedPrescription(Prescription source) {
        medication = source.medication.toString();
        cost = source.cost.toString();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Prescription} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Prescription toModelType() throws IllegalValueException {

        if (!Medication.isValidMedication(medication)) {
            throw new IllegalValueException(Medication.MESSAGE_CONSTRAINTS);
        }

        if (!Cost.isValidCost(cost)) {
            throw new IllegalValueException(Cost.MESSAGE_CONSTRAINTS);
        }

        return new Prescription(new Medication(medication), new Cost(cost));
    }

}

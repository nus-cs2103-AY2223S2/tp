package seedu.fitbook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.fitbook.commons.exceptions.IllegalValueException;
import seedu.fitbook.model.client.Weight;

/**
 * Jackson-friendly version of {@link Weight}.
 */
class JsonAdaptedWeightHistory {

    private final String weightName;

    /**
     * Constructs a {@code JsonAdaptedWeightHistory} with the given {@code weightName}.
     */
    @JsonCreator
    public JsonAdaptedWeightHistory(String weightName) {
        this.weightName = weightName;
    }

    /**
     * Converts a given {@code Weight} into this class for Jackson use.
     */
    public JsonAdaptedWeightHistory(Weight source) {
        weightName = source.value;
    }

    @JsonValue
    public String getWeightName() {
        return weightName;
    }

    /**
     * Converts this Jackson-friendly adapted weight object into the model's String object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted string weight.
     */
    public String toFitBookModelType() throws IllegalValueException {
        if (!Weight.isValidWeight(weightName)) {
            throw new IllegalValueException(Weight.MESSAGE_CONSTRAINTS);
        }
        return weightName;
    }

}

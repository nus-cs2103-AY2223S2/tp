package seedu.recipe.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.Step;

/**
 * Jackson-friendly version of {@link Step}.
 */
class JsonAdaptedStep {

    private final String step;

    /**
     * Constructs a {@code JsonAdaptedStep} with the given {@code stepName}.
     */
    @JsonCreator
    public JsonAdaptedStep(String step) {
        this.step = step;
    }

    /**
     * Converts a given {@code Step} into this class for Jackson use.
     */
    public JsonAdaptedStep(Step source) {
        step = source.step;
    }

    @JsonValue
    public String getStep() {
        return step;
    }

    /**
     * Converts this Jackson-friendly adapted step object into the model's {@code Step} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted step.
     */
    public Step toModelType() throws IllegalValueException {
        if (!Step.isValidStep(step)) {
            throw new IllegalValueException(Step.MESSAGE_CONSTRAINTS);
        }
        return new Step(step);
    }

}

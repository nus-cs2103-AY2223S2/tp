package fasttrack.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import fasttrack.commons.exceptions.IllegalValueException;
import fasttrack.model.Budget;


/**
 * Jackson-friendly version of {@link Budget}.
 */
class JsonAdaptedBudget {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Budget's %s field is missing!";
    private final String amount;

    /**
     * Constructs a {@code JsonAdaptedBudget} with the given budget details.
     */
    @JsonCreator
    public JsonAdaptedBudget(@JsonProperty("amount") String amount) {
        this.amount = amount;
    }

    /**
     * Converts a given {@code Budget} into this class for Jackson use.
     */
    public JsonAdaptedBudget(Budget source) {
        this.amount = source.toString();
    }

    /**
     * Converts this Jackson-friendly adapted budget object into the model's
     * {@code Budget} object.
     * @throws IllegalValueException if there were any data constraints violated
     *     in the adapted budget.
     */
    public Budget toModelType() throws IllegalValueException {

        if (amount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }

        return new Budget(Double.parseDouble(amount));
    }

}

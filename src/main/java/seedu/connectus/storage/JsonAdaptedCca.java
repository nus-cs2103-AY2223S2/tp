package seedu.connectus.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.connectus.commons.exceptions.IllegalValueException;
import seedu.connectus.model.tag.Cca;

/**
 * Jackson-friendly version of {@link Cca}.
 */
class JsonAdaptedCca {

    private final String ccaName;

    /**
     * Constructs a {@code JsonAdaptedCca} with the given {@code ccaName}.
     */
    @JsonCreator
    public JsonAdaptedCca(String ccaName) {
        this.ccaName = ccaName;
    }

    /**
     * Converts a given {@code Cca} into this class for Jackson use.
     */
    public JsonAdaptedCca(Cca source) {
        this.ccaName = source.decoupledCcaName;
    }

    @JsonValue
    public String getCcaName() {
        return ccaName;
    }

    /**
     * Converts this Jackson-friendly adapted CCA object into the model's {@code Cca} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted CCA.
     */
    public Cca toModelType() throws IllegalValueException {
        if (!Cca.isValidCcaName(couple(ccaName))) {
            throw new IllegalValueException(Cca.MESSAGE_CONSTRAINTS);
        }
        return new Cca(couple(ccaName));
    }

    public String couple(String str) {
        String[] arr = str.split("-");
        if (arr.length == 2) {
            return arr[0] + "#" + arr[1];
        } else {
            return arr[0];
        }
    }

}

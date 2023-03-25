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
     * Constructs a {@code JsonAdaptedModule} with the given {@code moduleName}.
     */
    @JsonCreator
    public JsonAdaptedCca(String ccaName) {
        this.ccaName = ccaName;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedCca(Cca source) {
        this.ccaName = source.tagName;
    }

    @JsonValue
    public String getModuleName() {
        return ccaName;
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Cca} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted cca.
     */
    public Cca toModelType() throws IllegalValueException {
        if (!Cca.isValidCcaName(ccaName)) {
            throw new IllegalValueException(Cca.MESSAGE_CONSTRAINTS);
        }
        return new Cca(ccaName);
    }

}

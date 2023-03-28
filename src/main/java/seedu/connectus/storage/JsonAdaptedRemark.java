package seedu.connectus.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.connectus.commons.exceptions.IllegalValueException;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Remark;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedRemark {

    private final String remarkName;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given {@code moduleName}.
     */
    @JsonCreator
    public JsonAdaptedRemark(String remarkName) {
        this.remarkName = remarkName;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedRemark(Remark source) {
        this.remarkName = source.remarkName;
    }

    @JsonValue
    public String getRemarkName() {
        return remarkName;
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Remark toModelType() throws IllegalValueException {
        if (!Remark.isValidRemarkName(remarkName)) {
            throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(remarkName);
    }

}

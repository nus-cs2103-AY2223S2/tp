package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.ModuleTag;

/**
 * Jackson-friendly version of {@link ModuleTag}.
 */
class JsonAdaptedModuleTag {

    private final String moduleTagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedModuleTag(String moduleTagName) {
        this.moduleTagName = moduleTagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedModuleTag(ModuleTag source) {
        moduleTagName = source.moduleTagName;
    }

    @JsonValue
    public String getTagName() {
        return moduleTagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public ModuleTag toModelType() throws IllegalValueException {
        if (!ModuleTag.isValidTagName(moduleTagName)) {
            throw new IllegalValueException(ModuleTag.MESSAGE_CONSTRAINTS);
        }
        return new ModuleTag(moduleTagName);
    }

}


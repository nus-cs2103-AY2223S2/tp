package t154.CLIpboard.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import t154.CLIpboard.commons.exceptions.IllegalValueException;
import t154.CLIpboard.model.student.ModuleCode;

/**
 * Jackson-friendly version of {@link ModuleCode}.
 */
class JsonAdaptedModuleCode {

    private final String moduleCode;

    /**
     * Constructs a {@code JsonAdaptedModuleCode} with the given {@code moduleCode}.
     */
    @JsonCreator
    public JsonAdaptedModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Converts a given {@code ModuleCode} into this class for Jackson use.
     */
    public JsonAdaptedModuleCode(ModuleCode source) {
        moduleCode = source.moduleCode;
    }

    @JsonValue
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Converts this Jackson-friendly adapted module code object into the model's {@code ModuleCode} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module code.
     */
    public ModuleCode toModelType() throws IllegalValueException {
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(moduleCode);
    }

}

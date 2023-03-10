package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.mod.Mod;

/**
 * Jackson-friendly version of {@link Mod}.
 */
class JsonAdaptedMods {

    private final String modName;

    /**
     * Constructs a {@code JsonAdaptedMods} with the given {@code skillName}.
     */
    @JsonCreator
    public JsonAdaptedMods(String skillName) {
        this.modName = skillName;
    }

    /**
     * Converts a given {@code Mod} into this class for Jackson use.
     */
    public JsonAdaptedMods(Mod source) {
        modName = source.getModName();
    }

    @JsonValue
    public String getModName() {
        return modName;
    }

    /**
     * Converts this Jackson-friendly adapted skill object into the model's {@code Mod} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted skill.
     */
    public Mod toModelType() throws IllegalValueException {
        if (!Mod.isValidSkillName(modName)) {
            throw new IllegalValueException(Mod.MESSAGE_CONSTRAINTS);
        }
        return new Mod(modName);
    }
}

package seedu.address.storage.addressbook;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.fields.subfields.NusMod;
import seedu.address.model.person.fields.subfields.Tag;

/**
 * Jackson-friendly version of {@link NusMod}.
 */
public class JsonAdaptedNusMod {

    private final String modName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedNusMod(String modName) {
        this.modName = modName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedNusMod(NusMod source) {
        this.modName = source.name;
    }

    @JsonValue
    public String getModName() {
        return this.modName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public NusMod toModelType() throws IllegalValueException {
        if (!NusMod.isValidModName(this.modName)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new NusMod(this.modName);
    }
}

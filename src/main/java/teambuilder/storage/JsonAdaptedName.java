package teambuilder.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import teambuilder.commons.exceptions.IllegalValueException;
import teambuilder.model.person.Name;

/**
 * Jackson-friendly version of {@link Name}.
 */
public class JsonAdaptedName {
    private final String name;

    /**
     * Constructs a {@code JsonAdaptedName} with the given {@code name}.
     */
    @JsonCreator
    public JsonAdaptedName(String name) {
        this.name = name;
    }

    /**
     * Converts a given {@code name} into this class for Jackson use.
     */
    public JsonAdaptedName(Name source) {
        name = source.toString();
    }

    @JsonValue
    public String getName() {
        return name;
    }

    /**
     * Converts this Jackson-friendly adapted name object into the model's {@code name} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Name toModelType() throws IllegalValueException {
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }
}

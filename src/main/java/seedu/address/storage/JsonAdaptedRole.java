package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.role.Role;

/**
 * Jackson-friendly version of {@link Role}.
 */
class JsonAdaptedRole {

    private final String roleName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedRole(String tagName) {
        this.roleName = tagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedRole(Role source) {
        roleName = source.roleName;
    }

    @JsonValue
    public String getTagName() {
        return roleName;
    }

    /**
     * Converts this Jackson-friendly adapted role object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Role toModelType() throws IllegalValueException {
        if (!Role.isValidRoleName(roleName)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS);
        }
        return new Role(roleName);
    }

}

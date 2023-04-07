package mycelium.mycelium.storage;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mycelium.mycelium.commons.exceptions.IllegalValueException;

/**
 * Represents a generic entity that can be stored in JSON.
 */
public abstract class JsonAdaptedEntity {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "%s's %s field is missing!";
    @JsonIgnore
    private String entityName;

    public JsonAdaptedEntity(String entityName) {
        this.entityName = entityName;
    }

    /**
     * Throws an {@code IllegalValueException} with the given message if the {@code obj} is null.
     *
     * @param obj           the object to be checked.
     * @param attributeName The name of the attribute being checked.
     * @throws IllegalValueException if the given boolean is true.
     */
    protected void nullCheck(Object obj, String attributeName) throws IllegalValueException {
        if (obj == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, entityName, attributeName));
        }
    }

    /**
     * Throws an {@code IllegalValueException} with the given message if the boolean is false.
     *
     * @param check   The boolean to be checked.
     * @param message The error message to be displayed if the check fails.
     * @throws IllegalValueException if the given boolean is true.
     */
    protected void validityCheck(boolean check, String message) throws IllegalValueException {
        if (!check) {
            throw new IllegalValueException(message);
        }
    }
}

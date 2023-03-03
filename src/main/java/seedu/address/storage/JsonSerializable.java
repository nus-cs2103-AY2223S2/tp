package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Indicates that an Entity is serializable
 *
 * @param <T> The type to serialize to.
 */
public interface JsonSerializable<T> {
    T toModelType() throws IllegalValueException;
}

package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FriendlyLink;

/**
 * Indicates that an Entity is serializable.
 *
 * @param <T> The model type to serialize to.
 */
public interface JsonSerializable<T> {
    T toModelType(FriendlyLink friendlyLink) throws IllegalValueException;
}

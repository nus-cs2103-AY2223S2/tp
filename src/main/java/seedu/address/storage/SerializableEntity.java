package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;

public interface SerializableEntity<T> {
    T toModelType() throws IllegalValueException;
}

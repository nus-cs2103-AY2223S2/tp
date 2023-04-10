package seedu.address.storage.json.model;

import seedu.address.commons.exceptions.IllegalValueException;

abstract class JsonAdapted<T> {
    public abstract T toModelType() throws IllegalValueException;
}

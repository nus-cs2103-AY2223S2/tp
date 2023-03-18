package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * A interface of JsonAdapted
 * @param <T>
 */
public interface JsonAdapted<T> {
    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public T toModelType() throws IllegalValueException;
}

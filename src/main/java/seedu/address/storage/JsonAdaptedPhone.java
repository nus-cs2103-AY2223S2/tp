package seedu.address.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Phone;

/**
 * Jackson-friendly version of {@link Phone}.
 */
class JsonAdaptedPhone {
    private final Optional<String> phone;

    /**
     * Constructs a {@code JsonAdaptedAddress} with the given {@code phone}.
     */
    @JsonCreator
    public JsonAdaptedPhone(String phone) {
        if (phone.equals("") || phone == null) {
            this.phone = Optional.empty();
        } else {
            this.phone = Optional.of(phone);
        }
    }

    @JsonCreator
    public JsonAdaptedPhone() {
        phone = Optional.empty();
    }

    /**
     * Converts a given {@code Address} into this class for Jackson use.
     */
    public JsonAdaptedPhone(Phone source) {
        phone = Optional.of(source.getValue());
    }

    @JsonValue
    public String getPhone() {
        if (phone.isPresent()) {
            return phone.get();
        } else {
            return null;
        }
    }

    /**
     * Converts this Jackson-friendly adapted address object into the model's
     * {@code Address} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted address.
     */
    public Optional<Phone> toModelType() throws IllegalValueException {
        if (phone.isPresent()) {
            return Optional.of(new Phone(phone.get()));
        } else {
            return Optional.empty();
        }
    }

}

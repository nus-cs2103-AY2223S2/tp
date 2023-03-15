package seedu.address.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;

/**
 * Jackson-friendly version of {@link Address}.
 */
class JsonAdaptedAddress {
    private final Optional<String> address;

    /**
     * Constructs a {@code JsonAdaptedAddress} with the given {@code address}.
     */
    @JsonCreator
    public JsonAdaptedAddress(String address) {
        if (address.equals("") || address == null) {
            this.address = Optional.empty();
        } else {
            this.address = Optional.of(address);
        }
    }

    @JsonCreator
    public JsonAdaptedAddress() {
        address = Optional.empty();
    }

    /**
     * Converts a given {@code Address} into this class for Jackson use.
     */
    public JsonAdaptedAddress(Address source) {
        address = Optional.of(source.getValue());
    }

    @JsonValue
    public String getAddress() {
        if (address.isPresent()) {
            return address.get();
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
    public Optional<Address> toModelType() throws IllegalValueException {
        if (address.isPresent()) {
            return Optional.of(new Address(address.get()));
        } else {
            return Optional.empty();
        }
    }

}

package seedu.address.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;

/**
 * Jackson-friendly version of {@link Email}.
 */
class JsonAdaptedEmail {
    private final Optional<String> email;

    /**
     * Constructs a {@code JsonAdaptedAddress} with the given {@code email}.
     */
    @JsonCreator
    public JsonAdaptedEmail(String email) {
        if (email.equals("") || email == null) {
            this.email = Optional.empty();
        } else {
            this.email = Optional.of(email);
        }
    }

    @JsonCreator
    public JsonAdaptedEmail() {
        email = Optional.empty();
    }

    /**
     * Converts a given {@code Address} into this class for Jackson use.
     */
    public JsonAdaptedEmail(Email source) {
        email = Optional.of(source.getValue());
    }

    @JsonValue
    public String getEmail() {
        if (email.isPresent()) {
            return email.get();
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
    public Optional<Email> toModelType() throws IllegalValueException {
        if (email.isPresent()) {
            return Optional.of(new Email(email.get()));
        } else {
            return Optional.empty();
        }
    }

}

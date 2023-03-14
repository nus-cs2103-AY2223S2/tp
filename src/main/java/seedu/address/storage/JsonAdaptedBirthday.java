package seedu.address.storage;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Birthday;

/**
 * Jackson-friendly version of {@link Birthday}.
 */
class JsonAdaptedBirthday {
    private final Optional<Birthday> birthday;

    /**
     * Constructs a {@code JsonAdaptedBirthday} with the given {@code birthday}.
     */
    @JsonCreator
    public JsonAdaptedBirthday(String birthday) throws IllegalValueException {
        if (birthday.equals("") || birthday == null) {
            this.birthday = Optional.empty();
        } else {
            this.birthday = Optional.of(new Birthday(birthday));
        }
    }

    @JsonCreator
    public JsonAdaptedBirthday() {
        birthday = Optional.empty();
    }

    /**
     * Converts a given {@code Birthday} into this class for Jackson use.
     */
    public JsonAdaptedBirthday(Birthday source) {
        birthday = Optional.of(source);
    }

    @JsonValue
    public String getBirthday() {
        if (birthday.isPresent()) {
            return birthday.get().getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            return null;
        }
    }

    /**
     * Converts this Jackson-friendly adapted birthday object into the model's
     * {@code Birthday} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted birthday.
     */
    public Optional<Birthday> toModelType() throws IllegalValueException {
        if (birthday.isPresent()) {
            return Optional.of(new Birthday(birthday.get().getValue()));
        } else {
            return Optional.empty();
        }
    }

}

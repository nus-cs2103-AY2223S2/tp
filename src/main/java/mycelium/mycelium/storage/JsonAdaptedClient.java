package mycelium.mycelium.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import mycelium.mycelium.commons.exceptions.IllegalValueException;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.person.Name;
import mycelium.mycelium.model.person.Phone;

/**
 * Jackson-friendly version of {@link Client}. This class is used to convert a {@code Client} object into a
 * JSON-friendly format for serialization and deserialization using Jackson library. It contains methods for
 * converting a {@code Client} object to this class for serialization, and for converting this class back to a
 * {@code Client} object for deserialization. It also contains methods for null and validity checks of the adapted
 * client object's fields.
 */
class JsonAdaptedClient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Client's %s field is missing!";

    private final String name;
    private final String email;
    private final String yearOfBirth;
    private final String source;
    private final String mobileNumber;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedClient(@JsonProperty("name") String name, @JsonProperty("email") String email,
                             @JsonProperty("year_of_birth") String yearOfBirth,
                             @JsonProperty("source") String source,
                             @JsonProperty("mobile_number") String mobileNumber) {
        this.name = name;
        this.email = email;
        this.yearOfBirth = yearOfBirth;
        this.source = source;
        this.mobileNumber = mobileNumber;
    }

    /**
     * Converts a given {@code Client} into this class for Jackson use.
     */
    public JsonAdaptedClient(Client client) {
        name = client.getName().fullName;
        email = client.getEmail().value;
        yearOfBirth = client.getYearOfBirth().orElse(null).value;
        source = client.getSource().orElse(null);
        mobileNumber = client.getMobileNumber().orElse(null).value;
    }

    /**
     * Throws an {@code IllegalValueException} with the given message if the given boolean is true.
     *
     * @param check         The boolean to be checked.
     * @param attributeName The name of the attribute being checked.
     * @throws IllegalValueException if the given boolean is true.
     */
    public void nullCheck(boolean check, String attributeName) throws IllegalValueException {
        if (check) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, attributeName));
        }
    }

    /**
     * Throws an {@code IllegalValueException} with the given message if the boolean is true.
     *
     * @param check   The boolean to be checked.
     * @param message The error message to be displayed if the check fails.
     * @throws IllegalValueException if the given boolean is true.
     */
    public void validityCheck(boolean check, String message) throws IllegalValueException {
        if (check) {
            throw new IllegalValueException(message);
        }
    }

    /**
     * Converts this Jackson-friendly adapted client object into the model's {@code Client} object.
     *
     * @return the model's {@code Client} object.
     * @throws IllegalValueException if there were any data constraints violated in the adapted client.
     */
    public Client toModelType() throws IllegalValueException {
        nullCheck(name == null, Name.class.getSimpleName());
        validityCheck(!Name.isValidName(name), Name.MESSAGE_CONSTRAINTS);
        final Name modelName = new Name(name);

        nullCheck(email == null, Email.class.getSimpleName());
        validityCheck(!Email.isValidEmail(email), Email.MESSAGE_CONSTRAINTS);
        final Email modelEmail = new Email(email);
        if (yearOfBirth != null) {
            validityCheck(!YearOfBirth.isValidYearOfBirth(yearOfBirth), YearOfBirth.MESSAGE_CONSTRAINTS);
        }
        final YearOfBirth modelYearOfBirth = new YearOfBirth(yearOfBirth);
        // TODO validityCheck for source
        if (mobileNumber != null) {
            validityCheck(!Phone.isValidPhone(mobileNumber), Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelMobileNumber = new Phone(mobileNumber);
        return new Client(modelName,
            modelEmail,
            Optional.ofNullable(modelYearOfBirth),
            Optional.ofNullable(source),
            Optional.ofNullable(modelMobileNumber));
    }


}

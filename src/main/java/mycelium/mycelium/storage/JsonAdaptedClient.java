package mycelium.mycelium.storage;

import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import mycelium.mycelium.commons.exceptions.IllegalValueException;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.client.Name;
import mycelium.mycelium.model.client.Phone;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.util.NonEmptyString;

/**
 * Jackson-friendly version of {@link Client}. This class is used to convert a {@code Client} object into a
 * JSON-friendly format for serialization and deserialization using Jackson library. It contains methods for
 * converting a {@code Client} object to this class for serialization, and for converting this class back to a
 * {@code Client} object for deserialization. It also contains methods for null and validity checks of the adapted
 * client object's fields.
 */
class JsonAdaptedClient extends JsonAdaptedEntity {
    private static final String ENTITY_NAME = "Client";
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
        super(ENTITY_NAME);
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
        super(ENTITY_NAME);
        name = client.getName().fullName;
        email = client.getEmail().value;
        yearOfBirth = client.getYearOfBirth().map(x -> x.value).orElse(null);
        source = client.getSource().map(NonEmptyString::toString).orElse(null);
        mobileNumber = client.getMobileNumber().map(x -> x.value).orElse(null);
    }

    /**
     * Converts this Jackson-friendly adapted client object into the model's {@code Client} object.
     *
     * @return the model's {@code Client} object.
     * @throws IllegalValueException if there were any data constraints violated in the adapted client.
     */
    public Client toModelType() throws IllegalValueException {
        nullCheck(name, Name.class.getSimpleName());
        validityCheck(Name.isValidName(name), Name.MESSAGE_CONSTRAINTS);
        final Name modelName = new Name(name);

        nullCheck(email, Email.class.getSimpleName());
        validityCheck(Email.isValidEmail(email), Email.MESSAGE_CONSTRAINTS);
        final Email modelEmail = new Email(email);

        if (yearOfBirth != null) {
            validityCheck(YearOfBirth.isValidYearOfBirth(yearOfBirth), YearOfBirth.MESSAGE_CONSTRAINTS);
        }
        if (mobileNumber != null) {
            validityCheck(Phone.isValidPhone(mobileNumber), Phone.MESSAGE_CONSTRAINTS);
        }

        return new Client(modelName,
            modelEmail,
            Optional.ofNullable(yearOfBirth).map(YearOfBirth::new),
            NonEmptyString.ofOptional(source),
            Optional.ofNullable(mobileNumber).map(Phone::new));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JsonAdaptedClient that = (JsonAdaptedClient) o;
        return Objects.equals(name, that.name)
            && Objects.equals(email, that.email)
            && Objects.equals(yearOfBirth, that.yearOfBirth)
            && Objects.equals(source, that.source)
            && Objects.equals(mobileNumber, that.mobileNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, yearOfBirth, source, mobileNumber);
    }
}

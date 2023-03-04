package seedu.address.storage.volunteer;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.storage.JsonAdaptedPerson;
import seedu.address.storage.JsonAdaptedTag;
import seedu.address.storage.JsonSerializable;

/**
 * Jackson-friendly version of {@link Volunteer}.
 */
public class JsonAdaptedVolunteer extends JsonAdaptedPerson implements JsonSerializable<Volunteer> {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Volunteer's %s field is missing!";
    private final String nric;
    private final String age;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedVolunteer(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("nric") String nric, @JsonProperty("age") String age,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {

        super(name, phone, email, address, tagged);
        this.nric = nric;
        this.age = age;
    }

    /**
     * Converts a given {@code Volunteer} into this class for Jackson use.
     */
    public JsonAdaptedVolunteer(Volunteer source) {
        super(source);
        nric = source.getNric().value;
        age = source.getAge().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Volunteer toModelType(FriendlyLink friendlyLink) throws IllegalValueException {
        Name modelName = super.getModelName();
        Phone modelPhone = super.getModelPhone();
        Email modelEmail = super.getModelEmail();
        Address modelAddress = super.getModelAddress();
        Set<Tag> modelTags = super.getTagSet(friendlyLink);

        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName()));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Age.MESSAGE_CONSTRAINTS);
        }

        return new Volunteer(modelName, modelPhone, modelEmail, modelAddress,
                new Nric(nric), new Age(age), modelTags);
    }
}

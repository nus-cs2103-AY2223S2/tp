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
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.storage.JsonAdaptedAvailableDate;
import seedu.address.storage.JsonAdaptedPerson;
import seedu.address.storage.JsonAdaptedTag;
import seedu.address.storage.JsonSerializable;

/**
 * Jackson-friendly version of {@link Volunteer}.
 */
public class JsonAdaptedVolunteer extends JsonAdaptedPerson implements JsonSerializable<Volunteer> {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Volunteer's %s field is missing!";

    /**
     * Constructs a {@code JsonAdaptedVolunteer} with the given volunteer details.
     */
    @JsonCreator
    public JsonAdaptedVolunteer(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("nric") String nric, @JsonProperty("age") String age,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("availableDates") List<JsonAdaptedAvailableDate> dates) {

        super(name, phone, email, address, nric, age, tagged, dates);
    }

    /**
     * Converts a given {@code Volunteer} into this class for Jackson use.
     */
    public JsonAdaptedVolunteer(Volunteer source) {
        super(source);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Volunteer toModelType(FriendlyLink friendlyLink) throws IllegalValueException {
        Name modelName = super.getModelName(MISSING_FIELD_MESSAGE_FORMAT);
        Phone modelPhone = super.getModelPhone(MISSING_FIELD_MESSAGE_FORMAT);
        Email modelEmail = super.getModelEmail(MISSING_FIELD_MESSAGE_FORMAT);
        Address modelAddress = super.getModelAddress(MISSING_FIELD_MESSAGE_FORMAT);
        Set<Tag> modelTags = super.getTagSet(friendlyLink);
        Nric modelNric = super.getModelNric(MISSING_FIELD_MESSAGE_FORMAT);
        Age modelAge = super.getModelAge(MISSING_FIELD_MESSAGE_FORMAT);
        Set<AvailableDate> modelAvailableDates = super.getAvailableDateSet();

        return new Volunteer(modelName, modelPhone, modelEmail, modelAddress,
                modelNric, modelAge, modelTags, modelAvailableDates);
    }
}

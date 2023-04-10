package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Station;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.person.User;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;


/**
 * Jackson-friendly version of {@link User}.
 */
public class JsonAdaptedUser extends JsonAdaptedPerson {

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedUser(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("station") String station,
                             @JsonProperty("telegramHandle") String telegramHandle,
                             @JsonProperty("index") Integer index,
                             @JsonProperty("groups") List<JsonAdaptedGroupTag> tagged,
                             @JsonProperty("modules") List<JsonAdaptedModuleTag> modules) {
        super(name, phone, email, station, telegramHandle, index, tagged, modules);
    }

    /**
     * Converts a given {@code User} into this class for Jackson use.
     */
    public JsonAdaptedUser(User source) {
        super(source);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public User toModelType() throws IllegalValueException {
        final List<GroupTag> userGroupTags = new ArrayList<>();
        for (JsonAdaptedGroupTag tag : groups) {
            userGroupTags.add(tag.toModelType());
        }

        final List<ModuleTag> userModuleTags = new ArrayList<>();
        for (JsonAdaptedModuleTag tag : modules) {
            userModuleTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (station == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Station.class.getSimpleName()));
        }
        if (!Station.isValidStation(station)) {
            throw new IllegalValueException(Station.MESSAGE_CONSTRAINTS);
        }
        final Station modelStation = new Station(station);

        if (telegramHandle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TelegramHandle.class.getSimpleName()));
        }
        if (!TelegramHandle.isValidTelegramHandle(telegramHandle)) {
            throw new IllegalValueException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        final TelegramHandle modelTelegramHandle = new TelegramHandle(telegramHandle);

        if (index == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ContactIndex.class.getSimpleName()));
        }
        final ContactIndex modelContactIndex = new ContactIndex(index);
        final Set<GroupTag> modelGroupTags = new HashSet<>(userGroupTags);
        final Set<ModuleTag> modelModuleTags = new HashSet<>(userModuleTags);
        return new User(modelName, modelPhone, modelEmail,
                modelStation, modelTelegramHandle, modelContactIndex,
                modelGroupTags, modelModuleTags);
    }
}

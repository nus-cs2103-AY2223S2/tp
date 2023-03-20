package seedu.address.storage.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Event;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Favorite;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.subfields.NusMod;
import seedu.address.model.person.fields.subfields.Tag;
import seedu.address.model.user.User;
import seedu.address.storage.addressbook.JsonAdaptedNusMod;
import seedu.address.storage.addressbook.JsonAdaptedPerson;
import seedu.address.storage.addressbook.JsonAdaptedTag;

/**
 * Jackson-friendly version of {@link User}.
 */
public class JsonAdaptedUser extends JsonAdaptedPerson {

    private final List<JsonAdaptedEvent> events = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedUser(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("race") String race,
                             @JsonProperty("major") String major, @JsonProperty("gender") String gender,
                             @JsonProperty("comms") String comms,
                             @JsonProperty("modules") List<JsonAdaptedNusMod> modules,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("favorite") String isFavorite,
                           @JsonProperty("events") List<JsonAdaptedEvent> events) {
        super(name, phone, email, address, race, major, gender, comms, modules, tagged, isFavorite);
        if (events != null) {
            this.events.addAll(events);
        }
    }

    /**
     * Converts a given {@code User} into this class for Jackson use.
     */
    public JsonAdaptedUser(User source) {
        super(source);
        if (source.getEvents() != null) {
            this.events.addAll(
                    source.getEvents().asUnmodifiableObservableList().stream()
                    .map(JsonAdaptedEvent::new).collect(Collectors.toList()));
        }
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    @Override
    public User toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();

        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (!Gender.isValidGender(this.gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(this.gender);

        if (!Major.isValidMajor(this.major)) {
            throw new IllegalValueException(Major.MESSAGE_CONSTRAINTS);
        }
        final Major modelMajor = new Major(this.major);

        if (!Race.isValidRace(address)) {
            throw new IllegalValueException(Race.MESSAGE_CONSTRAINTS);
        }
        final Race modelRace = new Race(this.race);

        if (!Favorite.isValidFavorite(isFavorite)) {
            throw new IllegalValueException(Favorite.MESSAGE_CONSTRAINTS);
        }
        final Favorite modelFavoriteStatus = new Favorite(this.isFavorite);

        Set<NusMod> personMods = new HashSet<>();
        for (JsonAdaptedNusMod mod: this.modules) {
            personMods.add(mod.toModelType());
        }
        Modules modelModules = new Modules(personMods);

        List<Event> modelUserEvents = new ArrayList<>();

        for (JsonAdaptedEvent event: this.events) {
            modelUserEvents.add(event.toModelType());
        }

        CommunicationChannel modelComms = new CommunicationChannel(this.comms);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new User(modelName, modelPhone, modelEmail, modelAddress, modelGender, modelMajor,
                modelModules, modelRace, modelTags, modelComms, modelFavoriteStatus, modelUserEvents);
    }
}

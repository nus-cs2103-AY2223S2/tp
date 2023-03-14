package seedu.address.storage.addressbook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Favorite;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.NusMod;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String race;
    private final String major;
    private final String gender;
    private final String comms;

    private final String isFavorite;

    private final List<JsonAdaptedNusMod> modules = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("race") String race,
                             @JsonProperty("major") String major, @JsonProperty("gender") String gender,
                             @JsonProperty("comms") String comms,
                             @JsonProperty("modules") List<JsonAdaptedNusMod> modules,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("favorite") String isFavorite) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.race = race;
        this.major = major;

        this.gender = gender;
        this.comms = comms;
        this.isFavorite = isFavorite;

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (modules != null) {
            this.modules.addAll(modules);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        this.name = source.getName().fullName;
        this.phone = source.getPhone().value;
        this.email = source.getEmail().value;
        this.address = source.getAddress().value;
        this.race = source.getRace().race;
        this.major = source.getMajor().majorName;
        this.gender = source.getGender().gender.toString();
        this.comms = source.getComms().nameOfCommunicationChannel;
        this.modules.addAll(source.getModules().mods.stream()
                .map(mod -> (new JsonAdaptedNusMod(mod.name)))
                .collect(Collectors.toList()));
        this.tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        this.isFavorite = source.getIsFavorite().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
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

        //if (phone == null) {
        //throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        //}
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        //if (email == null) {
        //throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        //}
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        //if (address == null) {
        //throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        //}
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

        if (!Race.isValidRace(this.race)) {
            throw new IllegalValueException(Race.MESSAGE_CONSTRAINTS);
        }
        final Race modelRace = new Race(this.race);

        if (!Favorite.isValidFavorite(isFavorite)) {
            throw new IllegalValueException(Favorite.MESSAGE_CONSTRAINTS);
        }
        final Favorite favoriteStatus = new Favorite(this.isFavorite);

        Set<NusMod> personMods = new HashSet<>();
        for (JsonAdaptedNusMod mod: this.modules) {
            personMods.add(mod.toModelType());
        }
        Modules modelModules = new Modules(personMods);

        CommunicationChannel modelComms = new CommunicationChannel(this.comms);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelGender, modelMajor,
                modelModules, modelRace, modelTags, modelComms, favoriteStatus);
    }

}

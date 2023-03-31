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
import seedu.address.model.person.fields.Faculty;
import seedu.address.model.person.fields.Favorite;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.Tags;
import seedu.address.model.person.fields.subfields.NusMod;
import seedu.address.model.person.fields.subfields.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
public class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    protected String name;
    protected String phone;
    protected String email;
    protected String address;
    protected String race;
    protected String major;
    protected String gender;
    protected String comms;
    protected String faculty;

    protected String isFavorite;

    protected final List<JsonAdaptedNusMod> modules = new ArrayList<>();
    protected final List<JsonAdaptedTag> tagged = new ArrayList<>();

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
                             @JsonProperty("favorite") String isFavorite,
                             @JsonProperty("faculty") String faculty) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.race = race;
        this.major = major;

        this.gender = gender;
        this.comms = comms;
        this.isFavorite = isFavorite;
        this.faculty = faculty;

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
        this.name = source.getName().value;
        this.phone = source.getPhone().value;
        this.email = source.getEmail().value;
        this.address = source.getAddress().value;
        this.race = source.getRace().value;
        this.major = source.getMajor().value;
        this.gender = source.getGender().value;
        this.comms = source.getComms().value;
        this.modules.addAll(source.getModules().values.stream()
                .map(mod -> (new JsonAdaptedNusMod(mod.value)))
                .collect(Collectors.toList()));
        this.tagged.addAll(source.getSetOfTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        this.isFavorite = source.getFavorite().toString();
        this.faculty = source.getFaculty().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
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

        if (!Race.isValidRace(this.race)) {
            throw new IllegalValueException(Race.MESSAGE_CONSTRAINTS);
        }
        final Race modelRace = new Race(this.race);

        if (!Favorite.isValidFavorite(isFavorite)) {
            throw new IllegalValueException(Favorite.MESSAGE_CONSTRAINTS);
        }
        final Favorite favoriteStatus = new Favorite(this.isFavorite);

        final Set<NusMod> personMods = new HashSet<>();
        for (JsonAdaptedNusMod mod: this.modules) {
            personMods.add(mod.toModelType());
        }
        final Modules modelModules = new Modules(personMods);

        if (!CommunicationChannel.isValidComms(this.comms)) {
            throw new IllegalValueException(CommunicationChannel.MESSAGE_CONSTRAINTS);
        }
        final CommunicationChannel modelComms = new CommunicationChannel(this.comms);

        if (!Faculty.isValidFaculty(this.faculty)) {
            throw new IllegalValueException(Faculty.MESSAGE_CONSTRAINTS);
        }
        final Faculty faculty = new Faculty(this.faculty);

        final Set<Tag> personTags = new HashSet<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        final Tags modelTags = new Tags(personTags);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelGender, modelMajor,
                modelModules, modelRace, modelTags, modelComms, favoriteStatus, faculty);
    }

}

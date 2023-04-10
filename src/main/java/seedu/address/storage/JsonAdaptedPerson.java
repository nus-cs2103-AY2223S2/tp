package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Station;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    protected final String name;
    protected final String phone;
    protected final String email;
    protected final String station;
    protected final String telegramHandle;
    protected final Integer index;
    protected final List<JsonAdaptedGroupTag> groups = new ArrayList<>();
    protected final List<JsonAdaptedModuleTag> modules = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("station") String station,
            @JsonProperty("telegramHandle") String telegramHandle,
            @JsonProperty("index") Integer index,
            @JsonProperty("groups") List<JsonAdaptedGroupTag> groups,
            @JsonProperty("modules") List<JsonAdaptedModuleTag> modules) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.station = station;
        this.telegramHandle = telegramHandle;
        this.index = index;
        if (groups != null) {
            this.groups.addAll(groups);
        }
        if (modules != null) {
            this.modules.addAll(modules);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().getValue();
        phone = source.getPhone().getValue();
        email = source.getEmail().getValue();
        station = source.getStation().getValue().getName();
        telegramHandle = source.getTelegramHandle().getValue();
        index = source.getContactIndex().getValue();
        groups.addAll(source.getImmutableGroupTags().stream()
                .map(JsonAdaptedGroupTag::new)
                .collect(Collectors.toList()));
        modules.addAll(source.getImmutableModuleTags().stream()
                .map(JsonAdaptedModuleTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<GroupTag> personGroupTags = new ArrayList<>();
        for (JsonAdaptedGroupTag tag : groups) {
            personGroupTags.add(tag.toModelType());
        }
        final List<ModuleTag> personModuleTags = new ArrayList<>();
        for (JsonAdaptedModuleTag tag : modules) {
            personModuleTags.add(tag.toModelType());
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
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (station == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Station.class.getSimpleName()));
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

        if (index == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ContactIndex.class.getSimpleName()));
        }
        final ContactIndex modelContactIndex = new ContactIndex(index);
        final TelegramHandle modelTelegramHandle = new TelegramHandle(telegramHandle);
        final Set<GroupTag> modelGroupTags = new HashSet<>(personGroupTags);
        final Set<ModuleTag> modelModuleTags = new HashSet<>(personModuleTags);
        return new Person(modelName, modelPhone, modelEmail, modelStation,
                modelTelegramHandle, modelContactIndex, modelGroupTags, modelModuleTags);
    }

}

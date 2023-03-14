package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.PayRate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Session;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String address;
    private final String payRate;
    private final String startDateTime;
    private final String endDateTime;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("address") String address, @JsonProperty("payRate") String payRate, @JsonProperty("startDateTime") String startDateTime
            , @JsonProperty("endDateTIME") String endDateTime, @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.payRate = payRate;
        this.startDateTime = startDateTime;
        this.endDateTime = JsonAdaptedPerson.this.endDateTime;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        address = source.getAddress().value;
        payRate = source.getPayRate().value;
        startDateTime = LocalDateTime.parse(source.getSession().getStartDateTime(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).format(Session.DATE_TIME_FORMATTER);
        endDateTime = LocalDateTime.parse(source.getSession().getEndDateTime(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).format(Session.DATE_TIME_FORMATTER);
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
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

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (payRate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, PayRate.class.getSimpleName()));
        }
        if (!PayRate.isValidPayRate(payRate)) {
            throw new IllegalValueException(PayRate.MESSAGE_CONSTRAINTS);
        }
        final PayRate modelPayRate = new PayRate(payRate);

        if (startDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Session.class.getSimpleName()));
        }
        if (!Session.isValidDateTimeFormat(startDateTime)) {
            throw new IllegalValueException(Session.MESSAGE_CONSTRAINTS);
        }

        if (endDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Session.class.getSimpleName()));
        }
        if (!Session.isValidDateTimeFormat(endDateTime)) {
            throw new IllegalValueException(Session.MESSAGE_CONSTRAINTS);
        }
        final Session modelSession = new Session(startDateTime, endDateTime);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelAddress, modelPayRate, modelSession, modelTags);
    }


}

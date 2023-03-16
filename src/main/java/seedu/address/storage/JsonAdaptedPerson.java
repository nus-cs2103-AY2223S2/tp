package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address; // create a new import group for Address
import seedu.address.model.person.Name;
import seedu.address.model.person.PayRate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Session;
import seedu.address.model.tag.Tag;





class JsonAdaptedPerson {

    static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    private final String name;
    private final String phone;
    private final String address;
    private final String payRate;
    private final String sessionString; // new field
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("address") String address, @JsonProperty("payRate") String payRate,
                             @JsonProperty("sessionString") String sessionString,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.payRate = payRate;
        this.sessionString = sessionString;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        address = source.getAddress().value;
        payRate = source.getPayRate().value;
        sessionString = source.getSession().toCommandString(); // convert session to string
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

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

        if (sessionString == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Session.class.getSimpleName()));
        }
        final Session modelSession = new Session(sessionString); // create session from string

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelAddress, modelPayRate, modelSession, modelTags);
    }

}

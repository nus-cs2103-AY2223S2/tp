package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.MedicalCondition;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
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
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private String medicalCondition;
    private String age;
    private LocalDateTime time = null;
    private String appointment;
    private String nric;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */

    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged, @JsonProperty("time") String time,
                             @JsonProperty("age") String age,
                             @JsonProperty("MedicalCondition") String medicalCondition,
                             @JsonProperty("Appointment") String appointment, @JsonProperty("NRIC") String nric) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;

        if (age != null) {
            this.age = age;
        } else {
            this.age = "";
        }

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (time != null) {
            this.time = LocalDateTime.parse(time);
        }
        if (medicalCondition != null) {
            this.medicalCondition = medicalCondition;
        } else {
            this.medicalCondition = "";
        }
        if (appointment != null) {
            this.appointment = appointment;
        }
        if (nric != null) {
            this.nric = nric;
        } else {
            this.nric = "";
        }
    }

    public JsonAdaptedPerson(String name, String phone, String email, String address, List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    public JsonAdaptedPerson(String name, String phone, String email, String address,
                             String age, List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.age = age;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    public JsonAdaptedPerson(String name, String phone, String email, String address,
                             String age, List<JsonAdaptedTag> tagged, String medicalCondition) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.age = age;
        this.medicalCondition = medicalCondition;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    public JsonAdaptedPerson(String name, String phone, String email, String address,
                             String age, String time, List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.age = age;
        this.time = LocalDateTime.parse(time);
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
        email = source.getEmail().value;
        address = source.getAddress().value;
        if (source.hasTime()) {
            time = source.getTime();
        }
        if (source.getAge() != null) {
            age = source.getAge().getAge();
        }
        if (source.getMedicalCondition() != null) {
            medicalCondition = source.getMedicalCondition().getValue();
        }
        if (source.hasAppointment()) {
            appointment = source.getAppointment().saveToAddressbook();
        }
        if (source.getNric() != null) {
            nric = source.getNric().getNumber();
        }
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

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);


        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName()));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Age.MESSAGE_CONSTRAINTS);
        }

        if (medicalCondition == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MedicalCondition.class.getSimpleName()));
        }
        if (!MedicalCondition.isValidCondition(medicalCondition)) {
            throw new IllegalValueException(MedicalCondition.MESSAGE_CONSTRAINTS);
        }
        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNumber(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        Optional<LocalDateTime> optionalTime = Optional.ofNullable(time);
        Optional<String> optionalAge = Optional.ofNullable(age);
        Optional<String> optionalMedicalCond = Optional.ofNullable(medicalCondition);
        Optional<String> optionalAppointment = Optional.ofNullable(appointment);
        Optional<String> optionalNric = Optional.ofNullable(nric);
        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Age modelAge = optionalAge != null && optionalAge.isPresent() ? new Age(optionalAge.get()) : new Age("");
        final MedicalCondition modelMedical = optionalMedicalCond != null && optionalMedicalCond.isPresent()
                ? new MedicalCondition(optionalMedicalCond.get()) : new MedicalCondition("");
        final Nric modelNric = optionalNric != null && optionalNric.isPresent()
                ? new Nric(optionalNric.get()) : new Nric("");
        final LocalDateTime modelTime = optionalTime != null && optionalTime.isPresent()
                ? optionalTime.get() : null;
        final Appointment modelAppointment = optionalAppointment != null && optionalAppointment.isPresent()
                ? ParserUtil.parseTimeFromAddressbook(appointment) : null;
        if (modelAppointment == null) {
            if (modelTime == null) {
                return new Person(modelName, modelPhone, modelEmail, modelAddress, modelAge, modelTags, modelMedical,
                        modelNric);
            } else {
                return new Person(modelName, modelPhone, modelEmail, modelAddress,
                        modelAge, modelTags, modelTime, modelMedical, modelNric);
            }
        } else {
            if (modelTime == null) {
                return new Person(modelName, modelPhone, modelEmail, modelAddress,
                        modelAge, modelTags, modelMedical, modelAppointment, modelNric);
            } else {
                return new Person(modelName, modelPhone, modelEmail, modelAddress,
                        modelAge, modelTags, time, modelMedical, modelAppointment, modelNric);
            }
        }
    }
}

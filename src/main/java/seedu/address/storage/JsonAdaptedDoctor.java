package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Specialty;
import seedu.address.model.person.Yoe;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Doctor}.
 */
class JsonAdaptedDoctor {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Doctor's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String specialty;
    private final String yearsOfExperience;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedDoctor(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("specialty") String specialty,
                             @JsonProperty("yearsOfExperience") String yearsOfExperience) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.specialty = specialty;
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * Converts a given {@code Doctor} into this class for Jackson use.
     */
    public JsonAdaptedDoctor(Doctor source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        yearsOfExperience = source.getYoe().value;
        specialty = source.getSpecialty().specialty;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Doctor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Doctor toModelType() throws IllegalValueException {
        final List<Tag> doctorTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            doctorTags.add(tag.toModelType());
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

        final Set<Tag> modelTags = new HashSet<>(doctorTags);

        if (specialty == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Specialty.class.getSimpleName()));
        }
        if (!Specialty.isValidSpecialty(specialty)) {
            throw new IllegalValueException(Specialty.MESSAGE_CONSTRAINTS);
        }
        final Specialty modelSpecialty = new Specialty(specialty);

        if (yearsOfExperience == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Yoe.class.getSimpleName()));
        }
        if (!Yoe.isValidYoe(yearsOfExperience)) {
            throw new IllegalValueException(Yoe.MESSAGE_CONSTRAINTS);
        }
        final Yoe modelYearsOfExperience = new Yoe(yearsOfExperience);

        return new Doctor(modelName, modelPhone, modelEmail,
                modelSpecialty, modelYearsOfExperience, modelTags);
    }

}

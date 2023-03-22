package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Education;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Subject;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
@JsonInclude(Include.NON_NULL)
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String education;
    private final String remark;
    private final List<JsonAdaptedSubject> subjects = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("remark") String remark, @JsonProperty("education") String education,
                             @JsonProperty("subjects") List<JsonAdaptedSubject> subjects,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.education = education;
        this.remark = remark;
        if (subjects != null) {
            this.subjects.addAll(subjects);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getOptionalPhone().map(Phone::toString).orElse(null);
        email = source.getOptionalEmail().map(Email::toString).orElse(null);
        address = source.getOptionalAddress().map(Address::toString).orElse(null);
        education = source.getOptionalEducation().map(Education::toString).orElse(null);
        remark = source.getOptionalRemark().map(Remark::toString).orElse(null);
        subjects.addAll(source.getSubjects().stream()
                .map(JsonAdaptedSubject::new)
                .collect(Collectors.toList()));

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
        final List<Subject> personSubjects = new ArrayList<>();
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedSubject subject : subjects) {
            personSubjects.add(subject.toModelType());
        }
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

        final Phone modelPhone = phone == null
            ? null
            : Optional.of(phone)
                    .filter(Phone::isValidPhone)
                    .map(Phone::new)
                    .orElseThrow(() -> new IllegalValueException(Phone.MESSAGE_CONSTRAINTS));

        final Email modelEmail = email == null
            ? null
            : Optional.of(email)
                    .filter(Email::isValidEmail)
                    .map(Email::new)
                    .orElseThrow(() -> new IllegalValueException(Email.MESSAGE_CONSTRAINTS));


        final Address modelAddress = address == null
            ? null
            : Optional.of(address)
                    .filter(Address::isValidAddress)
                    .map(Address::new)
                    .orElseThrow(() -> new IllegalValueException(Address.MESSAGE_CONSTRAINTS));

        final Education modelEducation = education == null
            ? null
            : Optional.of(education)
                    .filter(Education::isValidEducation)
                    .map(Education::new)
                    .orElseThrow(() -> new IllegalValueException(Education.MESSAGE_CONSTRAINTS));

        final Remark modelRemark = remark == null
            ? null
            : new Remark(remark);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Set<Subject> modelSubjects = new HashSet<>(personSubjects);
        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelEducation, modelRemark,
                modelSubjects, modelTags);
    }

}

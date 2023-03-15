package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.socialmedia.SocialMedia;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private JsonAdaptedPhone phone = new JsonAdaptedPhone();
    private JsonAdaptedEmail email = new JsonAdaptedEmail();
    private JsonAdaptedAddress address = new JsonAdaptedAddress();
    private JsonAdaptedSocialMedia socialMedia = new JsonAdaptedSocialMedia();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private JsonAdaptedBirthday birthday = new JsonAdaptedBirthday();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") JsonAdaptedPhone phone,
            @JsonProperty("email") JsonAdaptedEmail email, @JsonProperty("address") JsonAdaptedAddress address,
            @JsonProperty("socialMedia") JsonAdaptedSocialMedia socialMedia,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("birthday") JsonAdaptedBirthday birthday) {
        this.name = name;
        if (phone != null) {
            this.phone = phone;
        }
        if (email != null) {
            this.email = email;
        }
        if (address != null) {
            this.address = address;
        }
        if (socialMedia != null) {
            this.socialMedia = socialMedia;
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (birthday != null) {
            this.birthday = birthday;
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));

        if (source.getPhone().isPresent()) {
            phone = new JsonAdaptedPhone(source.getPhone().get());
        }

        if (source.getEmail().isPresent()) {
            email = new JsonAdaptedEmail(source.getEmail().get());
        }

        if (source.getAddress().isPresent()) {
            address = new JsonAdaptedAddress(source.getAddress().get());
        }

        if (source.getSocialMedia().isPresent()) {
            socialMedia = new JsonAdaptedSocialMedia(source.getSocialMedia().get());
        }

        if (source.getBirthday().isPresent()) {
            birthday = new JsonAdaptedBirthday(source.getBirthday().get());
        }
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's
     * {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person.
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

        final Set<Tag> modelTags = new HashSet<>(personTags);
        Person p = new Person(modelName, modelTags);

        if (phone != null) {
            Optional<Phone> modelPhone = phone.toModelType();
            if (modelPhone.isPresent()) {
                p.setPhone(modelPhone.get());
            }
        }

        if (email != null) {
            Optional<Email> modelEmail = email.toModelType();
            if (modelEmail.isPresent()) {
                p.setEmail(modelEmail.get());
            }
        }

        if (address != null) {
            Optional<Address> modelAddress = address.toModelType();
            if (modelAddress.isPresent()) {
                p.setAddress(modelAddress.get());
            }
        }

        if (birthday != null) {
            Optional<Birthday> modelBirthday = birthday.toModelType();
            if (modelBirthday.isPresent()) {
                p.setBirthday(modelBirthday.get());
            }
        }

        if (socialMedia != null) {
            Optional<SocialMedia> modelSocialMedia = socialMedia.toModelType();
            if (modelSocialMedia.isPresent()) {
                p.setSocialMedia(modelSocialMedia.get());
            }
        }

        return p;
    }

}

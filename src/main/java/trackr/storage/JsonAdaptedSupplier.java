package trackr.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import trackr.commons.exceptions.IllegalValueException;
import trackr.model.commons.Tag;
import trackr.model.person.PersonAddress;
import trackr.model.person.PersonEmail;
import trackr.model.person.PersonName;
import trackr.model.person.PersonPhone;
import trackr.model.person.Supplier;

/**
 * Jackson-friendly version of {@link Supplier}.
 */
class JsonAdaptedSupplier {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Supplier's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedSupplier(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                               @JsonProperty("email") String email, @JsonProperty("address") String address,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    //@@author liumc-sg-reused
    /**
     * Converts a given {@code Supplier} into this class for Jackson use.
     */
    public JsonAdaptedSupplier(Supplier source) {
        name = source.getPersonName().getName();
        phone = source.getPersonPhone().personPhone;
        email = source.getPersonEmail().personEmail;
        address = source.getPersonAddress().personAddress;
        tagged.addAll(source.getPersonTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted supplier object into the model's {@code Supplier} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Supplier toModelType() throws IllegalValueException {
        final List<Tag> supplierTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            supplierTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PersonName.class.getSimpleName()));
        }
        if (!PersonName.isValidName(name)) {
            throw new IllegalValueException(PersonName.MESSAGE_CONSTRAINTS);
        }
        final PersonName modelName = new PersonName(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PersonPhone.class.getSimpleName()));
        }
        if (!PersonPhone.isValidPersonPhone(phone)) {
            throw new IllegalValueException(PersonPhone.MESSAGE_CONSTRAINTS);
        }
        final PersonPhone modelPersonPhone = new PersonPhone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PersonEmail.class.getSimpleName()));
        }
        if (!PersonEmail.isValidPersonEmail(email)) {
            throw new IllegalValueException(PersonEmail.MESSAGE_CONSTRAINTS);
        }
        final PersonEmail modelPersonEmail = new PersonEmail(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PersonAddress.class.getSimpleName()));
        }
        if (!PersonAddress.isValidPersonAddress(address)) {
            throw new IllegalValueException(PersonAddress.MESSAGE_CONSTRAINTS);
        }
        final PersonAddress modelPersonAddress = new PersonAddress(address);

        final Set<Tag> modelTags = new HashSet<>(supplierTags);
        return new Supplier(modelName, modelPersonPhone, modelPersonEmail, modelPersonAddress, modelTags);
    }

}

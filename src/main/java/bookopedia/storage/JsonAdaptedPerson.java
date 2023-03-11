package bookopedia.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import bookopedia.commons.exceptions.IllegalValueException;
import bookopedia.model.DeliveryStatus;
import bookopedia.model.person.Address;
import bookopedia.model.person.Email;
import bookopedia.model.person.Name;
import bookopedia.model.person.Person;
import bookopedia.model.person.Phone;
import bookopedia.model.parcel.Parcel;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedParcel> parcels = new ArrayList<>();

    private final String deliveryStatus;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("parcels") List<JsonAdaptedParcel> parcels,
            @JsonProperty("deliveryStatus") String deliveryStatus) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (parcels != null) {
            this.parcels.addAll(parcels);
        }
        this.deliveryStatus = deliveryStatus;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        parcels.addAll(source.getParcels().stream()
                .map(JsonAdaptedParcel::new)
                .collect(Collectors.toList()));
        deliveryStatus = source.getDeliveryStatus().name();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Parcel> personParcels = new ArrayList<>();
        for (JsonAdaptedParcel parcel : parcels) {
            personParcels.add(parcel.toModelType());
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
        if (!phone.equals("") && !Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!email.equals("") && !Email.isValidEmail(email)) {
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

        if (deliveryStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DeliveryStatus.class.getSimpleName()));
        }
        DeliveryStatus modelDeliveryStatus;
        try {
            modelDeliveryStatus = DeliveryStatus.valueOf(deliveryStatus);
        } catch (IllegalArgumentException exception) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }

        final Set<Parcel> modelParcels = new HashSet<>(personParcels);
        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelParcels, modelDeliveryStatus);
    }

}

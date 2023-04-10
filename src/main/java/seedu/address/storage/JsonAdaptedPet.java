package seedu.address.storage;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.pet.Address;
import seedu.address.model.pet.Deadline;
import seedu.address.model.pet.Email;
import seedu.address.model.pet.Name;
import seedu.address.model.pet.NoDeadline;
import seedu.address.model.pet.OwnerName;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Pet}.
 */
class JsonAdaptedPet {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Pet's %s field is missing!";

    private final String ownerName;
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String deadline;
    private String status;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    private String timestamp;

    /**
     * Constructs a {@code JsonAdaptedPet} with the given pet details.
     */
    @JsonCreator
    public JsonAdaptedPet(@JsonProperty("ownerName") String ownerName, @JsonProperty("name") String name,
            @JsonProperty("phone") String phone, @JsonProperty("email") String email,
            @JsonProperty("address") String address, @JsonProperty("timestamp") String timestamp,
                          @JsonProperty("deadline") String deadline, @JsonProperty("status") String status,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {

        this.ownerName = ownerName;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.timestamp = timestamp;
        this.address = address;
        this.deadline = deadline;
        this.status = status;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Pet} into this class for Jackson use.
     */
    public JsonAdaptedPet(Pet source) {
        ownerName = source.getOwnerName().fullName;
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        timestamp = source.getTimeStamp().toString();
        status = source.getIsMarked() ? "Marked" : "Unmarked";

        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        deadline = source.getDeadline().toString();
    }

    /**
     * Converts this Jackson-friendly adapted pet object into the model's {@code Pet} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted pet.
     */
    public Pet toModelType() throws IllegalValueException {
        final List<Tag> petTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            petTags.add(tag.toModelType());
        }

        if (ownerName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(ownerName)) {
            throw new IllegalValueException(OwnerName.MESSAGE_CONSTRAINTS);
        }
        final OwnerName modelOwnerName = new OwnerName(ownerName);
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

        if (timestamp == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }
        Instant i = Instant.parse(timestamp + "Z");
        final LocalDateTime modelTimeStamp = LocalDateTime.ofInstant(i, ZoneOffset.UTC);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    boolean.class.getSimpleName()));
        }
        boolean isMarked = status.equals("Marked");

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }

        Deadline modelDeadline = new NoDeadline();
        if (!deadline.equals("N/A")) {
            String[] split = deadline.split("by", 2);
            String description = split[0].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime time = LocalDateTime.parse(split[1].trim(), formatter);
            modelDeadline = new Deadline(description, time);
        }

        final Set<Tag> modelTags = new HashSet<>(petTags);

        Pet pet = new Pet(modelOwnerName, modelName, modelPhone, modelEmail,
                modelAddress, modelTimeStamp, modelDeadline, modelTags);
        if (isMarked) {
            pet.setMarked();
        }
        return pet;
    }

}

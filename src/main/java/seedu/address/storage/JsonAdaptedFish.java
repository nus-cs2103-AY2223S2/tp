package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.fish.Address;
import seedu.address.model.fish.Email;
import seedu.address.model.fish.Fish;
import seedu.address.model.fish.LastFedDate;
import seedu.address.model.fish.Name;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Fish}.
 */
class JsonAdaptedFish {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Fish's %s field is missing!";

    private final String name;
    private final String lastFedDate;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedFish} with the given fish details.
     */
    @JsonCreator
    public JsonAdaptedFish(@JsonProperty("name") String name, @JsonProperty("lastFedDate") String lastFedDate,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.lastFedDate = lastFedDate;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Fish} into this class for Jackson use.
     */
    public JsonAdaptedFish(Fish source) {
        name = source.getName().fullName;
        lastFedDate = source.getLastFedDate().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted fish object into the model's {@code Fish} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted fish.
     */
    public Fish toModelType() throws IllegalValueException {
        final List<Tag> fishTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            fishTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (lastFedDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, LastFedDate.class
                    .getSimpleName()));
        }
        if (!LastFedDate.isValidLastFedDate(lastFedDate)) {
            throw new IllegalValueException(LastFedDate.MESSAGE_CONSTRAINTS);
        }
        final LastFedDate modelLastFedDate = new LastFedDate(lastFedDate);

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

        final Set<Tag> modelTags = new HashSet<>(fishTags);
        return new Fish(modelName, modelLastFedDate, modelEmail, modelAddress, modelTags);
    }

}

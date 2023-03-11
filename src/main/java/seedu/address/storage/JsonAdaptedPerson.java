package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Event;
import seedu.address.model.person.Name;
import seedu.address.model.person.Rate;
import seedu.address.model.person.Timing;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Event}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String rate;
    private final String address;
    private final String startTime;
    private final String endTime;
    private final String mark;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("rate") String rate,
            @JsonProperty("address") String address,
            @JsonProperty("startTiming") String startTime,
            @JsonProperty("endTiming") String endTiming,
            @JsonProperty("mark") String mark,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.rate = rate;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTiming;
        this.mark = mark;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Event source) {
        name = source.getName().fullName;
        rate = Double.toString(source.getRate().value);
        address = source.getAddress().value;
        startTime = source.getTiming().startTime;
        endTime = source.getTiming().endTime;
        mark = source.getMark().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Event toModelType() throws IllegalValueException {
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

        if (rate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Rate.class.getSimpleName()));
        }
        if (!Rate.isValidRate(rate)) {
            throw new IllegalValueException(Rate.MESSAGE_CONSTRAINTS);
        }
        final Rate modelRate = new Rate(rate);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (startTime == null || endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Timing.class.getSimpleName()));
        }
        if (!Timing.isValidTiming(startTime, endTime)) {
            throw new IllegalValueException(Timing.MESSAGE_CONSTRAINTS);
        }
        final Timing modelTiming = new Timing(startTime, endTime);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        Event event = new Event(modelName, modelRate, modelAddress, modelTiming, modelTags);
        if (mark.equals("[X]")) {
            event.mark();
        }
        return event;
    }

}

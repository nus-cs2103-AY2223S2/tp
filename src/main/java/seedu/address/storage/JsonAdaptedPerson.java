package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String type;
    private final String timeSlot;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String remark;
    private final String deadline;
    private final String teacher;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("type") String type,
            @JsonProperty("timeSlot") String timeSlot, @JsonProperty("address") String address,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged, @JsonProperty("remark") String remark,
            @JsonProperty("deadline") String deadline, @JsonProperty("teacher") String teacher) {
        this.name = name;
        this.type = type;
        this.timeSlot = timeSlot;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.remark = remark;
        this.deadline = deadline;
        this.teacher = teacher;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        type = source.getType().value;
        timeSlot = source.getTimeSlot().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        remark = source.getRemark().value;
        deadline = source.getDeadline().value;
        teacher = source.getTeacher().value;
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

        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Type.class.getSimpleName()));
        }
        if (!Type.isValidType(type)) {
            throw new IllegalValueException(Type.MESSAGE_CONSTRAINTS);
        }
        final Type modelType = new Type(type);

        if (timeSlot == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, TimeSlot.class.getSimpleName()));
        }
        if (!TimeSlot.isValidTimeSlot(timeSlot)) {
            throw new IllegalValueException(TimeSlot.MESSAGE_CONSTRAINTS);
        }
        final TimeSlot modelTimeSlot = new TimeSlot(timeSlot);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);
        final Deadline modelDeadline = new Deadline(deadline);
        final Teacher modelTeacher = new Teacher(teacher);
        return new Person(modelName, modelType, modelTimeSlot, modelAddress, modelTags, modelRemark, modelDeadline, modelTeacher);
    }

}

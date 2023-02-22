package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Person;
import seedu.address.model.person.information.Name;

/**
 * Jackson-friendly version of {@link Pair}.
 */
class JsonAdaptedPair {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Pair's %s field is missing!";

    private final String elderlyName;
    private final String volunteerName;

    /**
     * Constructs a {@code JsonAdaptedPair} with the given pair details.
     */
    @JsonCreator
    public JsonAdaptedPair(@JsonProperty("elderlyName") String elderlyName,
                           @JsonProperty("volunteerName") String volunteerName) {
        this.elderlyName = elderlyName;
        this.volunteerName = volunteerName;
    }

    /**
     * Converts a given {@code Pair} into this class for Jackson use.
     */
    public JsonAdaptedPair(Pair source) {
        elderlyName = source.getElderly().getName().fullName;
        volunteerName = source.getVolunteer().getName().fullName;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Pair toModelType(FriendlyLink friendlyLink) throws IllegalValueException {
        if (elderlyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(elderlyName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelElderlyName = new Name(elderlyName);
        Person elderly = friendlyLink.getPerson(modelElderlyName);


        if (volunteerName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(volunteerName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelVolunteerName = new Name(volunteerName);
        Person volunteer = friendlyLink.getPerson(modelVolunteerName);


        return new Pair(elderly, volunteer);
    }

}

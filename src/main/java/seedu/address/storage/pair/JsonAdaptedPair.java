package seedu.address.storage.pair;

import static seedu.address.commons.core.Messages.MESSAGE_ELDERLY_NOT_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_VOLUNTEER_NOT_FOUND;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.exceptions.ElderlyNotFoundException;
import seedu.address.model.person.exceptions.VolunteerNotFoundException;
import seedu.address.model.person.information.Nric;
import seedu.address.storage.JsonSerializable;

/**
 * Jackson-friendly version of {@link Pair}.
 */
class JsonAdaptedPair implements JsonSerializable<Pair> {

    public static final String MISSING_ELDERLY_FIELD_MESSAGE_FORMAT = "Elderly member's %s field is missing!";
    public static final String MISSING_VOLUNTEER_FIELD_MESSAGE_FORMAT = "Volunteer's %s field is missing!";

    private final String elderlyNric;
    private final String volunteerNric;

    /**
     * Constructs a {@code JsonAdaptedPair} with the given pair details.
     */
    @JsonCreator
    public JsonAdaptedPair(@JsonProperty("elderlyNric") String elderlyNric,
            @JsonProperty("volunteerNric") String volunteerNric) {
        this.elderlyNric = elderlyNric;
        this.volunteerNric = volunteerNric;
    }

    /**
     * Converts a given {@code Pair} into this class for Jackson use.
     */
    public JsonAdaptedPair(Pair source) {
        elderlyNric = source.getElderly().getNric().value;
        volunteerNric = source.getVolunteer().getNric().value;
    }

    /**
     * Converts this Jackson-friendly adapted pair object into the model's {@code Pair} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted pair.
     */
    public Pair toModelType(FriendlyLink friendlyLink) throws IllegalValueException {
        if (elderlyNric == null) {
            throw new IllegalValueException(String.format(MISSING_ELDERLY_FIELD_MESSAGE_FORMAT,
                    Nric.class.getSimpleName()));
        }
        if (volunteerNric == null) {
            throw new IllegalValueException(String.format(MISSING_VOLUNTEER_FIELD_MESSAGE_FORMAT,
                    Nric.class.getSimpleName()));
        }
        if (!(Nric.isValidNric(elderlyNric) && Nric.isValidNric(volunteerNric))) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }

        final Nric modelElderlyNric = new Nric(elderlyNric);
        final Nric modelVolunteerNric = new Nric(volunteerNric);

        Elderly elderly = friendlyLink.getElderly(modelElderlyNric);
        Volunteer volunteer = friendlyLink.getVolunteer(modelVolunteerNric);
        if (elderly == null) {
            throw new IllegalValueException(String.format(MESSAGE_ELDERLY_NOT_FOUND, modelElderlyNric));
        }
        if (volunteer == null) {
            throw new IllegalValueException(String.format(MESSAGE_VOLUNTEER_NOT_FOUND, modelVolunteerNric));
        }
        return new Pair(elderly, volunteer);

    }
}

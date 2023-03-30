package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meetup.Participants;
import seedu.address.model.person.Person;


/**
 * Jackson-friendly version of {@link Participants}.
 */
public class JsonAdaptedParticipants {

    private static final String MISSING_PARTICIPANTS = "Participants list is missing";
    protected final List<JsonAdaptedPerson> participants;

    /**
     * Constructs a {@code JsonAdaptedParticipants} with the given participants indexes.
     */
    @JsonCreator
    public JsonAdaptedParticipants(@JsonProperty("participants") List<JsonAdaptedPerson> participants) {
        this.participants = participants;
    }

    /**
     * Converts a given {@code Participants} into this class for Jackson use.
     */
    public JsonAdaptedParticipants(Participants participantsList) {
        this.participants = new ArrayList<>();
        assert (participantsList != null);
        for (Person participant : participantsList.getParticipants()) {
            this.participants.add(new JsonAdaptedPerson(participant));
        }
    }

    /**
     * Converts this Jackson-friendly adapted Participants object into the model's {@code Participants} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted participants.
     */
    public Participants toModelType() throws IllegalValueException {
        if (participants == null) {
            throw new IllegalValueException(MISSING_PARTICIPANTS);
        }
        List<Person> people = new ArrayList<>();
        for (JsonAdaptedPerson person : participants) {
            people.add(person.toModelType());
        }

        final Participants modelParticipants = new Participants(people);
        return modelParticipants;
    }
}

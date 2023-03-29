package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meetup.Participants;
import seedu.address.model.person.ContactIndex;

import java.util.ArrayList;
import java.util.List;

/**
 * Jackson-friendly version of {@link Participants}.
 */
public class JsonAdaptedParticipants {

    private static final String MISSING_PARTICIPANTS = "Participants list is missing";
    protected final List<Integer> participants;

    /**
     * Constructs a {@code JsonAdaptedParticipants} with the given participants indexes.
     */
    @JsonCreator
    public JsonAdaptedParticipants(@JsonProperty("participants") List<Integer> participants) {
        this.participants = participants;
    }

    /**
     * Converts a given {@code Participants} into this class for Jackson use.
     */
    public JsonAdaptedParticipants(Participants participantsList) {
        this.participants = new ArrayList<>();
        assert (participantsList != null);
        for (ContactIndex contactIndex : participantsList.getParticipants()) {
            this.participants.add(contactIndex.getContactIndex());
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
        final Participants modelParticipants = new Participants(participants);
        return modelParticipants;
    }
}

package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Participants;
import seedu.address.model.person.ContactIndex;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JsonAdaptedParticipants {

    protected final List<Integer> participants;

    @JsonCreator
    public JsonAdaptedParticipants(@JsonProperty("participants") List<Integer> participants) {
        this.participants = participants;
    }

    public JsonAdaptedParticipants(Participants participantsList) {
        this.participants = new ArrayList<>();
        assert (participantsList != null);
        for (ContactIndex contactIndex : participantsList.getParticipants()) {
            this.participants.add(contactIndex.getContactIndex());
        }

    }

    public Participants toModelType() {
        //todo check this again
        final List<Integer> temp = new ArrayList<>();
        for (Integer i : participants) {
            temp.add(i);
        }
        final Participants modelParticipants = new Participants(temp);
        return modelParticipants;
    }
}

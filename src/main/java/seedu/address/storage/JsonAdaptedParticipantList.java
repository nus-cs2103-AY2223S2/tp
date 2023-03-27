package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.person.ContactIndex;

import java.util.Set;

public class JsonAdaptedParticipantList {
    protected final Set<ContactIndex> participantList;

    @JsonCreator
    public JsonAdaptedParticipantList(@JsonProperty("participantList") Set<ContactIndex> participantList) {
        this.participantList = participantList;
    }

    public Set<ContactIndex> toModelType() {
        return participantList; //todo check again before push
    }
}

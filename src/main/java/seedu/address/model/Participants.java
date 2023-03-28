package seedu.address.model;

import seedu.address.model.person.ContactIndex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a group of participants in a meetup by their indexes.
 */
public class Participants {

    Set<ContactIndex> participants;

    /**
     * Constructor for {@code Participants} object.
     * @param participantIndexList The set of participant indexes.
     */
    public Participants(Set<ContactIndex> participantIndexList) {
        participants = participantIndexList;
    }

    public Participants() {
        participants = new HashSet<>();
    }

    public Participants(List<Integer> participantIndexList) {
        List<ContactIndex> contactIndices = new ArrayList<>();
        for (Integer i : participantIndexList) {
            contactIndices.add(new ContactIndex(i));
        }
        participants = new HashSet<>(contactIndices);
    }

    public Set<ContactIndex> getParticipants() {
        return this.participants;
    }

}

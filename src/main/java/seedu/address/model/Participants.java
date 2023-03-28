package seedu.address.model;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.ContactIndex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Participants {

    Set<ContactIndex> participants;

    public Participants(Set<ContactIndex> contactIndexSet) {
        participants = contactIndexSet;
    }

    public Participants() {
        participants = new HashSet<>();
    };

    public Participants(List<Integer> contactIndexList) {
        //todo may need to rename this
        List<ContactIndex> temp = new ArrayList<>();
        for (Integer i : contactIndexList) {
            temp.add(new ContactIndex(i));
        }
        participants = new HashSet<>(temp);
    }

    public Set<ContactIndex> getParticipants() {
        return this.participants;
    }

}

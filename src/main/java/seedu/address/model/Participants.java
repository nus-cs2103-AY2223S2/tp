package seedu.address.model;

import seedu.address.model.person.ContactIndex;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Participants {

    Set<ContactIndex> participants;

    public Participants(Set<ContactIndex> contactIndexSet) {
        participants = contactIndexSet;
    }
}

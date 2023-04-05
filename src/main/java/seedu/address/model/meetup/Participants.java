package seedu.address.model.meetup;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;

/**
 * Represents a group of participants in a meetup by their indexes.
 */
public class Participants {

    private List<Person> participants;
    private final Set<ContactIndex> contactIndices;

    /**
     * Constructor for {@code Participants} object.
     * @param participants The set of participant indexes.
     */
    public Participants(List<Person> participants) {

        this.participants = new ArrayList<>();
        //to remove the user
        for (Person person : participants) {
            if (person.getContactIndex().getContactIndex() != 0) {
                this.participants.add(person);
            }
        }
        this.contactIndices = new HashSet<>();
    }

    /**
     * Constructor for {@code Participants} object.
     */
    public Participants() {
        this.participants = new ArrayList<>();
        this.contactIndices = new HashSet<>();
    }

    /**
     * Constructor for {@code Participants} object.
     */
    public Participants(Set<ContactIndex> indices) {
        this.participants = new ArrayList<>();
        this.contactIndices = indices;
    }

    public void setParticipants(List<Person> participants) {
        this.participants = participants;
    }

    public Set<ContactIndex> getContactIndices() {
        if (this.contactIndices.isEmpty()) {
            for (Person person : this.participants) {
                contactIndices.add(person.getContactIndex());
            }
        }
        return this.contactIndices;
    }

    public List<Person> getParticipants() {
        return this.participants;
    }

    public boolean containsParticipant(Person person) {
        return this.participants.contains(person);
    }

    /**
     * Compares two Participants to check whether all contact indexes are the same.
     */
    public boolean isSameParticipants(Participants other) {
        return this.getContactIndices().equals(other.getContactIndices());
    }
}

package seedu.address.model.person;

/**
 * Slight Variation of a normal {@code Meeting} that also stores data about the person to meet.
 * Mostly a helper class for {@code UniquePersonList} to help UI know the name of the person to display
 */
public class MeetingWithPerson extends Meeting {

    private final Person personToMeet;

    public MeetingWithPerson(Meeting meeting, Person personToMeet) {
        super(meeting.description, meeting.start, meeting.end);
        this.personToMeet = personToMeet;
    }

    public Person getPersonToMeet() {
        return personToMeet;
    }
}

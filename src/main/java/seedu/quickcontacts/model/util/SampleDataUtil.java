package seedu.quickcontacts.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.quickcontacts.model.QuickBook;
import seedu.quickcontacts.model.ReadOnlyQuickBook;
import seedu.quickcontacts.model.meeting.DateTime;
import seedu.quickcontacts.model.meeting.Description;
import seedu.quickcontacts.model.meeting.Location;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.model.meeting.Title;
import seedu.quickcontacts.model.person.Address;
import seedu.quickcontacts.model.person.Email;
import seedu.quickcontacts.model.person.Name;
import seedu.quickcontacts.model.person.Person;
import seedu.quickcontacts.model.person.Phone;
import seedu.quickcontacts.model.tag.Tag;

/**
 * Contains utility methods for populating {@code QuickBook} with sample data.
 */
public class SampleDataUtil {
    /**
     * {@code Person} that are also attendees of meetings.
     */
    private static final Person ALEX = new Person(new Name("Alex Yeoh"), new Phone("87438807"),
            new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
            getTagSet("friends"));

    public static Person[] getSamplePersons() {
        return new Person[] {
            ALEX,
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static Meeting[] getSampleMeetingss() {
        return new Meeting[] {
            new Meeting(new Title("Lunch with Alex"), new DateTime("03/03/2023 15:00"), getAttendeeSet(ALEX),
                    new Location("The Deck"), new Description("Catch-up"))
        };
    }

    public static ReadOnlyQuickBook getSampleQuickBook() {
        QuickBook sampleAb = new QuickBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        for (Meeting sampleMeeting : getSampleMeetingss()) {
            sampleAb.addMeeting(sampleMeeting);
        }

        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an attendee set containing the list of Person given.
     */
    public static Set<Person> getAttendeeSet(Person... attendees) {
        return Arrays.stream(attendees)
                .collect(Collectors.toSet());
    }
}

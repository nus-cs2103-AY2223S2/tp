package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserData;
import seedu.address.model.event.Event;
import seedu.address.model.event.OneTimeEvent;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;
import seedu.address.model.person.Person;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Faculty;
import seedu.address.model.person.fields.Favorite;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.Tags;
import seedu.address.model.person.fields.subfields.NusMod;
import seedu.address.model.person.fields.subfields.Tag;
import seedu.address.model.user.UserData;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    // The following sample people are adapted from ChatGPT-generated content.
    public static final Person SAMPLE_PERSON = new Person(new Name("John Doe"), new Phone("98765432"),
            new Email("john.doe@example.com"), new Address("123 Main St, Anytown"), new Gender("Male"),
            new Major("Computer Science"),
            new Modules(Set.of(new NusMod("CS1101S"), new NusMod("CS1231S"))),
            new Race("Caucasian"), new Tags(Set.of(new Tag("friends"), new Tag("colleagues"))),
            new CommunicationChannel("Telegram"), new Favorite(true), new Faculty("Computing"));
    public static final Person PERSON_1 = new Person(new Name("Randy Jackson"), new Phone("84567890"),
            new Email("randy.jackson@gmail.com"), new Address("123 Main St, Anytown USA"), new Gender("Male"),
            new Major("Computer Science"),
            new Modules(Set.of(new NusMod("MA1521"), new NusMod("IS1108"), new NusMod("EC2101"))),
            new Race("Asian"), new Tags(Set.of(new Tag("Athlete"), new Tag("Musician"))),
            new CommunicationChannel("Email"), new Favorite(true), new Faculty("Faculty of Science"));
    public static final Person PERSON_2 = new Person(new Name("Olivia Brown"), new Phone("89123478"),
            new Email("olivia.brown@hotmail.com"), new Address("456 High St, London UK"), new Gender("Female"),
            new Major("English Literature"),
            new Modules(Set.of(new NusMod("EN1101E"), new NusMod("EN2201"),
                    new NusMod("EN3220"), new NusMod("EN4222"))),
            new Race("Caucasian"), new Tags(Set.of(new Tag("Artist"), new Tag("Dancer"), new Tag("Volunteer"))),
            new CommunicationChannel("WhatsApp"), new Favorite(false),
            new Faculty("Faculty of Arts and Social Sciences"));
    public static final Person PERSON_3 = new Person(new Name("Mohammed Ali"), new Phone("97150123"),
            new Email("mohammed.ali@gamil.com"), new Address("789 Al Fahidi St, Dubai UAE"), new Gender("Male"),
            new Major("Business Administration"),
            new Modules(Set.of(new NusMod("BSP1702"), new NusMod("ACC1701"),
                    new NusMod("FIN2704"), new NusMod("MKT2711"))),
            new Race("Arab"), new Tags(Set.of(new Tag("Entrepreneur"), new Tag("Soccer Player"), new Tag("Traveler"))),
            new CommunicationChannel("Phone"), new Favorite(true), new Faculty("NUS Business School"));
    public static final Person PERSON_4 = new Person(new Name("Sara Kim"), new Phone("98765432"),
            new Email("sara.kim@naver.com"), new Address("321 Gangnam Blvd, Seoul South Korea"), new Gender("Female"),
            new Major("Chemical Engineering"),
            new Modules(Set.of(new NusMod("CM1102"), new NusMod("CM2112"),
                    new NusMod("CM3221"), new NusMod("CM4211"))),
            new Race("Asian"), new Tags(Set.of(new Tag("Researcher"), new Tag("Pianist"), new Tag("Foodie"))),
            new CommunicationChannel("Zoom"), new Favorite(false), new Faculty("Faculty of Engineering"));
    public static final Person PERSON_5 = new Person(new Name("Michael Johnson"), new Phone("95678901"),
            new Email("michael.johnson@yahoo.com"), new Address("567 Pine St, San Francisco USA"), new Gender("Male"),
            new Major("Psychology"),
            new Modules(Set.of(new NusMod("PL2131"), new NusMod("EL3201"), new NusMod("PL3103"))),
            new Race("African American"),
            new Tags(Set.of(new Tag("Advocate"), new Tag("Swimmer"), new Tag("Photographer"))),
            new CommunicationChannel("Skype"), new Favorite(true), new Faculty("Faculty of Arts and Social Sciences"));

    public static final Event EVENT_1 = new OneTimeEvent(new Description("CS2101 Meeting"),
            new DateTime("2023-03-31 1600"), new DateTime("2023-03-31 1700"), Set.of(PERSON_2, PERSON_5));
    public static final Event EVENT_2 = new RecurringEvent(new Description("CS2103T Lecture"),
            new DateTime("2023-03-31 1400"), new DateTime("2023-03-31 1600"), new Recurrence(Recurrence.WEEKLY_CASE),
            Set.of(PERSON_1, PERSON_5));
    public static final Event EVENT_3 = new RecurringEvent(new Description("Christmas Day"),
            new DateTime("2023-12-25 0000"), new DateTime("2023-12-25 2359"), new Recurrence(Recurrence.YEARLY_CASE),
            Set.of(PERSON_1, PERSON_2, PERSON_4));
    public static final Event EVENT_4 = new RecurringEvent(new Description("Dinner with Olivia"),
            new DateTime("2023-03-31 1800"), new DateTime("2023-03-31 1900"), new Recurrence(Recurrence.DAILY_CASE),
            Set.of(PERSON_2));
    public static final Event EVENT_5 = new RecurringEvent(new Description("Gym Session"),
            new DateTime("2023-03-31 0800"), new DateTime("2023-03-31 0900"), new Recurrence(Recurrence.DAILY_CASE),
            Set.of(PERSON_4, PERSON_5));

    public static Person[] getSamplePersons() {
        return new Person[]{PERSON_1, PERSON_2, PERSON_3, PERSON_4, PERSON_5};
    }

    public static Event[] getSampleEvents() {
        return new Event[]{EVENT_1, EVENT_2, EVENT_3, EVENT_4, EVENT_5};
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyUserData getSampleUserData() {
        UserData userData = new UserData();
        for (Event event : getSampleEvents()) {
            userData.addEvent(event);
        }
        return userData;
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
     * Returns a nusmod set containing the list of strings given.
     */
    public static Set<NusMod> getModsSet(String... strings) {
        return Arrays.stream(strings)
                .map(NusMod::new)
                .collect(Collectors.toSet());
    }

}

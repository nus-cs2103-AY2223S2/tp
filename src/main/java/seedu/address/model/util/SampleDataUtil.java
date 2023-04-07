package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.HMHero;
import seedu.address.model.ReadOnlyHMHero;
import seedu.address.model.note.Note;
import seedu.address.model.person.Address;
import seedu.address.model.person.ApplicationDateTime;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static final LocalDateTime SAMPLE_DATE_TIME = LocalDateTime.of(2023, 01, 01, 16, 25);
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), Status.APPLIED,
                    new ApplicationDateTime(SAMPLE_DATE_TIME),
                    Optional.empty(), getNoteSet("C#")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), Status.APPLIED,
                    new ApplicationDateTime(SAMPLE_DATE_TIME),
                    Optional.empty(), getNoteSet("C++", "Java")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), Status.REJECTED,
                    new ApplicationDateTime(SAMPLE_DATE_TIME), Optional.empty(),
                getNoteSet("C")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), Status.APPLIED,
                    new ApplicationDateTime(SAMPLE_DATE_TIME),
                    Optional.empty(), getNoteSet("Leadership")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), Status.APPLIED,
                    new ApplicationDateTime(SAMPLE_DATE_TIME), Optional.empty(),
                getNoteSet("Python")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), Status.APPLIED,
                    new ApplicationDateTime(SAMPLE_DATE_TIME), Optional.empty(),
                getNoteSet("Java"))
        };
    }

    public static ReadOnlyHMHero getSampleAddressBook() {
        HMHero sampleAb = new HMHero();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a note set containing the list of strings given.
     */
    public static Set<Note> getNoteSet(String... strings) {
        return Arrays.stream(strings)
                .map(Note::new)
                .collect(Collectors.toSet());
    }

}

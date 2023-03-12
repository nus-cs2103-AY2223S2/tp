package expresslibrary.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import expresslibrary.model.ExpressLibrary;
import expresslibrary.model.ReadOnlyExpressLibrary;
import expresslibrary.model.person.Address;
import expresslibrary.model.person.Email;
import expresslibrary.model.person.Name;
import expresslibrary.model.person.Person;
import expresslibrary.model.person.Phone;
import expresslibrary.model.person.Book;
import expresslibrary.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ExpressLibrary} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Book("Book 1"), getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        new Book("Book 1"), getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        new Book("Book 1"), getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        new Book("Book 1"), getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"),
                        new Book("Book 1"), getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"),
                        new Book("Book 1"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyExpressLibrary getSampleExpressLibrary() {
        ExpressLibrary sampleAb = new ExpressLibrary();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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

}

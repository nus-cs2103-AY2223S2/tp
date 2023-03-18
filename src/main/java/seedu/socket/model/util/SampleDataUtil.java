package seedu.socket.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.socket.model.ReadOnlySocket;
import seedu.socket.model.Socket;
import seedu.socket.model.person.Address;
import seedu.socket.model.person.Email;
import seedu.socket.model.person.GitHubProfile;
import seedu.socket.model.person.Name;
import seedu.socket.model.person.Person;
import seedu.socket.model.person.Phone;
import seedu.socket.model.tag.Language;
import seedu.socket.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Socket} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new GitHubProfile("alex-yeoh"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                getLanguageSet("Java"), getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new GitHubProfile("bernice-yu"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getLanguageSet("Python", "Dart"), getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new GitHubProfile("charlotte-o"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getLanguageSet("C"), getTagSet("neighbours")),
            new Person(new Name("David Li"), new GitHubProfile("david-li"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getLanguageSet("C#"), getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new GitHubProfile("irfan-ibrahim"), new Phone("92492021"),
                new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                getLanguageSet("C++"), getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new GitHubProfile("roy-balakrishnan"), new Phone("92624417"),
                new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                getLanguageSet("JavaScript"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlySocket getSampleSocket() {
        Socket sampleSocket = new Socket();
        for (Person samplePerson : getSamplePersons()) {
            sampleSocket.addPerson(samplePerson);
        }
        return sampleSocket;
    }

    /**
     * Returns a language set containing the list of strings given.
     */
    public static Set<Language> getLanguageSet(String... strings) {
        return Arrays.stream(strings)
                .map(Language::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Set<Person> getMemberSet(Person ... members) {
        return Arrays.stream(members)
            .collect(Collectors.toSet());
    }
}

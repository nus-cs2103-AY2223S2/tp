package teambuilder.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import teambuilder.model.ReadOnlyTeamBuilder;
import teambuilder.model.TeamBuilder;
import teambuilder.model.person.Address;
import teambuilder.model.person.Email;
import teambuilder.model.person.Major;
import teambuilder.model.person.Name;
import teambuilder.model.person.Person;
import teambuilder.model.person.Phone;
import teambuilder.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), Phone.of("87438807"), Email.of("alexyeoh@example.com"),
                Address.of("Blk 30 Geylang Street 29, #06-40"), Major.of("Computer Science"),
                getTagSet("friends"), getTagSet()),
            new Person(new Name("Bernice Yu"), Phone.of("99272758"), Email.of("berniceyu@example.com"),
                Address.of("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), Major.of("Computer Science"),
                getTagSet("colleagues", "friends"), getTagSet()),
            new Person(new Name("Charlotte Oliveiro"), Phone.of("93210283"), Email.of("charlotte@example.com"),
                Address.of("Blk 11 Ang Mo Kio Street 74, #11-04"), Major.of("Computer Science"),
                getTagSet("neighbours"), getTagSet()),
            new Person(new Name("David Li"), Phone.of("91031282"), Email.of("lidavid@example.com"),
                Address.of("Blk 436 Serangoon Gardens Street 26, #16-43"), Major.of("Computer Science"),
                getTagSet("family"), getTagSet()),
            new Person(new Name("Irfan Ibrahim"), Phone.of("92492021"), Email.of("irfan@example.com"),
                Address.of("Blk 47 Tampines Street 20, #17-35"), Major.of("Computer Science"),
                getTagSet("classmates"), getTagSet()),
            new Person(new Name("Roy Balakrishnan"), Phone.of("92624417"), Email.of("royb@example.com"),
                Address.of("Blk 45 Aljunied Street 85, #11-31"), Major.of("Computer Science"),
                getTagSet("colleagues"), getTagSet())
        };
    }

    public static ReadOnlyTeamBuilder getSampleAddressBook() {
        TeamBuilder sampleAb = new TeamBuilder();
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

    /**
     * Returns a name set containing the list of strings given.
     */
    public static Set<Name> getNameSet(String... strings) {
        return Arrays.stream(strings)
                .map(Name::new)
                .collect(Collectors.toSet());
    }

}

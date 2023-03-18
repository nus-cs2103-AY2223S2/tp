package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.subfields.NusMod;
import seedu.address.model.person.fields.subfields.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {

        Set<NusMod> mods = new HashSet<>();
        mods.add(new NusMod("CS2103T"));
        Gender gender = new Gender("Male");
        Major major = new Major("Computer Science");
        Modules modules = new Modules(mods);
        Race race = new Race("Chinese");


        return new Person[] {
        //            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
        //                new Address("Blk 30 Geylang Street 29, #06-40"), gender, major, modules, race,
        //                getTagSet("friends")),
        //            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
        //                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), gender, major, modules, race,
        //                getTagSet("colleagues", "friends")),
        //                new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
        //                new Email("charlotte@example.com"),
        //                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), gender, major, modules, race,
        //                getTagSet("neighbours")),
        //            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
        //                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), gender, major, modules, race,
        //                getTagSet("family")),
        //            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
        //                new Address("Blk 47 Tampines Street 20, #17-35"), gender, major, modules, race,
        //                getTagSet("classmates")),
        //            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
        //                new Address("Blk 45 Aljunied Street 85, #11-31"), gender, major, modules, race,
        //                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
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

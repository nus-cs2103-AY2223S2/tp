package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
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

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Person SAMPLE_PERSON = new Person(new Name("John Doe"), new Phone("98765432"),
            new Email("john.doe@example.com"), new Address("123 Main St, Anytown"), new Gender("Male"),
            new Major("Computer Science"),
            new Modules(Set.of(new NusMod("CS1101S"), new NusMod("CS1231S"))),
            new Race("Caucasian"), new Tags(Set.of(new Tag("friends"), new Tag("colleagues"))),
            new CommunicationChannel("Telegram"), new Favorite(true), new Faculty("Computing"));

    public static Person[] getSamplePersons() {

        Set<NusMod> mods = new HashSet<>();
        mods.add(new NusMod("CS2103T"));
        Gender gender = new Gender("Male");
        Major major = new Major("Computer Science");
        Modules modules = new Modules(mods);
        Race race = new Race("Chinese");

        return new Person[]{};
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

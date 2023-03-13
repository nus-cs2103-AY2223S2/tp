package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.module.Module;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.skill.Skill;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Github("alexyeoh"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getSkillSet("python"),
                getModuleSet("AY2223S2 CS1101")),
            new Person(new Name("Bernice Yu"), new Github("bernice-yu"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getSkillSet("java", "python"),
                getModuleSet("AY2223S2 CS1101", "AY2223S2 CS2030S")),
            new Person(new Name("Charlotte Oliveiro"), new Github("ch4rl0tt3"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getSkillSet("sql"),
                getModuleSet("AY2223S2 CS1101", "AY2223S2 CS2030S", "AY2223S2 CS2040S")),

            new Person(new Name("David Li"), new Github("David-Li"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getSkillSet("c"),
                getModuleSet("AY2223S2 CS1101", "AY2223S2 CS2030S")),
            new Person(new Name("Irfan Ibrahim"), new Github("iRf4n"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getSkillSet("javascript"),
                getModuleSet("AY2223S2 CS1101", "AY2223S2 CS2030S")),
            new Person(new Name("Roy Balakrishnan"), new Github("b4l4Kr15H-n4n"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getSkillSet("java"),
                getModuleSet("AY2223S2 CS1101", "AY2223S2 CS2030S"))
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
     * Returns a skill set containing the list of strings given.
     */
    public static Set<Skill> getSkillSet(String... strings) {
        return Arrays.stream(strings)
                .map(Skill::new)
                .collect(Collectors.toSet());
    }
    /**
     * Returns a module list containing the list of strings given.
     */
    public static Set<Module> getModuleSet(String... strings) {
        return Arrays.stream(strings)
                .map(Module::new)
                .collect(Collectors.toSet());
    }
}

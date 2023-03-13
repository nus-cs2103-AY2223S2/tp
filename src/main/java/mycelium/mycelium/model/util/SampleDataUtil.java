package mycelium.mycelium.model.util;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import mycelium.mycelium.model.AddressBook;
import mycelium.mycelium.model.ReadOnlyAddressBook;
import mycelium.mycelium.model.person.Address;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.person.Name;
import mycelium.mycelium.model.person.Person;
import mycelium.mycelium.model.person.Phone;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;
import mycelium.mycelium.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
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

    public static Project[] getSampleProjects() {
        return new Project[]{
            new Project(
                "Bing",
                ProjectStatus.NOT_STARTED,
                new Email("johndoe@gmail.com"),
                Optional.ofNullable("fiver"),
                Optional.ofNullable("Create the next google AKA bing"),
                new Date(),
                Optional.empty()
            ),
            new Project(
                "Havard2.0",
                ProjectStatus.IN_PROGRESS,
                new Email("EluidKipchoge@gmail.com"),
                Optional.ofNullable("Behind the alley"),
                Optional.ofNullable("University on the streets"),
                new Date(),
                Optional.ofNullable(new Date())
            ),
            new Project(
                "Build Skynet",
                ProjectStatus.DONE,
                new Email("VladPutin@hotmale.com"),
                Optional.ofNullable("Russia"),
                Optional.ofNullable("Conquer the world"),
                new Date(),
                Optional.empty()
            )
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Project sampleProject : getSampleProjects()) {
            sampleAb.addProject(sampleProject);
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

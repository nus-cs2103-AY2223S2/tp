package mycelium.mycelium.model.util;

import java.time.LocalDate;
import java.util.Optional;

import mycelium.mycelium.model.AddressBook;
import mycelium.mycelium.model.ReadOnlyAddressBook;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.client.Name;
import mycelium.mycelium.model.client.Phone;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Client[] getSampleClients() {
        return new Client[]{
            new Client(
                new Name("Alex Yeoh"),
                new Email("alexyeoh@example.com"),
                Optional.ofNullable(new YearOfBirth("1998")),
                NonEmptyString.ofOptional("www.fiverr.com"),
                Optional.ofNullable(new Phone("87438807"))),
            new Client(
                new Name("Bernice Yu"),
                new Email("berniceyu@example.com"),
                Optional.ofNullable(new YearOfBirth("1972")),
                NonEmptyString.ofOptional("www.upwork.com"),
                Optional.ofNullable(new Phone("99272758"))),
            new Client(
                new Name("Charlotte Oliveiro"),
                new Email("charlotte@example.com"),
                Optional.ofNullable(new YearOfBirth("1999")),
                NonEmptyString.ofOptional("www.guru.com"),
                Optional.ofNullable(new Phone("91234567"))),
            new Client(
                new Name("David Li"),
                new Email("lidavid@example.com"),
                Optional.ofNullable(new YearOfBirth("1998")),
                NonEmptyString.ofOptional("www.fiverr.com"),
                Optional.ofNullable(new Phone("91031282"))),
            new Client(
                new Name("Irfan Ibrahim"),
                new Email("irfan@example.com"),
                Optional.ofNullable(new YearOfBirth("1964")),
                NonEmptyString.ofOptional("www.linkedin.com"),
                Optional.ofNullable(new Phone("92492021"))),
            new Client(
                new Name("Roy Balakrishnan"),
                new Email("royb@example.com"),
                Optional.ofNullable(new YearOfBirth("2001")),
                NonEmptyString.ofOptional("www.simplyhired.com"),
                Optional.ofNullable(new Phone("92624417")))
        };
    }

    public static Project[] getSampleProjects() {
        return new Project[]{
            new Project(
                NonEmptyString.of("Bing"),
                ProjectStatus.NOT_STARTED,
                new Email("johndoe@gmail.com"),
                NonEmptyString.ofOptional("fiver"),
                Optional.ofNullable("Create the next google AKA bing"),
                LocalDate.now(),
                Optional.empty()
            ),
            new Project(
                NonEmptyString.of("Havard2.0"),
                ProjectStatus.IN_PROGRESS,
                new Email("EluidKipchoge@gmail.com"),
                NonEmptyString.ofOptional("Behind the alley"),
                Optional.ofNullable("University on the streets"),
                LocalDate.now(),
                Optional.ofNullable(LocalDate.now())
            ),
            new Project(
                NonEmptyString.of("Build Skynet"),
                ProjectStatus.DONE,
                new Email("VladPutin@hotmale.com"),
                NonEmptyString.ofOptional("Russia"),
                Optional.ofNullable("Conquer the world"),
                LocalDate.now(),
                Optional.empty()
            )
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Project sampleProject : getSampleProjects()) {
            sampleAb.addProject(sampleProject);
        }
        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
        }
        return sampleAb;
    }

}

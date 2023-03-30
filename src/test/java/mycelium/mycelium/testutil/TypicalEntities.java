package mycelium.mycelium.testutil;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import mycelium.mycelium.model.AddressBook;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalEntities {
    public static final Client FUTA = new ClientBuilder().withName("Rantaro Futanari")
        .withEmail("Futanari@example.com")
        .withYearOfBirth("1958")
        .withSource("www.nippon.com")
        .withMobileNumber("99910102").build();
    public static final Client RANTARO = new ClientBuilder().withName("Rantaro Futanari")
        .withEmail("Rantaro@example.com")
        .withYearOfBirth("1958")
        .withSource("www.futanariinflation.com")
        .withMobileNumber("99923442").build();
    public static final Client WEST = new ClientBuilder().withName("Kanye West")
        .withEmail("kanye@kardashians.org")
        .withYearOfBirth("2000")
        .withSource("www.kanyewest.com")
        .withMobileNumber("99923444").build();

    /* A simpler project, with several empty fields. */
    public static final Project
        BARD =
        new ProjectBuilder().withName("Bard")
            .withClientEmail("jamar@bard.org")
            .withStatus(ProjectStatus.NOT_STARTED)
            .withSource(null)
            .withDescription(null)
            .withDescription(null)
            .withAcceptedOn(LocalDate.of(2022, 3, 14))
            .withDeadline((LocalDate) null)
            .build();

    /* A project without any empty fields - all fields explicity provided. */
    public static final Project
        BING =
        new ProjectBuilder().withName("Bing")
            .withClientEmail("jamal@bing.org")
            .withStatus(ProjectStatus.IN_PROGRESS)
            .withSource("fiverr.com")
            .withDescription("Bing the new Google")
            .withAcceptedOn(LocalDate.of(2022, 3, 14))
            .withDeadline(LocalDate.of(2022, 4, 14))
            .build();

    /* And something in between. */
    public static final Project
        BOSE =
        new ProjectBuilder().withName("Bose")
            .withClientEmail("jamae@bose.org")
            .withStatus(ProjectStatus.DONE)
            .withSource(null)
            .withDescription("Audiophile headphones site")
            .withAcceptedOn(LocalDate.of(2022, 4, 20))
            .withDeadline((LocalDate) null)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEntities() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical projects and clients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Client cli : getTypicalClients()) {
            ab.addClient(cli);
        }
        for (Project pro : getTypicalProjects()) {
            ab.addProject(pro);
        }
        return ab;
    }

    public static List<Client> getTypicalClients() {
        return Arrays.asList(FUTA, RANTARO, WEST);
    }

    public static List<Project> getTypicalProjects() {
        return Arrays.asList(BARD, BING, BOSE);
    }
}

package mycelium.mycelium.testutil;

import static mycelium.mycelium.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static mycelium.mycelium.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static mycelium.mycelium.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static mycelium.mycelium.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static mycelium.mycelium.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static mycelium.mycelium.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static mycelium.mycelium.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static mycelium.mycelium.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static mycelium.mycelium.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static mycelium.mycelium.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mycelium.mycelium.model.AddressBook;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.person.Person;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {
    // Temporary placement of clients
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
        .withEmail("alice@example.com")
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

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
        .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
        .withPhone("94351253")
        .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
        .withAddress("311, Clementi Ave 2, #02-25")
        .withEmail("johnd@example.com").withPhone("98765432")
        .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
        .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
        .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
        .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
        .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
        .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
        .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
        .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
        .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
        .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical projects and clients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        // TODO remove this adding of persons
        for (Person per : getTypicalPersons()) {
            ab.addPerson(per);
        }
        for (Client cli : getTypicalClients()) {
            ab.addClient(cli);
        }
        for (Project pro : getTypicalProjects()) {
            ab.addProject(pro);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Client> getTypicalClients() {
        return Arrays.asList(FUTA, RANTARO, WEST);
    }

    public static List<Project> getTypicalProjects() {
        return Arrays.asList(BARD, BING, BOSE);
    }
}

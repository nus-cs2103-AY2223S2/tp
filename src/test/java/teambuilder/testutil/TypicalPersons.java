package teambuilder.testutil;

import static teambuilder.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static teambuilder.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static teambuilder.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static teambuilder.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static teambuilder.logic.commands.CommandTestUtil.VALID_MAJOR_AMY;
import static teambuilder.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static teambuilder.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static teambuilder.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static teambuilder.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static teambuilder.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static teambuilder.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static teambuilder.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import teambuilder.model.TeamBuilder;
import teambuilder.model.person.Name;
import teambuilder.model.person.Person;
import teambuilder.model.tag.Tag;
import teambuilder.model.team.Desc;
import teambuilder.model.team.Team;
import teambuilder.model.team.TeamName;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com").withPhone("94351253")
            .withMajor("computer science").withTags("Python").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com").withPhone("98765432")
            .withMajor("computer science").withTags("C", "PgSQL").withTeams("TeamA").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withMajor("computer science").withTeams("TeamA")
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withMajor("computer science")
            .withTags("React Native").withTeams("TeamB").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withMajor("computer science")
            .withTeams("TeamB").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withMajor("computer science").withTeams("TeamC")
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withMajor("computer science").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withMajor("computer science").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withMajor("computer science").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withMajor(VALID_MAJOR_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withMajor(VALID_MAJOR_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final List<Name> IN_TEAM_A = Arrays.asList(BENSON.getName(), CARL.getName());

    public static final List<Name> IN_TEAM_B = Arrays.asList(DANIEL.getName(), ELLE.getName());

    public static final List<Name> IN_TEAM_C = Arrays.asList(FIONA.getName());

    public static final Team TEAM_A = new Team(
            new Team(new TeamName("TeamA"), new Desc("Project group TeamA"), new HashSet<>()),
            new HashSet<Name>(IN_TEAM_A));
    public static final Team TEAM_B = new Team(new Team(new TeamName("TeamB"), new Desc("Project group TeamB"),
            new HashSet<Tag>(Arrays.asList(new Tag("Java"), new Tag("ReactNative")))), new HashSet<>(IN_TEAM_B));
    public static final Team TEAM_C = new Team(new Team(new TeamName("TeamC"), new Desc("Project group TeamC"),
            new HashSet<Tag>(Arrays.asList(new Tag("PgSQL"), new Tag("C")))), new HashSet<>(IN_TEAM_C));

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static TeamBuilder getTypicalAddressBook() {
        TeamBuilder ab = new TeamBuilder();

        for (Team team : getTypicalTeams()) {
            ab.addTeam(team);
        }
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Team> getTypicalTeams() {
        return new ArrayList<>(Arrays.asList(TEAM_A, TEAM_B, TEAM_C));
    }
}

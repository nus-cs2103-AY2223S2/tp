package codoc.testutil;

import static codoc.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static codoc.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static codoc.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_LINKEDIN_AMY;
import static codoc.logic.commands.CommandTestUtil.VALID_LINKEDIN_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static codoc.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_SKILL_CSHARP;
import static codoc.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import codoc.model.Codoc;
import codoc.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withLinkedin("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withGithub("alice-Pauline")
            .withSkills("python").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withLinkedin("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withGithub("bensonmeier")
            .withSkills("java", "python").withMods("AY2223S1 CS1101S", "AY2223S1 CS1231S").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withGithub("Carl-Kurz")
            .withEmail("heinz@example.com").withLinkedin("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withGithub("d4nielMeier")
            .withEmail("cornelia@example.com").withLinkedin("10th street").withSkills("python").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withGithub("Elle-meyer")
            .withEmail("werner@example.com").withLinkedin("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withGithub("F1onaKunz")
            .withEmail("lydia@example.com").withLinkedin("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withGithub("george-b3st")
            .withEmail("anna@example.com").withLinkedin("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withGithub("hoony-m313r")
            .withEmail("stefan@example.com").withLinkedin("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withGithub("1d4-mueLLer")
            .withEmail("hans@example.com").withLinkedin("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withGithub(VALID_GITHUB_AMY)
            .withEmail(VALID_EMAIL_AMY).withLinkedin(VALID_LINKEDIN_AMY).withSkills(VALID_SKILL_CSHARP).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withGithub(VALID_GITHUB_BOB)
            .withEmail(VALID_EMAIL_BOB).withLinkedin(VALID_LINKEDIN_BOB).withSkills(VALID_SKILL_JAVA,
                    VALID_SKILL_CSHARP)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

    /**
     * Returns an {@code Codoc} with all the typical persons.
     */
    public static Codoc getTypicalCodoc() {
        Codoc ab = new Codoc();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

package seedu.dengue.testutil;

import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATEANDTIME_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATEANDTIME_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAge("123, Jurong West Ave 6, #08-111").withDateAndTime("alice@example.com")
            .withPostal("943512")
            .withTags("friends").build();

    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAge("311, Clementi Ave 2, #02-25")
            .withDateAndTime("johnd@example.com").withPostal("987654")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPostal("953525")
            .withDateAndTime("heinz@example.com").withAge("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPostal("876525")
            .withDateAndTime("cornelia@example.com").withAge("10th street").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPostal("948222")
            .withDateAndTime("werner@example.com").withAge("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPostal("948242")
            .withDateAndTime("lydia@example.com").withAge("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPostal("948244")
            .withDateAndTime("anna@example.com").withAge("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPostal("848242")
            .withDateAndTime("stefan@example.com").withAge("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPostal("848213")
            .withDateAndTime("hans@example.com").withAge("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPostal(VALID_POSTAL_AMY)
            .withDateAndTime(VALID_DATEANDTIME_AMY).withAge(VALID_AGE_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPostal(VALID_POSTAL_BOB)
            .withDateAndTime(VALID_DATEANDTIME_BOB).withAge(VALID_AGE_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code DengueHotspotTracker} with all the typical persons.
     */
    public static DengueHotspotTracker getTypicalDengueHotspotTracker() {
        DengueHotspotTracker ab = new DengueHotspotTracker();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

package seedu.dengue.testutil;

import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_VARIANT_DENV1;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_VARIANT_DENV2;

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
            .withAge("21").withDate("2023-03-05")
            .withPostal("943512")
            .withVariants("DENV1").build();

    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAge("85").withDate("2022-03-05").withPostal("987654")
            .withVariants("DENV2", "DENV4").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPostal("953525")
            .withDate("2023-02-02").withAge("35").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPostal("876525")
            .withDate("2023-01-01").withAge("143").withVariants("DENV3").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPostal("948222")
            .withDate("2022-12-31").withAge("65").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPostal("948247")
            .withDate("2022-01-05").withAge("5").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPostal("948244")
            .withDate("2023-01-05").withAge("12").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPostal("848242")
            .withDate("1850-01-02").withAge("1").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPostal("848213")
            .withDate("1996-10-01").withAge("27").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPostal(VALID_POSTAL_AMY)
            .withDate(VALID_DATE_AMY).withAge(VALID_AGE_AMY).withVariants(VALID_VARIANT_DENV2).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPostal(VALID_POSTAL_BOB)
            .withDate(VALID_DATE_BOB).withAge(VALID_AGE_BOB).withVariants(VALID_VARIANT_DENV1, VALID_VARIANT_DENV2)
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

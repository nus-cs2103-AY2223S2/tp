package seedu.dengue.testutil;

import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_ALICE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_BENSON;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_CARL;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_DANIEL;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_ELLE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_FIONA;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_GEORGE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_HOON;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_IDA;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_ALICE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_BENSON;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_CARL;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_DANIEL;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_ELLE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_FIONA;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_GEORGE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_HOON;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_IDA;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_ALICE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_BENSON;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_CARL;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_DANIEL;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_ELLE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_FIONA;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_GEORGE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_HOON;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_IDA;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_ALICE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_BENSON;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_CARL;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_DANIEL;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_ELLE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_FIONA;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_GEORGE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_HOON;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_IDA;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_VARIANT_DENV1_UPPERCASE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_VARIANT_DENV2_UPPERCASE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_VARIANT_DENV3_UPPERCASE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_VARIANT_DENV4_UPPERCASE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName(VALID_NAME_ALICE)
            .withAge(VALID_AGE_ALICE).withDate(VALID_DATE_ALICE)
            .withPostal(VALID_POSTAL_ALICE)
            .withVariants(VALID_VARIANT_DENV1_UPPERCASE).build();

    public static final Person BENSON = new PersonBuilder().withName(VALID_NAME_BENSON)
            .withAge(VALID_AGE_BENSON).withDate(VALID_DATE_BENSON).withPostal(VALID_POSTAL_BENSON)
            .withVariants(VALID_VARIANT_DENV2_UPPERCASE, VALID_VARIANT_DENV4_UPPERCASE).build();
    public static final Person CARL = new PersonBuilder().withName(VALID_NAME_CARL).withPostal(VALID_POSTAL_CARL)
        .withDate(VALID_DATE_CARL).withAge(VALID_AGE_CARL).build();
    public static final Person DANIEL = new PersonBuilder().withName(VALID_NAME_DANIEL)
            .withPostal(VALID_POSTAL_DANIEL).withDate(VALID_DATE_DANIEL)
            .withAge(VALID_AGE_DANIEL).withVariants(VALID_VARIANT_DENV3_UPPERCASE).build();
    public static final Person ELLE = new PersonBuilder().withName(VALID_NAME_ELLE).withPostal(VALID_POSTAL_ELLE)
            .withDate(VALID_DATE_ELLE).withAge(VALID_AGE_ELLE).build();
    public static final Person FIONA = new PersonBuilder().withName(VALID_NAME_FIONA).withPostal(VALID_POSTAL_FIONA)
            .withDate(VALID_DATE_FIONA).withAge(VALID_AGE_FIONA).build();
    public static final Person GEORGE = new PersonBuilder().withName(VALID_NAME_GEORGE)
            .withPostal(VALID_POSTAL_GEORGE).withDate(VALID_DATE_GEORGE).withAge(VALID_AGE_GEORGE).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName(VALID_NAME_HOON).withPostal(VALID_POSTAL_HOON)
            .withDate(VALID_DATE_HOON).withAge(VALID_AGE_HOON).build();
    public static final Person IDA = new PersonBuilder().withName(VALID_NAME_IDA).withPostal(VALID_POSTAL_IDA)
            .withDate(VALID_DATE_IDA).withAge(VALID_AGE_IDA).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPostal(VALID_POSTAL_AMY)
            .withDate(VALID_DATE_AMY).withAge(VALID_AGE_AMY).withVariants(VALID_VARIANT_DENV2_UPPERCASE).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPostal(VALID_POSTAL_BOB)
            .withDate(VALID_DATE_BOB).withAge(VALID_AGE_BOB)
            .withVariants(VALID_VARIANT_DENV1_UPPERCASE, VALID_VARIANT_DENV2_UPPERCASE)
            .build();

    // Manually added

    public static final Person AMISH = new PersonBuilder().withName("Amish Bii").withPostal(VALID_POSTAL_CARL)
            .withDate(VALID_DATE_BOB).withAge(VALID_AGE_BOB)
            .withVariants(VALID_VARIANT_DENV2_UPPERCASE, VALID_VARIANT_DENV1_UPPERCASE)
            .build();

    public static final Person BECCA = new PersonBuilder().withName("Becca Poo").withPostal("135211")
            .withDate(VALID_DATE_AMY).withAge(VALID_AGE_AMY)
            .withVariants(VALID_VARIANT_DENV1_UPPERCASE, VALID_VARIANT_DENV2_UPPERCASE)
            .build();

    public static final Person CAROL = new PersonBuilder().withName("Carol See").withPostal(VALID_POSTAL_FIONA)
            .withDate(VALID_DATE_HOON).withAge(VALID_AGE_IDA).build();


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
        return new ArrayList<>(Arrays.asList(
                ALICE, BENSON, CARL, DANIEL,
                ELLE, FIONA, GEORGE));
    }
}

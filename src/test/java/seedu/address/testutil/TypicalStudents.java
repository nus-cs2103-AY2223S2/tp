package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIENDLY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_QUIET;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.person.Class;
import seedu.address.model.person.PcClass;
import seedu.address.model.person.student.Student;


/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {
    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withClass("1A")
            .withComment("Good Student!")
            .withIndexNumber("1")
            .withTags("friends").build();

    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withAge("20")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withImage("Insert student image here!")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withClass("1A")
            .withIndexNumber("2")
            .withTags("owesMoney", "friends").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withClass("1A")
            .withAddress("wall street").withIndexNumber("3").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withClass("1A").withAddress("10th street")
            .withTags("friends").withIndexNumber("4").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withIndexNumber("5")
            .withClass("1A").withAddress("michegan ave").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withIndexNumber("6")
            .withClass("1A").withAddress("little tokyo").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withIndexNumber("7")
            .withClass("1A").withAddress("4th street").build();

    // Students not in the PowerConnectParentList
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withClass("1A").withAddress("little india").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withClass("1A").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withClass(VALID_CLASS_AMY).withIndexNumber(VALID_INDEX_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIENDLY).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withClass(VALID_CLASS_BOB).withIndexNumber(VALID_INDEX_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_QUIET, VALID_TAG_FRIENDLY)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code Parents} with all the typical parents.
     */
    public static PcClass getTypicalPowerConnectStudents() {
        PcClass classes = new PcClass();
        for (Student student : getTypicalStudents()) {
            Class curr = student.getStudentClass();
            if (!classes.hasClass(curr)) {
                curr.addStudent(student);
                classes.addClass(curr);
            } else {
                Class temp = curr;
                if (!curr.getStudents().contains(student)) {
                    curr.addStudent(student);
                    classes.setClass(temp, curr);
                }
            }
        }
        return classes;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

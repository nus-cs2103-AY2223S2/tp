package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARENT_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARENT_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalPersons {

    public static final seedu.address.model.student.Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withParentPhone("98741555")
            .withTags("friends").build();
    public static final seedu.address.model.student.Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withParentPhone("98741555")
            .withTags("owesMoney", "friends").build();
    public static final seedu.address.model.student.Student CARL = new StudentBuilder().withName("Carl Kurz").withPhone("95352563")
            .withParentPhone("98741555").withEmail("heinz@example.com").withAddress("wall street").build();
    public static final seedu.address.model.student.Student DANIEL = new StudentBuilder().withName("Daniel Meier").withPhone("87652533")
            .withParentPhone("98741555").withEmail("cornelia@example.com").withAddress("10th street")
            .withTags("friends").build();
    public static final seedu.address.model.student.Student ELLE = new StudentBuilder().withName("Elle Meyer").withPhone("9482224")
            .withParentPhone("98741555").withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final seedu.address.model.student.Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withParentPhone("98741555").withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final seedu.address.model.student.Student GEORGE = new StudentBuilder().withName("George Best").withPhone("9482442")
            .withParentPhone("98741555").withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final seedu.address.model.student.Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withParentPhone("98741555").withEmail("stefan@example.com").withAddress("little india").build();
    public static final seedu.address.model.student.Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withParentPhone("98741555").withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final seedu.address.model.student.Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withParentPhone(VALID_PARENT_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final seedu.address.model.student.Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withParentPhone(VALID_PARENT_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code Mathutoring} with all the typical persons.
     */
    public static seedu.address.model.Mathutoring getTypicalAddressBook() {
        seedu.address.model.Mathutoring ab = new seedu.address.model.Mathutoring();
        for (seedu.address.model.student.Student student : getTypicalPersons()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<seedu.address.model.student.Student> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

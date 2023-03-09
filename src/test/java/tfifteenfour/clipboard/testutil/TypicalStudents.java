package tfifteenfour.clipboard.testutil;

import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_MODULE_CS2105;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tfifteenfour.clipboard.model.AddressBook;
import tfifteenfour.clipboard.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {
    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withPhone("94351253")
            .withEmail("alice@example.com")
            .withStudentId("A0123456M")
            .withModules("CS2105").withTags("Team1").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withPhone("98765432")
            .withEmail("johnd@example.com")
            .withStudentId("A7890123R")
            .withModules("CS2103T").withTags("Team1", "Team2").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withStudentId("A6789123H")
            .withModules("CS2101").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withStudentId("A8905678J")
            .withModules("CS2101")
            .withTags("Team1").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withStudentId("A3456129K")
            .withModules("CS2101", "CS2103T").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withStudentId("A6789126Y")
            .withModules("CS2101").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withStudentId("A5678234W")
            .withModules("CS4225").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withStudentId("A2438243C").withModules("CS2103T").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withStudentId("A0012345K").withModules("CS3223").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withStudentId(VALID_STUDENTID_AMY).withModules(VALID_MODULE_CS2105).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withStudentId(VALID_STUDENTID_BOB).withModules(VALID_MODULE_CS2105).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

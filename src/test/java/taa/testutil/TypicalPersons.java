package taa.testutil;

import static taa.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static taa.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static taa.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static taa.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import taa.model.ClassList;
import taa.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Student ALICE = new PersonBuilder().withName("Alice Pauline")
            .withTags("friends").build();
    public static final Student BENSON = new PersonBuilder().withName("Benson Meier")
            .withTags("owesMoney", "friends").build();
    public static final Student CARL = new PersonBuilder().withName("Carl Kurz").build();
    public static final Student DANIEL = new PersonBuilder().withName("Daniel Meier").withTags("friends").build();
    public static final Student ELLE = new PersonBuilder().withName("Elle Meyer").build();
    public static final Student FIONA = new PersonBuilder().withName("Fiona Kunz").build();
    public static final Student GEORGE = new PersonBuilder().withName("George Best").build();

    // Manually added
    public static final Student HOON = new PersonBuilder().withName("Hoon Meier").build();
    public static final Student IDA = new PersonBuilder().withName("Ida Mueller").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new PersonBuilder().withName(VALID_NAME_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Student BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code ClassList} with all the typical persons.
     */
    public static ClassList getTypicalAddressBook() {
        ClassList ab = new ClassList();
        for (Student student : getTypicalPersons()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

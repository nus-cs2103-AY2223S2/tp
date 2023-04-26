package taa.testutil;

import static taa.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static taa.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static taa.logic.commands.CommandTestUtil.VALID_TAG_LAB02;
import static taa.logic.commands.CommandTestUtil.VALID_TAG_TUT_15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import taa.model.ClassList;
import taa.model.student.Attendance;
import taa.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalPersons {
    private static final Attendance atd = new Attendance("0;0;0;0;0;0;0;0;0;0;0;0",
            "-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1");
    public static final Student ALICE = new PersonBuilder().withName("Alice Pauline").withAttendance(atd)
            .withTags("T01").build();
    public static final Student BENSON = new PersonBuilder().withName("Benson Meier").withAttendance(atd)
            .withTags("T01", "L01").build();
    public static final Student CARL = new PersonBuilder().withName("Carl Kurz")
                    .withAttendance(atd).build();
    public static final Student DANIEL = new PersonBuilder().withName("Daniel Meier").withAttendance(atd)
                    .withTags("R01").build();
    public static final Student ELLE = new PersonBuilder().withName("Elle Meyer").withAttendance(atd).build();
    public static final Student FIONA = new PersonBuilder().withName("Fiona Kunz").withAttendance(atd).build();
    public static final Student GEORGE = new PersonBuilder().withName("George Best").withAttendance(atd).build();

    // Manually added
    public static final Student HOON = new PersonBuilder().withName("Hoon Meier").withAttendance(atd).build();
    public static final Student IDA = new PersonBuilder().withName("Ida Mueller").withAttendance(atd).build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withAttendance(atd).withTags(VALID_TAG_LAB02).build();
    public static final Student BOB = new PersonBuilder().withName(VALID_NAME_BOB).withAttendance(atd)
            .withTags(VALID_TAG_TUT_15, VALID_TAG_LAB02).build();
    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code ClassList} with all the typical persons.
     */
    public static ClassList getTypicalTaaData() {
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

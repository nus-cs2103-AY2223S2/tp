package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalMathutoring;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.testutil.StudentBuilder;



public class MathutoringTest {

    private final Mathutoring mathutoring = new Mathutoring();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), mathutoring.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mathutoring.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMathutoring_replacesData() {
        Mathutoring newData = getTypicalMathutoring();
        mathutoring.resetData(newData);
        assertEquals(newData, mathutoring);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        MathutoringStub newData = new MathutoringStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> mathutoring.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mathutoring.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInMathutoring_returnsFalse() {
        assertFalse(mathutoring.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInMathutoring_returnsTrue() {
        mathutoring.addStudent(ALICE);
        assertTrue(mathutoring.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInMathutoring_returnsTrue() {
        mathutoring.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(mathutoring.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> mathutoring.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyMathutoring whose students list can violate interface constraints.
     */
    private static class MathutoringStub implements ReadOnlyMathutoring {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        MathutoringStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }
    }

}

package seedu.address.logic.commands.student;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PcClass;
import seedu.address.model.person.exceptions.DuplicateStudentException;
import seedu.address.model.person.student.Student;
import seedu.address.testutil.TypicalStudents;

class StudentAddCommandTest {
    private PcClass studentList = TypicalStudents.getTypicalPowerConnectStudents();
    private Model model = new ModelManager(studentList, new UserPrefs());
    private Student student1 = TypicalStudents.AMY; // new student to add into list
    private Student student2 = TypicalStudents.BENSON; // existing student in list
    private Student student3 = TypicalStudents.IDA; // new student to add into list
    @Test
    public void executeTest() {
        // adding an existing student to the student list
        assertTrue(model.hasStudent(student2));
        assertThrows(DuplicateStudentException.class, () -> model.addStudent(student2,
                student2.getStudentClass()));

        // adding AMY into the student list when AMY is not INSIDE it
        assertFalse(model.hasStudent(student1));
        assertDoesNotThrow(() -> model.addStudent(student1, student1.getStudentClass()));

        // adding AMY into the student list when AMY is already inside it
        assertTrue(model.hasStudent(student1));
        assertThrows(DuplicateStudentException.class, () -> model.addStudent(student1,
                student1.getStudentClass()));

    }

    @Test
    public void equalsTest() {
        StudentAddCommand addCommand1 = new StudentAddCommand(student1);
        StudentAddCommand addCommand2 = new StudentAddCommand(student2);
        StudentAddCommand addCommand3 = new StudentAddCommand(student1);
        // same object -> returns true
        assertTrue(addCommand1.equals(addCommand1));
        // same student input -> returns true
        assertTrue(addCommand1.equals(addCommand3));
        // sane student input -> returns false
        assertFalse(addCommand1.equals(addCommand2));
        // null input -> returns false
        assertFalse(addCommand1.equals(null));
    }
}

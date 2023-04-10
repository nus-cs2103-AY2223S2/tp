package seedu.address.logic.commands.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PcClass;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.StudentNameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalStudents;

public class StudenFindCommandTest {

    private PcClass studentList = TypicalStudents.getTypicalPowerConnectStudents();
    private Model model = new ModelManager(studentList, new UserPrefs());
    private Student student1 = TypicalStudents.AMY; // new student to add into list
    private Student student2 = TypicalStudents.BENSON; // existing student in list
    private Student student3 = TypicalStudents.IDA; // new student to add into list
    @Test
    public void executeTest() {
        List<String> keywords = List.of("Benson");
        StudentFindCommand testCommand = new StudentFindCommand(student2.getStudentClass().toString(),
                new StudentNameContainsKeywordsPredicate(keywords));
        ModelManager expectedModel = new ModelManager(model.getPcClass(), new UserPrefs());
        expectedModel.updateFilteredStudentList(preparePredicate("Benson"));
        testCommand.execute(model);
        assertEquals(expectedModel.getFilteredStudentList(), model.getFilteredStudentList());

    }
    /**
     * Parses {@code userInput} into a {@code StudentNameContainsKeywordsPredicate}.
     */
    private StudentNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new StudentNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    @Test
    public void equalsTest() {
        StudentFindCommand findCommand1 = new StudentFindCommand(student1.getStudentClass().toString(),
                new StudentNameContainsKeywordsPredicate(List.of("Amy")));
        StudentFindCommand findCommand2 = new StudentFindCommand(student2.getStudentClass().toString(),
                new StudentNameContainsKeywordsPredicate(List.of("Benson")));
        StudentFindCommand findCommand3 = new StudentFindCommand(student1.getStudentClass().toString(),
                new StudentNameContainsKeywordsPredicate(List.of("Amy")));
        // same object -> returns true
        assertTrue(findCommand1.equals(findCommand1));
        // same student input -> returns true
        assertTrue(findCommand1.equals(findCommand3));
        // sane student input -> returns false
        assertFalse(findCommand1.equals(findCommand2));
        // null input -> returns false
        assertFalse(findCommand1.equals(null));
    }
}

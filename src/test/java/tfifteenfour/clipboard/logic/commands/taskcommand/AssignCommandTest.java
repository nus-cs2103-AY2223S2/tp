package tfifteenfour.clipboard.logic.commands.taskcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.testutil.Assert.assertThrows;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_OUT_OF_BOUND;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.SelectCommand;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.StudentWithGrades;
import tfifteenfour.clipboard.model.task.Task;
import tfifteenfour.clipboard.testutil.TypicalModel;

public class AssignCommandTest {
    private static int TEST_GRADE = 50;
    private Model model;
    private Task selectedTask;
    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        this.model.getCurrentSelection().setCurrentPage(PageType.TASK_STUDENT_PAGE);
        selectedTask = model.getCurrentSelection().getSelectedTask();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.TASK_STUDENT_PAGE);
    }

    @Test
    public void execute_validSingleIndex_success() {
        List<StudentWithGrades> lastShownList = selectedTask.getUnmodifiableStudentList();
        Student firstStudent = lastShownList.get(INDEX_FIRST.getZeroBased());
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST, TEST_GRADE);

        String expectedMessage = String.format(AssignCommand.MESSAGE_SUCCESS,
                selectedTask, firstStudent.getName());
        Model expectedModel = model.copy();

        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_onTaskPage_throwCommandException() {
        actualSelection.setCurrentPage(PageType.TASK_PAGE);
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST, TEST_GRADE);
        assertThrows(CommandException.class, () -> assignCommand.execute(model));
    }

    @Test
    public void execute_onGroupPage_throwCommandException() {
        actualSelection.setCurrentPage(PageType.GROUP_PAGE);
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST, TEST_GRADE);
        assertThrows(CommandException.class, () -> assignCommand.execute(model));
    }

    @Test
    public void execute_indexOutOfBound_throwCommandException() {
        actualSelection.setCurrentPage(PageType.TASK_STUDENT_PAGE);
        AssignCommand assignCommand = new AssignCommand(INDEX_OUT_OF_BOUND, TEST_GRADE);
        assertThrows(CommandException.class, () -> assignCommand.execute(model));
    }

    @Test
    public void equals() {
        AssignCommand command1 = new AssignCommand(INDEX_FIRST, TEST_GRADE);
        AssignCommand command2 = new AssignCommand(INDEX_FIRST, TEST_GRADE);
        AssignCommand command3 = new AssignCommand(INDEX_SECOND, TEST_GRADE);

        SelectCommand differentCommand = new SelectCommand(INDEX_FIRST);

        // Test for equality
        assertEquals(command1, command2);
        assertEquals(command2, command1);

        assertNotEquals(command3, command1);
        assertNotEquals(command1, command3);
        assertNotEquals(command1, differentCommand);
    }
}

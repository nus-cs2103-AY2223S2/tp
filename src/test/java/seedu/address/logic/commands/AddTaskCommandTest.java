package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalMathutoring;
import static seedu.address.testutil.TypicalTasks.VALID_TASK_1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;


class AddTaskCommandTest {

    private Model model = new ModelManager(getTypicalMathutoring(), new UserPrefs());

    @Test
    public void constructor_nullTaskAndIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, null));
    }

    @Test
    void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        AddTaskCommand addTaskCommand = new AddTaskCommand(outOfBoundIndex, VALID_TASK_1);

        assertCommandFailure(addTaskCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_addValidTask_success() throws Exception {
        Task validTask = new TaskBuilder().build();
        Student studentToAddTaskTo = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        CommandResult commandResult = new AddTaskCommand(INDEX_FIRST_STUDENT, validTask).execute(model);

        assertEquals(String.format(AddTaskCommand.MESSAGE_ADD_TASK_SUCCESS, studentToAddTaskTo.getName(),
                validTask.getName()), commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        Task firstTask = new TaskBuilder().withName("Do Homework").build();
        Task secondTask = new TaskBuilder().withName("Complete Math Paper").build();
        AddTaskCommand addFirstTaskCommand = new AddTaskCommand(INDEX_FIRST_STUDENT, firstTask);
        AddTaskCommand addSecondTaskCommand = new AddTaskCommand(INDEX_FIRST_STUDENT, secondTask);

        // same object -> returns true
        assertTrue(addFirstTaskCommand.equals(addFirstTaskCommand));

        // same values -> returns true
        AddTaskCommand addFirstTaskCommandCopy = new AddTaskCommand(INDEX_FIRST_STUDENT, firstTask);
        assertTrue(addFirstTaskCommand.equals(addFirstTaskCommandCopy));

        // different types -> returns false
        assertFalse(addFirstTaskCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstTaskCommand.equals(null));

        // different student -> returns false
        assertFalse(addFirstTaskCommand.equals(addSecondTaskCommand));
    }
}

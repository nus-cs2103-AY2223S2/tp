package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskBook;
import seedu.address.model.TaskBookModel;
import seedu.address.model.TaskBookModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.DeadlineTask;
import seedu.address.model.task.Score;
import seedu.address.model.task.Task;
import seedu.address.testutil.DeadlineTaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkCommand}.
 */
public class UnmarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private TaskBookModel taskBookModel = new TaskBookModelManager(new TaskBook(), new UserPrefs());


    @Test
    public void execute_validIndex_success() throws Exception {
        DeadlineTask validDeadlineTask = new DeadlineTaskBuilder().build();
        taskBookModel.addTask(validDeadlineTask);
        Task taskToUnmark = taskBookModel.getFilteredTaskList().get(INDEX_FIRST_PERSON.getZeroBased());
        Score score = new Score(4);
        taskToUnmark.setScore(score);
        CommandResult commandResult = new UnmarkCommand(INDEX_FIRST_PERSON).execute(model, taskBookModel);
        Task unmarkedTask = taskBookModel.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        assertEquals(String.format(UnmarkCommand.MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask),
                commandResult.getFeedbackToUser());
        assertFalse(taskToUnmark.isDone());
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(taskBookModel.getFilteredTaskList().size() + 1);
        UnmarkCommand markCommand = new UnmarkCommand(outOfBoundIndex);

        assertCommandFailure(markCommand, model, taskBookModel, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }
}

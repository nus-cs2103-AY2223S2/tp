package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalDeadlineTasks.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

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
public class MarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private TaskBookModel taskBookModel = new TaskBookModelManager(new TaskBook(), new UserPrefs());


    @Test
    public void execute_validIndex_success() throws Exception {
        DeadlineTask validDeadlineTask = new DeadlineTaskBuilder().build();
        taskBookModel.addTask(validDeadlineTask);
        Task taskToMark = taskBookModel.getFilteredTaskList().get(INDEX_FIRST_PERSON.getZeroBased());
        Score score = new Score(4);
        CommandResult commandResult = new MarkCommand(INDEX_FIRST_PERSON, score).execute(model, taskBookModel);

        assertEquals(String.format(MarkCommand.MESSAGE_MARK_TASK_SUCCESS, taskToMark, score.toString()),
                commandResult.getFeedbackToUser());
        assertTrue(taskToMark.isDone());
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(taskBookModel.getFilteredTaskList().size() + 1);
        Score score = new Score(4);
        MarkCommand markCommand = new MarkCommand(outOfBoundIndex, score);

        assertCommandFailure(markCommand, model, taskBookModel, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidScore_throwsCommandException() {
        DeadlineTask validDeadlineTask = new DeadlineTaskBuilder().build();
        taskBookModel.addTask(validDeadlineTask);
        Score score = new Score(6);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, score);
        assertCommandFailure(markCommand, model, taskBookModel, Score.MESSAGE_CONSTRAINTS);
    }

}

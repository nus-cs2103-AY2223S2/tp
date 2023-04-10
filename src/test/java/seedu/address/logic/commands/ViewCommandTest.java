package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskBook;
import seedu.address.model.TaskBookModel;
import seedu.address.model.TaskBookModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.DeadlineTask;
import seedu.address.testutil.DeadlineTaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ViewCommandTest {

    private Model model;
    private Model expectedModel;
    private TaskBookModel taskBookModel;
    private TaskBookModel expectedTaskBookModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        taskBookModel = new TaskBookModelManager(new TaskBook(), new UserPrefs());
        expectedTaskBookModel = new TaskBookModelManager(taskBookModel.getTaskBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ViewCommand(), model, taskBookModel, new CommandResult(ViewCommand.MESSAGE_SUCCESS),
            expectedModel, expectedTaskBookModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        DeadlineTask validDeadlineTask = new DeadlineTaskBuilder().build();
        taskBookModel.addTask(validDeadlineTask);
        expectedTaskBookModel.addTask(validDeadlineTask);
        showTaskAtIndex(taskBookModel, INDEX_FIRST_TASK);
        assertCommandSuccess(new ViewCommand(), model, taskBookModel, ViewCommand.MESSAGE_SUCCESS,
            expectedModel, expectedTaskBookModel);
    }
}

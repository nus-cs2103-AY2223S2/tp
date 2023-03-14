package seedu.task.logic.commands;

import static seedu.task.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.task.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.task.testutil.TypicalDailyPlans.getTypicalDailyPlans;
import static seedu.task.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.task.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.task.model.Model;
import seedu.task.model.ModelManager;
import seedu.task.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTaskBook(), new UserPrefs(), getTypicalDailyPlans());
        expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs(), model.getPlanner());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}

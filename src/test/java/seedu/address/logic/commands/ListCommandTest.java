package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFishAtIndex;
import static seedu.address.testutil.TypicalFishes.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FISH;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TankList;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(), new TankList());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), getTypicalTaskList(), new TankList());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandResult expectedResult = new CommandResult(ListCommand.MESSAGE_SUCCESS_FISHES, false, false, true);
        expectedModel.setGuiMode(GuiSettings.GuiMode.DISPLAY_FISHES_TASKS);
        assertCommandSuccess(ListCommand.LIST_FISHES, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showFishAtIndex(model, INDEX_FIRST_FISH);
        CommandResult expectedResult = new CommandResult(ListCommand.MESSAGE_SUCCESS_FISHES, false, false, true);
        expectedModel.setGuiMode(GuiSettings.GuiMode.DISPLAY_FISHES_TASKS);
        assertCommandSuccess(ListCommand.LIST_FISHES, model, expectedResult, expectedModel);
    }
}

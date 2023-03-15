package seedu.wife.logic.commands;

import static seedu.wife.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.wife.logic.commands.CommandTestUtil.showFoodAtIndex;
import static seedu.wife.testutil.TypicalFood.getTypicalWife;
import static seedu.wife.testutil.TypicalIndexes.INDEX_FIRST_FOOD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.wife.model.Model;
import seedu.wife.model.ModelManager;
import seedu.wife.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWife(), new UserPrefs());
        expectedModel = new ModelManager(model.getWife(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showFoodAtIndex(model, INDEX_FIRST_FOOD);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}

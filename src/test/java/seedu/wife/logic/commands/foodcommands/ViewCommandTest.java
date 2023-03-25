package seedu.wife.logic.commands.foodcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.wife.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.wife.logic.commands.CommandTestUtil.showFoodAtIndex;
import static seedu.wife.testutil.TypicalIndex.INDEX_FIRST_FOOD;
import static seedu.wife.testutil.TypicalIndex.INDEX_SECOND_FOOD;
import static seedu.wife.testutil.TypicalWife.getTypicalWife;

import org.junit.jupiter.api.Test;

import seedu.wife.commons.core.Messages;
import seedu.wife.commons.core.index.Index;
import seedu.wife.model.Model;
import seedu.wife.model.ModelManager;
import seedu.wife.model.UserPrefs;
import seedu.wife.model.food.Food;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewCommand}.
 */
public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalWife(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Food foodToView = model.getFilteredFoodList().get(INDEX_FIRST_FOOD.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_FOOD);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_FOOD_SUCCESS, foodToView);

        ModelManager expectedModel = new ModelManager(model.getWife(), new UserPrefs());
        expectedModel.viewFood(foodToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFoodList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
    }


    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFoodAtIndex(model, INDEX_FIRST_FOOD);

        Index outOfBoundIndex = INDEX_SECOND_FOOD;
        // ensures that outOfBoundIndex is still in bounds of wife book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWife().getFoodList().size());

        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_FOOD);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_FOOD);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_FOOD);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different food -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

}

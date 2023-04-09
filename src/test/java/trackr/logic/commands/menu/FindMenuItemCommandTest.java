package trackr.logic.commands.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.commons.core.Messages.MESSAGE_ITEMS_LISTED_OVERVIEW;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.testutil.TypicalMenuItems.CHOCOLATE_COOKIE_M;
import static trackr.testutil.TypicalMenuItems.CUPCAKE_M;
import static trackr.testutil.TypicalMenuItems.HARLEY_SHIRT_M;
import static trackr.testutil.TypicalMenuItems.getTypicalMenu;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ModelManager;
import trackr.model.UserPrefs;
import trackr.model.menu.ItemNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindMenuItemCommandTest {
    private Model model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
            getTypicalMenu(), getTypicalOrderList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
            getTypicalMenu(), getTypicalOrderList(), new UserPrefs());

    @Test
    public void equals() {
        ItemNameContainsKeywordsPredicate firstPredicate =
                new ItemNameContainsKeywordsPredicate(Collections.singletonList("first"));
        ItemNameContainsKeywordsPredicate secondPredicate =
                new ItemNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindMenuItemCommand findFirstCommand = new FindMenuItemCommand(firstPredicate);
        FindMenuItemCommand findSecondCommand = new FindMenuItemCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindMenuItemCommand findFirstCommandCopy = new FindMenuItemCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noItemFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 0,
                ModelEnum.MENUITEM.toString().toLowerCase());
        ItemNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindMenuItemCommand command = new FindMenuItemCommand(predicate);
        expectedModel.updateFilteredItemList(predicate, ModelEnum.MENUITEM);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMenu());
    }

    @Test
    public void execute_multipleKeywords_multipleItemsFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 3,
                ModelEnum.MENUITEM.toString().toLowerCase());
        ItemNameContainsKeywordsPredicate predicate = preparePredicate("Chocolate Cupcakes Harley");
        FindMenuItemCommand command = new FindMenuItemCommand(predicate);
        expectedModel.updateFilteredItemList(predicate, ModelEnum.MENUITEM);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CHOCOLATE_COOKIE_M, CUPCAKE_M, HARLEY_SHIRT_M), model.getFilteredMenu());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ItemNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ItemNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}


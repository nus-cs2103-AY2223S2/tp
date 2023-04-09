package trackr.logic.commands.supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.commons.core.Messages.MESSAGE_ITEMS_LISTED_OVERVIEW;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.testutil.TypicalMenuItems.getTypicalMenu;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.CARL;
import static trackr.testutil.TypicalSuppliers.ELLE;
import static trackr.testutil.TypicalSuppliers.FIONA;
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
import trackr.model.person.PersonNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindSupplierCommandTest {
    private Model model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
            getTypicalMenu(), getTypicalOrderList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
            getTypicalMenu(), getTypicalOrderList(), new UserPrefs());

    @Test
    public void equals() {
        PersonNameContainsKeywordsPredicate firstPredicate =
                new PersonNameContainsKeywordsPredicate(Collections.singletonList("first"));
        PersonNameContainsKeywordsPredicate secondPredicate =
                new PersonNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindSupplierCommand findFirstCommand = new FindSupplierCommand(firstPredicate);
        FindSupplierCommand findSecondCommand = new FindSupplierCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindSupplierCommand findFirstCommandCopy = new FindSupplierCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 0,
                ModelEnum.SUPPLIER.toString().toLowerCase());
        PersonNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindSupplierCommand command = new FindSupplierCommand(predicate);
        expectedModel.updateFilteredItemList(predicate, ModelEnum.SUPPLIER);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredSupplierList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 3,
                ModelEnum.SUPPLIER.toString().toLowerCase());
        PersonNameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindSupplierCommand command = new FindSupplierCommand(predicate);
        expectedModel.updateFilteredItemList(predicate, ModelEnum.SUPPLIER);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredSupplierList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private PersonNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PersonNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

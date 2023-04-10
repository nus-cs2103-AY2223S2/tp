package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_CUSTOMERS_LISTED_OVERVIEW;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_CUSTOMERS_ONLY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalCustomers.CARL;
import static seedu.loyaltylift.testutil.TypicalCustomers.ELLE;
import static seedu.loyaltylift.testutil.TypicalCustomers.FIONA;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.customer.CustomerNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCustomerCommand}.
 */
public class FindCustomerCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CustomerNameContainsKeywordsPredicate firstPredicate =
                new CustomerNameContainsKeywordsPredicate(Collections.singletonList("first"));
        CustomerNameContainsKeywordsPredicate secondPredicate =
                new CustomerNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCustomerCommand findFirstCommand = new FindCustomerCommand(firstPredicate);
        FindCustomerCommand findSecondCommand = new FindCustomerCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCustomerCommand findFirstCommandCopy = new FindCustomerCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different customer -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noCustomerFound() {
        CustomerNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCustomerCommand command = new FindCustomerCommand(predicate);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 0),
                LIST_CUSTOMERS_ONLY);

        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCustomerList());
    }

    @Test
    public void execute_multipleKeywords_multipleCustomersFound() {
        CustomerNameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCustomerCommand command = new FindCustomerCommand(predicate);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 3),
                LIST_CUSTOMERS_ONLY);

        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredCustomerList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private CustomerNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new CustomerNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

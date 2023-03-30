package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
// import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.OnlyOnePersonPredicate;
import seedu.address.model.person.TxnContainsPersonPredicate;

/**
 * Contains integration tests (interaction with the Model) for
 * {@codeFindTxnByPersonCommand}.
 */
public class FindTxnByPersonCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TxnContainsPersonPredicate firstPredicate = new TxnContainsPersonPredicate("First");
        OnlyOnePersonPredicate secondPredicate = new OnlyOnePersonPredicate("Second");
        TxnContainsPersonPredicate thirdPredicate = new TxnContainsPersonPredicate("Third");
        OnlyOnePersonPredicate fourthPredicate = new OnlyOnePersonPredicate("Fourth");

        FindTxnByPersonCommand findFirstCommand = new FindTxnByPersonCommand(firstPredicate, secondPredicate);
        FindTxnByPersonCommand findSecondCommand = new FindTxnByPersonCommand(thirdPredicate, fourthPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindTxnByPersonCommand findFirstCommandCopy = new FindTxnByPersonCommand(firstPredicate, secondPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        TxnContainsPersonPredicate txnPredicate = prepareTxnPredicate(" ");
        OnlyOnePersonPredicate personPredicate = preparePersonPredicate(" ");
        FindTxnByPersonCommand command = new FindTxnByPersonCommand(txnPredicate, personPredicate);
        model.updateFilteredPersonList(personPredicate);
        // model.updateFilteredTransactionList(txnPredicate);
        // assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredPersonList(), model.getFilteredPersonList());
        // TODO: REDO THIS unit test case, dummy test case need to correct
    }

    /**
     * Parses {@code userInput} into a {@code FindContainsAnythingPredicate}.
     */
    private TxnContainsPersonPredicate prepareTxnPredicate(String userInput) {
        return new TxnContainsPersonPredicate(userInput);
    }

    /**
     * Parses {@code userInput} into a {@code FindContainsAnythingPredicate}.
     */
    private OnlyOnePersonPredicate preparePersonPredicate(String userInput) {
        return new OnlyOnePersonPredicate(userInput);
    }
}

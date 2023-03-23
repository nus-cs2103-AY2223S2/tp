package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTransactions.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.txncommands.ListTxnCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ListTxnCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListTxnCommand(), model, ListTxnCommand.MESSAGE_SUCCESS, expectedModel);
    }

    // TODO filtering of transaction (through TransactionDescContainsKeywordPredicate) is required
    // for this filteredList test
    //    @Test
    //    public void execute_listIsFiltered_showsEverything() {
    //        showTransactionAtIndex(model, INDEX_FIRST_TRANSACTION);
    //        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    //    }
}

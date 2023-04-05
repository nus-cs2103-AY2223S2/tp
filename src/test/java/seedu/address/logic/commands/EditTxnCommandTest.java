//package seedu.address.logic.commands;
//
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TXN_DESC_COFFEE_MACHINES;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TRANSACTION;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TRANSACTION;
//import static seedu.address.testutil.TypicalTransactions.getTypicalAddressBook;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.commons.core.Messages;
//import seedu.address.commons.core.index.Index;
//import seedu.address.logic.commands.txncommands.EditTxnCommand;
//import seedu.address.model.AddressBook;
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.transaction.Transaction;
//import seedu.address.testutil.EditTxnDescriptorBuilder;
//import seedu.address.testutil.TransactionBuilder;
//
//public class EditTxnCommandTest {
//
//    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//
//    @Test
//    public void execute_allFieldsSpecifiedUnfilteredList_success() {
//        Transaction editedTxn = new TransactionBuilder().build();
//        EditTxnCommand.EditTxnDescriptor descriptor = new EditTxnDescriptorBuilder(editedTxn).build();
//        EditTxnCommand editTxnCommand = new EditTxnCommand(INDEX_FIRST_TRANSACTION, descriptor);
//
//        String expectedMessage = String.format(EditTxnCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS, editedTxn);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setTransaction(model.getFilteredTransactionList().get(0), editedTxn);
//
//        assertCommandSuccess(editTxnCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_someFieldsSpecifiedUnfilteredList_success() {
//        Index indexLastTxn = Index.fromOneBased(model.getFilteredTransactionList().size());
//        Transaction lastTxn = model.getFilteredTransactionList().get(indexLastTxn.getZeroBased());
//
//        TransactionBuilder txnInList = new TransactionBuilder(lastTxn);
//        Transaction editedTxn = txnInList.withDesc(VALID_TXN_DESC_COFFEE_MACHINES).build();
//
//        EditTxnCommand.EditTxnDescriptor descriptor = new EditTxnDescriptorBuilder()
//                .withDescription(VALID_TXN_DESC_COFFEE_MACHINES).build();
//        EditTxnCommand editTxnCommand = new EditTxnCommand(indexLastTxn, descriptor);
//
//        String expectedMessage = String.format(EditTxnCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS, editedTxn);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setTransaction(lastTxn, editedTxn);
//
//        assertCommandSuccess(editTxnCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_noFieldSpecifiedUnfilteredList_success() {
//        EditTxnCommand editTxnCommand = new EditTxnCommand(INDEX_FIRST_TRANSACTION,
//                new EditTxnCommand.EditTxnDescriptor());
//        Transaction editedTxn = model.getFilteredTransactionList().get(INDEX_FIRST_TRANSACTION.getZeroBased());
//
//        String expectedMessage = String.format(EditTxnCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS, editedTxn);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//
//        assertCommandSuccess(editTxnCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_filteredList_success() {
//    }
//
//    @Test
//    public void execute_duplicateTxnUnfilteredList_failure() {
//        Transaction firstTxn = model.getFilteredTransactionList().get(INDEX_FIRST_TRANSACTION.getZeroBased());
//        EditTxnCommand.EditTxnDescriptor descriptor = new EditTxnDescriptorBuilder(firstTxn).build();
//        EditTxnCommand editTxnCommand = new EditTxnCommand(INDEX_SECOND_TRANSACTION, descriptor);
//
//        assertCommandFailure(editTxnCommand, model, EditTxnCommand.MESSAGE_DUPLICATE_TRANSACTION);
//    }
//
//    @Test
//    public void execute_duplicatePersonFilteredList_failure() {
//
//    }
//
//    @Test
//    public void execute_invalidPersonIndexUnfilteredList_failure() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTransactionList().size() + 1);
//        EditTxnCommand.EditTxnDescriptor descriptor = new EditTxnDescriptorBuilder()
//                .withDescription(VALID_TXN_DESC_COFFEE_MACHINES).build();
//        EditTxnCommand editTxnCommand = new EditTxnCommand(outOfBoundIndex, descriptor);
//
//        assertCommandFailure(editTxnCommand, model, Messages.MESSAGE_INVALID_TXN_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void equals() {
//
//    }
//
//}

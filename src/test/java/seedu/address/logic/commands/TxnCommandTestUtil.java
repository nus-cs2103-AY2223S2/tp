package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_OWNER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_VALUE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.txncommands.EditTxnCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.EditTxnDescriptorBuilder;
/**
 * Contains helper methods for testing txncommands.
 */
public class TxnCommandTestUtil {
    public static final String VALID_OWNER_LIMMY = "Limmy Lim";
    public static final String VALID_OWNER_MAC = "Mac Macpherson";
    public static final String VALID_DESCRIPTION_LIMMY = "5 Café Lite Espresso Machines for Proseware.";
    public static final String VALID_DESCRIPTION_MAC = "15 AwfullyHot Coffee Pots for Hotel Californication";
    public static final String VALID_VALUE_LIMMY = "5000";
    public static final String VALID_VALUE_MAC = "30000";
    public static final String VALID_STATUS_LIMMY = "open";
    public static final String VALID_STATUS_MAC = "closed";

    public static final String OWNER_DESC_LIMMY = " " + PREFIX_TXN_OWNER + VALID_OWNER_LIMMY;
    public static final String OWNER_DESC_MAC = " " + PREFIX_TXN_OWNER + VALID_OWNER_MAC;
    public static final String DESCRIPTION_DESC_LIMMY = " " + PREFIX_TXN_DESCRIPTION + VALID_DESCRIPTION_LIMMY;
    public static final String DESCRIPTION_DESC_MAC = " " + PREFIX_TXN_DESCRIPTION + VALID_DESCRIPTION_MAC;
    public static final String VALUE_DESC_LIMMY = " " + PREFIX_TXN_VALUE + VALID_VALUE_LIMMY;
    public static final String VALUE_DESC_MAC = " " + PREFIX_TXN_VALUE + VALID_VALUE_MAC;
    public static final String STATUS_DESC_LIMMY = " " + PREFIX_TXN_STATUS + VALID_STATUS_LIMMY;
    public static final String STATUS_DESC_MAC = " " + PREFIX_TXN_STATUS + VALID_STATUS_MAC;

    public static final String INVALID_OWNER_DESC = " " + PREFIX_TXN_OWNER + " "; // owner cannot be empty
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_TXN_DESCRIPTION + " ";
    // description cannot be empty
    public static final String INVALID_VALUE_DESC = " " + PREFIX_TXN_VALUE + "1000.12.2"; // max 1 dot allowed in values
    public static final String INVALID_STATUS_DESC = " " + PREFIX_TXN_STATUS + "pending";
    // pending is not a valid status

    public static final String PREAMBLE_WHITESPACE = "\t \r \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditTxnCommand.EditTxnDescriptor DESC_LIMMY;
    public static final EditTxnCommand.EditTxnDescriptor DESC_MAC;

    static {
        DESC_LIMMY = new EditTxnDescriptorBuilder().withOwner(VALID_OWNER_LIMMY)
                .withDescription(VALID_DESCRIPTION_LIMMY)
                .withValue(VALID_VALUE_LIMMY)
                .withStatus(VALID_STATUS_LIMMY).build();
        DESC_MAC = new EditTxnDescriptorBuilder().withOwner(VALID_OWNER_MAC)
                .withDescription(VALID_DESCRIPTION_MAC)
                .withValue(VALID_VALUE_MAC)
                .withStatus(VALID_STATUS_MAC).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Transaction> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTransactionList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredTransactionList());
    }
    //    /**
    //     * Updates {@code model}'s filtered list to show only the transaction at the given {@code targetIndex} in the
    //     * {@code model}'s address book.
    //     */
    //    public static void showPersonAtIndex(Model model, Index targetIndex) {
    //        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());
    //
    //        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
    //        final String[] splitName = person.getName().fullName.split("\\s+");
    //        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
    //
    //        assertEquals(1, model.getFilteredPersonList().size());
    //    }

    // requires implementation of TransactionDescContainsKeyword
    //    public static void showTransactionAtIndex(Model model, Index targetIndex) {
    //        assertTrue(targetIndex.getZeroBased() < model.getFilteredTransactionList().size());
    //
    //        Transaction txn = model.getFilteredTransactionList().get(targetIndex.getZeroBased());
    //        final String[] splitDesc = txn.getDescription().value.split("\\s");
    //        model.updateFilteredTransactionList(Model.PREDICATE_SHOW_ALL_TRANSACTIONS);
    //    }

}

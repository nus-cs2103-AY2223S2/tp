package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_OWNER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TXN_VALUE;

import seedu.address.logic.commands.txncommands.AddTxnCommand;
import seedu.address.logic.commands.txncommands.EditTxnCommand.EditTxnDescriptor;
import seedu.address.model.transaction.Transaction;

/**
 * A utility class for Transaction.
 */
public class TransactionUtil {

    /**
     * Returns an add command string for adding the {@code transaction}.
     */
    public static String getAddTxnCommand(Transaction transaction) {
        return AddTxnCommand.COMMAND_WORD + " " + getTxnDetails(transaction);
    }

    /**
     * Returns the part of command string for the given {@code transaction}'s details.
     */
    public static String getTxnDetails(Transaction transaction) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TXN_DESCRIPTION + transaction.getDescription().value + " ");
        sb.append(PREFIX_TXN_OWNER + transaction.getOwner().value + " ");
        sb.append(PREFIX_TXN_STATUS + transaction.getStatus().value + " ");
        sb.append(PREFIX_TXN_VALUE + transaction.getValue().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditTxnDescriptor}'s details.
     */
    public static String getEditTxnDescriptorDetails(EditTxnDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_TXN_DESCRIPTION).append(
                description.value).append(" "));
        descriptor.getOwner().ifPresent(owner -> sb.append(PREFIX_TXN_OWNER).append(owner.value).append(" "));
        descriptor.getStatus().ifPresent(status -> sb.append(PREFIX_TXN_STATUS).append(status.value).append(" "));
        descriptor.getValue().ifPresent(value -> sb.append(PREFIX_TXN_VALUE).append(value.value).append(" "));

        return sb.toString();
    }
}

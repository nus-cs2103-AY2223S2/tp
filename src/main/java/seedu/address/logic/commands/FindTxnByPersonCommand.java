package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.OnlyOnePersonPredicate;
import seedu.address.model.person.TxnContainsPersonPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the
 * argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTxnByPersonCommand extends Command {

    public static final String COMMAND_WORD = "findtxn";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Returns the contact that matches and all txns related to this contact "
            + "the specified keywords (case-insensitive) and displays the txn list with index numbers.\n"
            + "Parameters: Exact match [NAME]\n"
            + "Example: " + COMMAND_WORD + " Bernice Yu";

    private final TxnContainsPersonPredicate txnPredicate;
    private final OnlyOnePersonPredicate personPredicate;

    /**
     * FindTxnByPersonCommand this prepares the predicates
     * @param txnPredicate
     * @param personPredicate
     */
    public FindTxnByPersonCommand(TxnContainsPersonPredicate txnPredicate, OnlyOnePersonPredicate personPredicate) {
        this.txnPredicate = txnPredicate;
        this.personPredicate = personPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(personPredicate);
        model.updateFilteredTransactionList(txnPredicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTxnByPersonCommand // instanceof handles nulls
                        && txnPredicate.equals(((FindTxnByPersonCommand) other).txnPredicate)); // state check
    }
}

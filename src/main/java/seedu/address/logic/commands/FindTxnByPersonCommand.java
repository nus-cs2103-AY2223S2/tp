package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":FindTxn";

    private final TxnContainsPersonPredicate txnPredicate;
    private final OnlyOnePersonPredicate personPredicate;

    /**
     * fill this up later
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

        return new CommandResult("Find txn by person working");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTxnByPersonCommand // instanceof handles nulls
                        && txnPredicate.equals(((FindTxnByPersonCommand) other).txnPredicate)); // state check
    }
}

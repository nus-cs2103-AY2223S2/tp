package trackr.logic.commands;

import static java.util.Objects.requireNonNull;

import trackr.commons.core.Messages;
import trackr.model.Model;
import trackr.model.supplier.NameContainsKeywordsPredicate;

/**
 * Finds and lists all suppliers in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindSupplierCommand extends Command {

    public static final String COMMAND_WORD = "find_supplier";
    public static final String COMMAND_WORD_SHORTCUT = "find_s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all supplier whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FindSupplierCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredSupplierList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredSupplierList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindSupplierCommand // instanceof handles nulls
                && predicate.equals(((FindSupplierCommand) other).predicate)); // state check
    }
}

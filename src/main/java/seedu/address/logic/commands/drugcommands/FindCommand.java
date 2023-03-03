package seedu.address.logic.commands.drugcommands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CareFlowModel;
import seedu.address.model.drug.TradeNameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRADE_NAME;

/**
 * Finds a drug from the drug inventory
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_SUCCESS = "Found matching Drug(s): %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all drug(s) whose trade name contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final TradeNameContainsKeywordsPredicate predicate;

    public FindCommand(TradeNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(CareFlowModel model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredDrugList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_DRUGS_LISTED_OVERVIEW, model.getFilteredDrugList().size()));
    }
}

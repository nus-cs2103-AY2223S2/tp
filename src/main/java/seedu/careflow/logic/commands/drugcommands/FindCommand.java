package seedu.careflow.logic.commands.drugcommands;

import static java.util.Objects.requireNonNull;

import seedu.careflow.commons.core.Messages;
import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.drug.TradeNameContainsKeywordsPredicate;

/**
 * Finds a drug from the drug inventory
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    //public static final String MESSAGE_SUCCESS = "Found matching Drug(s): %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all drug(s) whose trade name contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Panadol";

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

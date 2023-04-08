package seedu.ultron.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ultron.model.Model;
import seedu.ultron.model.opening.CompanyOrPositionContainsKeywordsPredicate;

/**
 * Finds and lists all openings in Ultron whose company or position contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all openings whose company or position contain "
            + "any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " engineer analyst google";

    private static final String MESSAGE_SUCCESS = "%1$d openings with given keywords found!";

    private final CompanyOrPositionContainsKeywordsPredicate predicate;

    public FindCommand(CompanyOrPositionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOpeningList(predicate);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredOpeningList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}

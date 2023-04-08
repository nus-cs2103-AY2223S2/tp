package seedu.ultron.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ultron.model.Model;
import seedu.ultron.model.opening.ContainsStatusPredicate;

/**
 * Finds and lists all openings in Ultron whose status is the keyword.
 * Keyword matching is case-insensitive.
 */
public class StatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all openings whose status is "
            + "the specified keyword (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " applied";

    private static final String MESSAGE_SUCCESS = "%1$d openings found based on provided status!";

    private final ContainsStatusPredicate predicate;

    public StatusCommand(ContainsStatusPredicate predicate) {
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
                || (other instanceof StatusCommand // instanceof handles nulls
                && predicate.equals(((StatusCommand) other).predicate)); // state check
    }
}

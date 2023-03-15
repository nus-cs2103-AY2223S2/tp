package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.fitbook.commons.core.Messages;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.routines.RoutineNameContainsKeywordsPredicate;
/**
 * Finds and lists all routines in fitbook whose routine name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindRoutineCommand extends Command {

    public static final String COMMAND_WORD = "findRoutine";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all routine whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " cardio";

    private final RoutineNameContainsKeywordsPredicate predicate;

    public FindRoutineCommand(RoutineNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(FitBookModel model) {
        requireNonNull(model);
        model.updateFilteredRoutineList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ROUTINES_LISTED_OVERVIEW, model.getFilteredRoutineList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindRoutineCommand // instanceof handles nulls
                && predicate.equals(((FindRoutineCommand) other).predicate)); // state check
    }
}

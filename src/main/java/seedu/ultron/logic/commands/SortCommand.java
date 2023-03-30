package seedu.ultron.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ultron.commons.core.Messages;
import seedu.ultron.model.Model;
import seedu.ultron.model.opening.ContainsKeydatePredicate;
import seedu.ultron.model.opening.KeydateSort;


/**
 * Finds and lists all openings in Ultron whose company or position contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all openings that have a keydate and "
            + "sorts them by their earliest keydate in either ascending or descending order\n"
            + "Parameters: asc or desc\n"
            + "Example: " + COMMAND_WORD + " asc";

    private final KeydateSort direction;
    private final ContainsKeydatePredicate predicate;

    public SortCommand(ContainsKeydatePredicate predicate, KeydateSort direction) {
        this.direction = direction;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOpeningList(predicate);
        model.sortFilteredOpeningList(direction);
        return new CommandResult(
                String.format(Messages.MESSAGE_OPENING_LISTED_OVERVIEW, model.getFilteredOpeningList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && predicate.equals(((SortCommand) other).predicate)); // state check
    }
}

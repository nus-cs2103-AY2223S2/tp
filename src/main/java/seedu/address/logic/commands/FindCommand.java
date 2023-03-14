package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.EventContainsKeywordsPredicate;
import seedu.address.model.person.EventContainsKeywordsPredicate;

/**
 * Finds and lists all events in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds events that contain keyword provided"
            + "and displays them as a list with index number \n"
            + "Parameters: KEYWORD [MORE_KEYWORDS] ... \n"
            + "Example: " + COMMAND_WORD + " booking basketball court";
    
    private final EventContainsKeywordsPredicate predicate;

    public FindCommand(EventContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}

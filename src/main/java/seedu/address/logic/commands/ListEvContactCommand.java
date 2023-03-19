package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.EventSetContainsEventPredicate;

/**
 * Lists all persons with the target event in their event sets.
 */
public class ListEvContactCommand extends Command {
    public static final String COMMAND_WORD = "listevcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": List all the contacts from an event identified by it's index number\n"
            + "Parameters: EVENT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_LIST_EVENT_CONTACT_SUCCESS = "Listed all contacts from the event : %1$s";

    private final Index targetIndex;
    private EventSetContainsEventPredicate predicate;

    public ListEvContactCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownEventList = model.getFilteredEventList();

        if (targetIndex.getZeroBased() >= lastShownEventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event targetEvent = lastShownEventList.get(targetIndex.getZeroBased());
        predicate = new EventSetContainsEventPredicate(targetEvent);
        model.updateFilteredPersonList(predicate);

        return new CommandResult(String.format(MESSAGE_LIST_EVENT_CONTACT_SUCCESS, targetEvent));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListEvContactCommand // instanceof handles nulls
                && targetIndex.equals(((ListEvContactCommand) other).targetIndex)); // state check
    }

}

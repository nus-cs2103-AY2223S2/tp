package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.event.RecurringEventList;
import seedu.address.model.event.exceptions.EventConflictException;
import seedu.address.model.person.Person;


/**
 * Deletes a recurring event from a person's recurring event list using it's displayed person index
 * and displayed event index from the address book.
 */
public class DeleteRecurringEventCommand extends Command {
    public static final String COMMAND_WORD = "re_delete";
    public static final String MESSAGE_SUCCESS = "Recurring event deleted: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete an recurring event into the recurring event list"
            + "by the index number used in the last person listing.\n "
            + "Parameters: index number of the person and index number of the event in the event list"
            + "1 1 \n"
            + "Example: " + COMMAND_WORD + "1" + " 1";


    private Index personIndex;
    private Index eventIndex;

    /**
     * Constructor for DeleteIsolatedEventCommand.
     * @param personIndex the index of the person.
     * @param eventIndex the index of the event to be deleted.
     */
    public DeleteRecurringEventCommand(Index personIndex, Index eventIndex) {
        this.personIndex = personIndex;
        this.eventIndex = eventIndex;
    }

    /**
     * Get the Person object and the ReccuringEvent object to be deleted and
     * delete the event from the person's isolated event list.
     * @param model {@code Model} which the command should operate on.
     * @return Command result displaying the command successful message.
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException, EventConflictException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(personIndex.getZeroBased());
        RecurringEventList recurringEventList = personToEdit.getRecurringEventList();

        if (eventIndex.getZeroBased() >= recurringEventList.getSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECURRING_EVENT_LIST);
        }

        RecurringEvent event = recurringEventList.getRecurringEvent(eventIndex.getZeroBased());
        if (event == null) {
            throw new CommandException(Messages.MESSAGE_UNKNOWN_RECURRING_EVENT);
        }

        model.deleteRecurringEvent(personToEdit, event);

        return new CommandResult(String.format(MESSAGE_SUCCESS, event) + " to "
                + personToEdit.getName());

    }
}

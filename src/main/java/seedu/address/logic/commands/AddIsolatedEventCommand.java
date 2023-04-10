package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.IsolatedEvent;
import seedu.address.model.event.IsolatedEventList;
import seedu.address.model.event.exceptions.EventConflictException;
import seedu.address.model.person.Person;


/**
 * Command class for AddIsolatedEventCommand.
 */
public class AddIsolatedEventCommand extends Command {
    public static final String COMMAND_WORD = "event_create";
    public static final String MESSAGE_SUCCESS = "New isolated event added: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add an isolated event into the isolated event list"
            + "by the index number used in the last person listing.\n "
            + "Parameters: index of person to add the event, event name,"
            + " start date and end date (must be in the format of dd/MM/yyyy HH:mm) "
            + "[INDEX] ie/[EVENT_NAME] f/[START_DATE] t/[END_DATE] \n"
            + "Example: " + COMMAND_WORD + " 1" + " ie/biking" + " f/09/03/2025 14:00" + " t/09/03/2025 15:00";

    public final IsolatedEvent eventToAdd;
    public final Index index;

    /**
     * Constructor for AddIsolatedEventCommand Object.
     * @param index The index of the {@code Person} to which the {@code IsolatedEvent} will be added to.
     * @param eventToAdd The {IsolatedEvent} to be added to the {@code Person}.
     */
    public AddIsolatedEventCommand(Index index, IsolatedEvent eventToAdd) {
        requireNonNull(index);
        requireNonNull(eventToAdd);
        this.eventToAdd = eventToAdd;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        eventToAdd.checkDateTime();

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        IsolatedEventList isolatedEventList = personToEdit.getIsolatedEventList();
        try {
            isolatedEventList.checkClashingIsolatedEvent(eventToAdd.getStartDate(),
                    eventToAdd.getEndDate());
            eventToAdd.checkConflictsRecurringEventList(personToEdit.getRecurringEventList());
        } catch (EventConflictException e) {
            throw new CommandException(String.format(Messages.MESSAGE_EVENT_CLASH, e.getMessage()));
        }
        isolatedEventList.insert(eventToAdd);
        model.setPerson(personToEdit, personToEdit);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, eventToAdd) + " to "
                + personToEdit.getName());
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.IsolatedEvent;
import seedu.address.model.event.IsolatedEventList;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.event.RecurringEventList;
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
            + "Parameters: event name, start date and end date (must be in the format of dd/MM/yyyy HH:mm) "
            + "ie/[EVENT_NAME] f/[START_DATE] t/[END_DATE] \n"
            + "Example: " + COMMAND_WORD + " ie/biking" + " f/09/03/2023 14:00" + " t/09/03/2023 15:00";

    public final IsolatedEvent eventToAdd;
    public final Index index;

    /**
     * Constructor for AddIsolatedEventCommand Object.
     * @param index
     * @param eventToAdd
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
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        String checkConflictsInRecurringList = listConflictedEventWithRecurring(eventToAdd, personToEdit);

        if (!checkConflictsInRecurringList.equals("0")) {
            throw new EventConflictException(checkConflictsInRecurringList);
        }
        IsolatedEventList isolatedEventList = personToEdit.getIsolatedEventList();
        IsolatedEvent checkForEventClash = isolatedEventList.checkClashingIsolatedEvent(eventToAdd.getStartDate(),
                eventToAdd.getEndDate());

        if (checkForEventClash != null) {
            throw new CommandException(String.format(Messages.MESSAGE_EVENT_CLASH, checkForEventClash));
        }

        model.addIsolatedEvent(personToEdit, eventToAdd);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, eventToAdd) + " to "
                + personToEdit.getName());
    }

    /**
     * This function cross-check with the recurring event list to check for any conflicts
     * @param isolatedEvent is the event to be added
     * @param person of the event that is to be added
     * @return "0" if there are no conflicts and return the conflicted event string if there is a conflict
     */
    public String listConflictedEventWithRecurring(IsolatedEvent isolatedEvent, Person person) {
        LocalDateTime startPeriod = isolatedEvent.getStartDate();
        LocalDateTime endPeriod = isolatedEvent.getEndDate();

        RecurringEventList recurringEventList = person.getRecurringEventList();

        int index = 1;
        for (RecurringEvent re : recurringEventList.getRecurringEvents()) {
            if (re.occursBetween(startPeriod, endPeriod)) {
                return "Recurring Event List:\n" + index + " " + re;
            }
        }
        return "0";
    }
}

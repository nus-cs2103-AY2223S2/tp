package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
 * Command class for AddRecurringEventCommand.
 */
public class AddRecurringEventCommand extends Command {

    public static final String COMMAND_WORD = "event_create_recur";
    public static final String MESSAGE_SUCCESS = "New recurring event added: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add a recurring event into the recurring event list"
            + "by the index number used in the last person listing. \n"
            + "Parameters: index of person to add the event, event name, week of the day,"
            + " start time and end time (must be in the format HH:mm) "
            + "[INDEX] re/[EVENT_NAME] d/[DAY_OF_WEEK] f/[START_TIME] t/[END_TIME] \n"
            + "Example: " + COMMAND_WORD + " 1" + " re/biking" + " d/Monday" + " f/14:00" + " t/15:00";


    public final RecurringEvent eventToAdd;
    public final Index index;


    /**
     * Constructor for AddRecurringEventCommand
     * @param eventToAdd
     * @param index
     */
    public AddRecurringEventCommand(Index index, RecurringEvent eventToAdd) {
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

        try {
            eventToAdd.checkPeriod();
            eventToAdd.listConflictedEventWithIsolated(personToEdit.getIsolatedEventList());
        } catch (EventConflictException e) {
            throw new CommandException(e.getMessage());
        }

        RecurringEventList recurringEventList = personToEdit.getRecurringEventList();
        RecurringEvent checkForEventClash = recurringEventList.checkClashingRecurringEvent(eventToAdd);

        if (checkForEventClash != null) {
            throw new CommandException(String.format(Messages.MESSAGE_EVENT_CLASH, checkForEventClash));
        }

        model.addRecurringEvent(personToEdit, eventToAdd);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, eventToAdd) + " to "
                + personToEdit.getName());

    }
}

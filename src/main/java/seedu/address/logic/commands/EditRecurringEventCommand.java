package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAYOFWEEK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRINGEVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATETIME;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.event.RecurringEventList;
import seedu.address.model.person.Person;

/**
 * Edits the details of an existing event in the person's recurring event list.
 */
public class EditRecurringEventCommand extends Command {
    public static final String COMMAND_WORD = "re_edit";
    public static final String MESSAGE_SUCCESS = "Recurring event edited to: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";


    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edit a recurring event into the recurring event list"
            + "by the index number used in the person listing.\n "
            + "Parameters: "
            + "[index number of the person], "
            + "[index number of the event] in the event list"
            + PREFIX_RECURRINGEVENT + "EVENT_NAME"
            + PREFIX_DAYOFWEEK + "DAY_OF_WEEK"
            + PREFIX_STARTDATETIME + "START_TIME"
            + PREFIX_ENDDATETIME + "END_TIME"
            + "Example: " + COMMAND_WORD + " 1" + " 1" + " ie/biking"
            + "Example: " + COMMAND_WORD + " 1" + " 1" + " f/14:00" + " t/18:00";

    private final Index personIndex;
    private final Index eventIndex;
    private final EditEventDescriptor editEventDescriptor;

    /**
     * Constructor for EditRecurringCommand.
     * @param personIndex in which the event is contained in.
     * @param eventIndex for the event to be edited.
     * @param editEventDescriptor of what to be edited.
     */
    public EditRecurringEventCommand(Index personIndex, Index eventIndex,
                                     EditEventDescriptor editEventDescriptor) {
        requireNonNull(personIndex);
        requireNonNull(eventIndex);
        requireNonNull(editEventDescriptor);

        this.personIndex = personIndex;
        this.eventIndex = eventIndex;
        this.editEventDescriptor = new EditEventDescriptor(editEventDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
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

        RecurringEvent originalEvent = recurringEventList.getRecurringEvent(eventIndex.getZeroBased());
        if (originalEvent == null) {
            throw new CommandException(Messages.MESSAGE_UNKNOWN_RECURRING_EVENT);
        }

        RecurringEvent editedRecurringEvent =
                createEditedRecurringEvent(personToEdit, originalEvent, editEventDescriptor);

        model.setRecurringEvent(personToEdit, originalEvent, editedRecurringEvent);

        return new CommandResult(String.format(MESSAGE_SUCCESS, editedRecurringEvent)
                + " for " + personToEdit.getName()
                + "\nOriginal Event: " + originalEvent + " for " + personToEdit.getName());

    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static RecurringEvent createEditedRecurringEvent(Person personToEdit, RecurringEvent originalEvent,
                                                             EditEventDescriptor
                                                                     eventDescriptor) {
        assert personToEdit != null;

        String updatedEventName = eventDescriptor.getEventName().orElse(originalEvent.getEventName());
        DayOfWeek dayOfWeek = eventDescriptor.getDayOfWeek().orElse(originalEvent.getDayOfWeek());
        LocalTime startTime = eventDescriptor.getStartTime().orElse(originalEvent.getStartTime());
        LocalTime endTime = eventDescriptor.getEndTime().orElse(originalEvent.getEndTime());

        return new RecurringEvent(updatedEventName, dayOfWeek, startTime, endTime);
    }

    /**
     * Stores the details to edit the event with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditEventDescriptor {
        private String eventName;
        private DayOfWeek dayOfWeek;
        private LocalTime startTime;
        private LocalTime endTime;



        public EditEventDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditEventDescriptor(EditEventDescriptor toCopy) {
            setEventName(toCopy.eventName);
            setDayOfWeek(toCopy.dayOfWeek);
            setStartTime(toCopy.startTime);
            setEndTime(toCopy.endTime);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(eventName, dayOfWeek, startTime, endTime);
        }

        public void setEventName(String name) {
            this.eventName = name;
        }

        public Optional<String> getEventName() {
            return Optional.ofNullable(eventName);
        }

        public void setDayOfWeek(DayOfWeek dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
        }

        public Optional<DayOfWeek> getDayOfWeek() {
            return Optional.ofNullable(dayOfWeek);
        }

        public void setStartTime(LocalTime startTime) {
            this.startTime = startTime;
        }

        public Optional<LocalTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setEndTime(LocalTime endTime) {
            this.endTime = endTime;
        }

        public Optional<LocalTime> getEndTime() {
            return Optional.ofNullable(endTime);
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCommand.EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditEventDescriptor e = (EditEventDescriptor) other;

            return getEventName().equals(e.getEventName())
                    && getDayOfWeek().equals(e.getDayOfWeek())
                    && getStartTime().equals(e.getStartTime())
                    && getEndTime().equals(e.getEndTime());
        }
    }
}

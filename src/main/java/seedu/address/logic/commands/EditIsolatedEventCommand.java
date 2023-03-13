package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ISOEVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATETIME;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.IsolatedEvent;
import seedu.address.model.person.Person;

/**
 * Edits the details of an existing event in the person's isolated event list.
 */
public class EditIsolatedEventCommand extends Command {
    public static final String COMMAND_WORD = "ie_edit";
    public static final String MESSAGE_SUCCESS = "Isolated event edited to: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";


    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edit an isolated event into the isolated event list"
            + "by the index number used in the last person listing.\n "
            + "Parameters: "
            + "[index number of the person], "
            + "[index number of the event] in the event list"
            + PREFIX_ISOEVENT + "EVENT_NAME"
            + PREFIX_STARTDATETIME + "START_DATE"
            + PREFIX_ENDDATETIME + "END_DATE"
            + "Example: " + COMMAND_WORD + " 1" + " 1" + " ie/biking"
            + "Example: " + COMMAND_WORD + " 1" + " 1" + " f/09/03/2023 14:00" + " t/09/03/2023 18:00";

    private final Index personIndex;
    private final Index eventIndex;
    private final EditEventDescriptor editEventDescriptor;

    /**
     * Constructor for EditIsolatedCommand.
     * @param personIndex in which the event is contained in.
     * @param eventIndex for the event to be edited.
     * @param editEventDescriptor of what to be edited.
     */
    public EditIsolatedEventCommand(Index personIndex, Index eventIndex, EditEventDescriptor editEventDescriptor) {
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
        IsolatedEvent originalEvent = personToEdit.getIsolatedEventList().getIsolatedEvent(eventIndex.getZeroBased());
        IsolatedEvent editedIsolatedEvent = createEditedIsolatedEvent(personToEdit, originalEvent, editEventDescriptor);

        model.setIsolatedEvent(personToEdit, originalEvent, editedIsolatedEvent);

        return new CommandResult(String.format(MESSAGE_SUCCESS, editedIsolatedEvent)
                + " from " + originalEvent + " for " + personToEdit.getName());

    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static IsolatedEvent createEditedIsolatedEvent(Person personToEdit, IsolatedEvent originalEvent,
                                                           EditEventDescriptor eventDescriptor) {
        assert personToEdit != null;

        String updatedEventName = eventDescriptor.getEventName().orElse(originalEvent.getEventName());
        LocalDateTime startDate = eventDescriptor.getStartDate().orElse(originalEvent.getStartDate());
        LocalDateTime endDate = eventDescriptor.getEndDate().orElse(originalEvent.getEndDate());

        return new IsolatedEvent(updatedEventName, startDate, endDate);
    }

    /**
     * Stores the details to edit the event with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditEventDescriptor {
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String eventName;


        public EditEventDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditEventDescriptor(EditEventDescriptor toCopy) {
            setEventName(toCopy.eventName);
            setStartDate(toCopy.startDate);
            setEndDate(toCopy.endDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(endDate, startDate, eventName);
        }

        public void setEventName(String name) {
            this.eventName = name;
        }

        public Optional<String> getEventName() {
            return Optional.ofNullable(eventName);
        }

        public void setStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
        }

        public Optional<LocalDateTime> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setEndDate(LocalDateTime endDate) {
            this.endDate = endDate;
        }

        public Optional<LocalDateTime> getEndDate() {
            return Optional.ofNullable(endDate);
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
                    && getEndDate().equals(e.getEndDate())
                    && getStartDate().equals(e.getStartDate());
        }
    }

}

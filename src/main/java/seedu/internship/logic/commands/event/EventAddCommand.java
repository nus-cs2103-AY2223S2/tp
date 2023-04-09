package seedu.internship.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_END;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_EVENT_START;

import javafx.collections.ObservableList;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.ResultType;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.EventByInternship;
import seedu.internship.model.internship.Internship;

/**
 * Adds an event to the selected internship.
 */
public class EventAddCommand extends EventCommand {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = EventCommand.COMMAND_WORD + " "
            + EventAddCommand.COMMAND_WORD + ": Adds an event to the event catalogue.\n"
            + "Parameters: "
            + PREFIX_EVENT_NAME + "EVENT NAME"
            + PREFIX_EVENT_START + "START DATE TIME "
            + PREFIX_EVENT_END + "END DATE TIME "
            + PREFIX_EVENT_DESCRIPTION + "DESCRIPTION"
            + "\nExample: " + EventCommand.COMMAND_WORD + " "
            + EventAddCommand.COMMAND_WORD + " "
            + PREFIX_EVENT_NAME + "Technical Interview "
            + PREFIX_EVENT_START + "10/09/2023 1500 "
            + PREFIX_EVENT_END + "10/09/2023 1700 "
            + PREFIX_EVENT_DESCRIPTION + "On Zoom ";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the catalogue";
    public static final String MESSAGE_NO_INTERNSHIP_SELECTED = "Select an internship before adding an event.";
    public static final String MESSAGE_EVENT_CLASH_WARNING = "WARNING: This event CLASHES with another event.\n"
            + "New event added: %1$s\n" + "Use the clash command to see specifics.";
    public static final String MESSAGE_END_BEFORE_START = "End Date Time cannot be before Start Date Time.";

    private final Event eventToAdd;

    /**
     * Creates an AddCommand to add the specified {@code Event}
     */
    public EventAddCommand(Event event) {
        requireNonNull(event);
        this.eventToAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (eventToAdd.getStart().compareTo(eventToAdd.getEnd()) == 1) {
            throw new CommandException(MESSAGE_END_BEFORE_START);
        }

        // Retrieve the Selected Internship Index
        if (!model.hasSelectedInternship()) {
            throw new CommandException(MESSAGE_NO_INTERNSHIP_SELECTED);
        }

        // Adding internship information to event
        Internship selectedIntern = model.getSelectedInternship();
        eventToAdd.setInternship(selectedIntern);

        if (model.hasEvent(eventToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        //Checking for clashes
        model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);
        boolean isClashing = false;
        for (Event e : model.getFilteredEventList()) {
            if (!e.isDeadline() && eventToAdd.isClash(e)) {
                isClashing = true;
                break;
            }
        }

        model.addEvent(eventToAdd);
        model.updateFilteredEventList(new EventByInternship(selectedIntern));
        ObservableList<Event> events = model.getFilteredEventList();
        if (isClashing && !eventToAdd.isDeadline()) {
            return new CommandResult(String.format(MESSAGE_EVENT_CLASH_WARNING, eventToAdd), ResultType.SHOW_INFO,
                    selectedIntern, events);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, eventToAdd), ResultType.SHOW_INFO,
                selectedIntern, events);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventAddCommand // instanceof handles nulls
                && eventToAdd.equals(((EventAddCommand) other).eventToAdd));
    }

}

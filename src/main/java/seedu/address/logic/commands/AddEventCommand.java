package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE_TIME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.fields.DateTime;
import seedu.address.ui.result.ResultDisplay;

/**
 * Adds an Event to the Calendar.
 * Event is either {@code OneTimeEvent} or {@code RecurringEvent}.
 */
public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "addevent";

    public static final String MESSAGE_USAGE =
            ResultDisplay.formatMessage(COMMAND_WORD, "Adds an event to the event list.")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_PARAMETERS,
                    PREFIX_DESCRIPTION + "DESCRIPTION",
                    PREFIX_START_DATE_TIME + "START DATE TIME",
                    PREFIX_END_DATE_TIME + "END DATE TIME",
                    "[" + PREFIX_RECURRENCE + "INTERVAL]")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_EXAMPLE,
                    COMMAND_WORD,
                    PREFIX_DESCRIPTION + "CS2103T Lecture",
                    PREFIX_START_DATE_TIME + "2023-03-10 1600",
                    PREFIX_END_DATE_TIME + "2023-03-10 1800",
                    PREFIX_RECURRENCE + "weekly");

    private static final String MESSAGE_SUCCESS = "New event added: %1$s";
    private static final String MESSAGE_DUPLICATE_EVENT = "This event already exists!";
    private static final String MESSAGE_INVALID_INTERVAL = "END DATE TIME ("
            + PREFIX_END_DATE_TIME + ") should be after START DATE TIME ("
            + PREFIX_START_DATE_TIME + ")";

    private final Event toDo;

    /**
     * Creates an AddEventCommand to add the specified {@code Event}
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        toDo = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(toDo)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        if (!DateTime.isValidInterval(toDo.getStartDateTime(), toDo.getEndDateTime())) {
            throw new CommandException(MESSAGE_INVALID_INTERVAL);
        }

        model.addEvent(this.toDo);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDo));
    }

    /**
     * Returns true if the two events have the same identity and data fields.
     * This defines a weaker notion of equality between two events.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddEventCommand)) {
            return false;
        }

        AddEventCommand otherCommand = (AddEventCommand) other;
        return toDo.equals(otherCommand.toDo);
    }
}

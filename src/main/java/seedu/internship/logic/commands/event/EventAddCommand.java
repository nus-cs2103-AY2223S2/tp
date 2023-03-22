package seedu.internship.logic.commands.event;

import static seedu.internship.logic.parser.CliSyntax.*;

import static java.util.Objects.requireNonNull;


import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;
import seedu.internship.model.event.Event;


public class EventAddCommand extends EventCommand {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = EventCommand.COMMAND_WORD +
            EventAddCommand.COMMAND_WORD + ": Adds a event to the event catalogue. "
            + "Parameters: "
            + PREFIX_EVENT_NAME + "EVENT NAME"
            + PREFIX_EVENT_START + "START DATE TIME "
            + PREFIX_EVENT_END + "END DATE TIME "
            + PREFIX_EVENT_DESCRIPTION + "DESCRIPTION "
            + "Example: " + EventCommand.COMMAND_WORD + " "
            + EventAddCommand.COMMAND_WORD + " "
            + PREFIX_EVENT_NAME + "Technical Interview"
            + PREFIX_EVENT_START + "10/09/2023 1500 "
            + PREFIX_EVENT_END + "10/09/2023 1700 "
            + PREFIX_EVENT_DESCRIPTION + "On Zoom ";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the catalogue";
    public static final String MESSAGE_NO_INTERNSHIP_SELECTED = "Select an internship before adding an event.";

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

        if (model.hasEvent(eventToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        // Retreive the Selected Internship Index
        if (!model.hasSelectedInternship()) {
            throw new CommandException(MESSAGE_NO_INTERNSHIP_SELECTED );
        }
        // Adding internship infromation to event
        eventToAdd.setInternship(model.getSelectedInternship());

        model.addEvent(eventToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, eventToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventAddCommand // instanceof handles nulls
                && eventToAdd.equals(((EventAddCommand) other).eventToAdd));
    }

}

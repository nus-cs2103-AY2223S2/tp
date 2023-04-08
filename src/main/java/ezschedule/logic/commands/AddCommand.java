package ezschedule.logic.commands;

import static ezschedule.logic.parser.CliSyntax.PREFIX_DATE;
import static ezschedule.logic.parser.CliSyntax.PREFIX_END;
import static ezschedule.logic.parser.CliSyntax.PREFIX_NAME;
import static ezschedule.logic.parser.CliSyntax.PREFIX_START;
import static java.util.Objects.requireNonNull;

import ezschedule.logic.commands.exceptions.CommandException;
import ezschedule.model.Model;
import ezschedule.model.event.Event;

/**
 * Adds an {@code Event} to the {@code Scheduler}.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the scheduler."
            + "\nParameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DATE + "DATE "
            + PREFIX_START + "START TIME "
            + PREFIX_END + "END TIME "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tennis "
            + PREFIX_DATE + "2023-12-20 "
            + PREFIX_START + "18:00 "
            + PREFIX_END + "20:00";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the scheduler";
    public static final String MESSAGE_EVENT_EXIST_AT_TIME = "Another event already exists at the chosen time";

    private final Event toAdd;

    /**
     * Creates an AddEventCommand to add the specified {@code Event}.
     */
    public AddCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public String commandWord() {
        return COMMAND_WORD;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        } else if (model.hasEventAtTime(toAdd)) {
            throw new CommandException(MESSAGE_EVENT_EXIST_AT_TIME);
        } else {
            model.clearRecent();
            model.recentCommands().add(this);
            model.addEvent(toAdd);
            model.recentEvent().add(toAdd);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}

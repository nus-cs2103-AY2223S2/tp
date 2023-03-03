package seedu.address.logic.commands.commands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.pilot.Pilot;

/**
 * The command that adds a pilot to the address book.
 */
public class AddPilotCommand implements Command {
    /**
     * The pilot to be added.
     */
    private final Pilot pilot;

    /**
     * Creates a command that, when executed, adds the pilot to the address
     * book.
     *
     * @param pilot The pilot to be added.
     */
    public AddPilotCommand(Pilot pilot) {
        this.pilot = pilot;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.addPilot(pilot);
        // TODO: better message
        return new CommandResult("Added pilot: " + pilot);
    }
}

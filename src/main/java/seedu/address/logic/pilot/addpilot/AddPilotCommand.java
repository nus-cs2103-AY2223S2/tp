package seedu.address.logic.pilot.addpilot;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
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
        return new CommandResult(String.format(
                "Added %s.",
                pilot.toString()
        ));
    }
}

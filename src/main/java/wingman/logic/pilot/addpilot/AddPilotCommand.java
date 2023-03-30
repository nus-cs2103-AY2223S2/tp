package wingman.logic.pilot.addpilot;

import wingman.logic.core.Command;
import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;
import wingman.model.pilot.Pilot;

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

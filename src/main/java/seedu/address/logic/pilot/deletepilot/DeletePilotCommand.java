package seedu.address.logic.pilot.deletepilot;

import java.util.Optional;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exception.IndexOutOfBoundException;
import seedu.address.model.pilot.Pilot;

/**
 * The command that deletes a pilot from the address book.
 */
public class DeletePilotCommand implements Command {
    /**
     * The UUID of the pilot to be deleted.
     */
    private final String id;

    /**
     * This can be used to undo the deletion.
     */
    private Optional<Pilot> pilotToDelete;

    /**
     * Creates a command that, when executed, deletes the pilot with the
     * given uuid.
     *
     * @param id The index of the pilot to be deleted.
     */
    public DeletePilotCommand(String id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        int index = Integer.parseInt(id);
        try {
            pilotToDelete = model.getPilotManager().getItemByIndex(index);
            model.deleteLocationByIndex(index);
        } catch (IndexOutOfBoundException e) {
            return new CommandResult(
                    String.format("Error: %s", e.getMessage())
            );
        }
        return new CommandResult("Deleted pilot: " + id);
    }
}

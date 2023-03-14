package seedu.address.logic.crew.deletecrew;

import java.util.Optional;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.crew.Crew;

/**
 * The command that deletes a crew from the address book.
 */
public class DeleteCrewCommand implements Command {
    /**
     * The UUID of the crew to be deleted.
     */
    private final String uuid;

    /**
     * This can be used to undo the deletion.
     */
    private Optional<Crew> crewToDelete;

    /**
     * Creates a command that, when executed, deletes the pilot with the
     * given uuid.
     *
     * @param uuid The index of the pilot to be deleted.
     */
    public DeleteCrewCommand(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        crewToDelete = model.getCrewManager().getItem(uuid);
        model.deleteCrew(uuid);
        return new CommandResult("Deleted crew: " + uuid);
    }
}

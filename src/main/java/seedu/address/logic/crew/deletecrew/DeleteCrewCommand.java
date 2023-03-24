package seedu.address.logic.crew.deletecrew;

import java.util.Optional;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.crew.Crew;
import seedu.address.model.exception.IndexOutOfBoundException;


/**
 * The command that deletes a crew from the address book.
 *
 */
public class DeleteCrewCommand implements Command {
    /**
     * The id of the crew to be deleted.
     */
    private final String id;

    /**
     * This can be used to undo the deletion.
     */
    private Optional<Crew> crewToDelete;

    /**
     * Creates a command that, when executed, deletes the pilot with the
     * given id.
     *
     * @param uuid The index of the pilot to be deleted.
     */
    public DeleteCrewCommand(String id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        int index = Integer.parseInt(id);
        try {
            crewToDelete = model.getCrewManager().getItemByIndex(index);
        } catch (IndexOutOfBoundException e) {
            return new CommandResult(
                    String.format("Error: %s", e.getMessage())
            );
        }
        model.deleteCrewByIndex(index);
        return new CommandResult("Deleted crew: " + id);
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a technician identified using its id.
 */
public class DeleteTechnicianCommand extends Command {

    public static final String COMMAND_WORD = "deletetechnician";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the technician identified by the id number displayed by the list command.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TECHNICIAN_SUCCESS = "Deleted Technician: %d";

    private final int id;

    public DeleteTechnicianCommand(int id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.getShop().removeTechnician(id);
            model.resetSelected();
            return new CommandResult(String.format(MESSAGE_DELETE_TECHNICIAN_SUCCESS, id), Tab.TECHNICIANS);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteTechnicianCommand // instanceof handles nulls
            && id == ((DeleteTechnicianCommand) other).id); // state check
    }
}

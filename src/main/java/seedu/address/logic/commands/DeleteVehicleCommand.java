package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a vehicle identified using its id.
 */
public class DeleteVehicleCommand extends Command {

    public static final String COMMAND_WORD = "deletevehicle";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the vehicle identified by the id number displayed by the list command.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_VEHICLE_SUCCESS = "Deleted Vehicle: %1$s";
    private final int id;

    public DeleteVehicleCommand(int id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.getShop().removeVehicle(id);
            model.resetSelected();
            return new CommandResult(String.format(MESSAGE_DELETE_VEHICLE_SUCCESS, id), Tab.VEHICLES);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteVehicleCommand // instanceof handles nulls
            && this.id == ((DeleteVehicleCommand) other).id); // state check
    }
}

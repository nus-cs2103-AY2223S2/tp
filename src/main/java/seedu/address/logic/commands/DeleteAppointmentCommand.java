package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.service.Vehicle;

/**
 * Deletes a vehicle identified using it's displayed index from viewvehicle.
 */
public class DeleteVehicleCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "deletevehicle";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the vehicle identified by the id number displayed by the list command.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_VEHICLE_SUCCESS = "Deleted Vehicle: %1$s";

    private final Index targetIndex;

    public DeleteVehicleCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult executeUndoableCommand(Model model) throws CommandException {
        requireNonNull(model);
        List<Vehicle> lastShownList = model.getFilteredVehicleList();

        Vehicle vehicleToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteVehicle(vehicleToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_VEHICLE_SUCCESS, vehicleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteVehicleCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteVehicleCommand) other).targetIndex)); // state check
    }
}

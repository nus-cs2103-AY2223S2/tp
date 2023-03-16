package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.service.VehicleIdPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewVehicleCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "viewvehicle";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display vehicle details given the vehicle id (not"
            + "vehicle plate).\n"
            + "Parameters: ID\n"
            + "Example: " + COMMAND_WORD + " 8";

    private final VehicleIdPredicate predicate;

    public ViewVehicleCommand(VehicleIdPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult executeUndoableCommand(Model model) {
        requireNonNull(model);
        model.updateFilteredVehicleList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_VEHICLE_VIEW_OVERVIEW));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewVehicleCommand // instanceof handles nulls
                && predicate.equals(((ViewVehicleCommand) other).predicate)); // state check
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_ID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.service.Service;

/**
 * Manages adding services
 */
public class AddServiceCommand extends RedoableCommand {
    public static final String COMMAND_WORD = "addservice";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a service to the shop. "
            + "Parameters: "
            + PREFIX_VEHICLE_ID + "VEHICLE ID "
            + PREFIX_SERVICE_TYPE + "TYPE "
            + PREFIX_SERVICE_DURATION + "SERVICE DURATION "
            + "Optional: " + PREFIX_SERVICE_STATUS + "STATUS "
            + "Optional: " + PREFIX_SERVICE_DESCRIPTION + "DESCRIPTION "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_VEHICLE_ID + "10 "
            + PREFIX_SERVICE_TYPE + "standard "
            + PREFIX_SERVICE_STATUS + "in progress "
            + PREFIX_SERVICE_DESCRIPTION + "Customer says abc";

    public static final String MESSAGE_SUCCESS = "New service added: %1$s";
    public static final String MESSAGE_VEHICLE_NOT_FOUND = "Vehicle %d not in system";
    public static final String MESSAGE_DUPLICATE_SERVICE = "Service %d already in system";

    private final Service toAdd;

    /**
     * Constructs command that adds service to the model
     *
     * @param service Service to be added
     */
    public AddServiceCommand(Service service) {
        this.toAdd = service;
    }

    /**
     * Execution of command
     *
     * @param model {@code Model} which the command should operate on.
     * @return Result of command execution
     * @throws CommandException If error occurs during command execution
     */
    @Override
    public CommandResult executeUndoableCommand(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasVehicle(toAdd.getVehicleId())) {
            throw new CommandException(String.format(MESSAGE_VEHICLE_NOT_FOUND, toAdd.getVehicleId()));
        }
        if (model.hasService(toAdd.getId())) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_SERVICE, toAdd.getId()));
        }
        model.addService(toAdd.getVehicleId(), toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddServiceCommand // instanceof handles nulls
                && toAdd.equals(((AddServiceCommand) other).toAdd));
    }
}

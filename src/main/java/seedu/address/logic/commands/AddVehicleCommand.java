package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BRAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATE_NUM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_TYPE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ShopModel;
import seedu.address.model.Vehicle;

/**
 * Manages adding vehicles
 */
public class AddVehicleCommand extends ShopCommand {
    public static final String COMMAND_WORD = "addvehicle";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a vehicle to the shop. "
            + "Parameters: "
            + PREFIX_PLATE_NUM + "PLATE NUMBER "
            + PREFIX_BRAND + "VEHICLE BRAND "
            + PREFIX_CUSTOMER_ID + "OWNER ID "
            + PREFIX_VEHICLE_TYPE + "VEHICLE TYPE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PLATE_NUM + "SBA1234A "
            + PREFIX_BRAND + "Toyota "
            + PREFIX_CUSTOMER_ID + "1 "
            + PREFIX_VEHICLE_TYPE + "4wd ";

    public static final String MESSAGE_SUCCESS = "New vehicle added: %1$s";
    public static final String MESSAGE_DUPLICATE_VEHICLE = "This vechicle already registered";
    public static final String MESSAGE_CUSTOMER_NOT_FOUND = "Customer not registered";

    private final Vehicle toAdd;

    /**
     * Constructs command that adds vehicle to the model
     *
     * @param vehicle Customer to be added
     */
    public AddVehicleCommand(Vehicle vehicle) {
        this.toAdd = vehicle;
    }

    /**
     * Execution of command
     *
     * @param model {@code Model} which the command should operate on.
     * @return Result of command execution
     * @throws CommandException If error occurs during command execution
     */
    @Override
    public CommandResult execute(ShopModel model) throws CommandException {
        requireNonNull(model);
        if (!model.hasCustomer(toAdd.getOwnerId())) {
            throw new CommandException(MESSAGE_CUSTOMER_NOT_FOUND);
        }
        if (model.hasVehicle(toAdd.getId())) {
            throw new CommandException(MESSAGE_DUPLICATE_VEHICLE);
        }

        model.addVehicle(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddVehicleCommand // instanceof handles nulls
                && toAdd.equals(((AddVehicleCommand) other).toAdd));
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BRAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATE_NUM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_TYPE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.service.VehicleType;

/**
 * Adds a vehicle to the shop
 */
public class AddVehicleCommand extends Command {
    public static final String COMMAND_WORD = "addvehicle";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a vehicle to the shop. "
        + "Parameters: "
        + PREFIX_PLATE_NUM + "PLATE NUMBER "
        + PREFIX_BRAND + "VEHICLE BRAND "
        + PREFIX_CUSTOMER_ID + "OWNER ID "
        + PREFIX_VEHICLE_COLOR + "COLOR "
        + PREFIX_VEHICLE_TYPE + "VEHICLE TYPE "
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_PLATE_NUM + "SBA1234A "
        + PREFIX_BRAND + "Toyota "
        + PREFIX_VEHICLE_COLOR + "red"
        + PREFIX_CUSTOMER_ID + "1 "
        + PREFIX_VEHICLE_TYPE + "4wd ";

    public static final String MESSAGE_SUCCESS = "New vehicle added";

    private final int ownerId;
    private final String plateNum;
    private final String color;
    private final String brand;
    private final VehicleType vehicleType;

    /**
     * Creates an AddCommand to add the specified {@code Vehicle}
     *
     * @param ownerId     ID of owner
     * @param plateNum    plate number of vehicle
     * @param color       color of vehicle
     * @param brand       brand of vehicle
     * @param vehicleType type of vehicle
     */
    public AddVehicleCommand(int ownerId, String plateNum, String color, String brand, VehicleType vehicleType) {
        this.ownerId = ownerId;
        this.plateNum = plateNum;
        this.color = color;
        this.brand = brand;
        this.vehicleType = vehicleType;
    }

    /**
     * Execution of command
     *
     * @param model {@code Model} which the command should operate on.
     * @return Result of command execution
     * @throws CommandException If error occurs during command execution
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.getShop().addVehicle(ownerId, plateNum, color, brand, vehicleType);
            model.updateFilteredVehicleList(Model.PREDICATE_SHOW_ALL_VEHICLES);
            model.selectVehicle(lst -> lst.get(lst.size() - 1));
            return new CommandResult(MESSAGE_SUCCESS, Tab.VEHICLES);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}

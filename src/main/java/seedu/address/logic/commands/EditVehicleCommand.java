package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BRAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATE_NUM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_TYPE;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.service.VehicleType;

/**
 * Edits the details of an existing vehicle in the shop.
 */
public class EditVehicleCommand extends Command {

    public static final String COMMAND_WORD = "editvehicle";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the customer identified "
        + "by the id number displayed by listvehicle. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: "
        + PREFIX_INTERNAL_ID + "VEHICLE_ID "
        + "[" + PREFIX_PLATE_NUM + "PLATE NUMBER] "
        + "[" + PREFIX_BRAND + "VEHICLE BRAND] "
        + "[" + PREFIX_CUSTOMER_ID + "OWNER ID] "
        + "[" + PREFIX_VEHICLE_COLOR + "COLOR]"
        + "[" + PREFIX_VEHICLE_TYPE + "VEHICLE TYPE]"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_PLATE_NUM + "SBA1234A "
        + PREFIX_VEHICLE_COLOR + "blue";

    public static final String MESSAGE_EDIT_VEHICLE_SUCCESS = "Edited vehicle %d";

    private final int id;
    private final Optional<Integer> ownerId;
    private final Optional<String> plateNumber;
    private final Optional<String> color;
    private final Optional<String> brand;
    private final Optional<VehicleType> type;

    /**
     * @param id          ID of vehicle to edit
     * @param ownerId     ID of new owner
     * @param plateNumber new plate number
     * @param color       new color
     * @param brand       new brand
     * @param type        new type
     */
    public EditVehicleCommand(int id, Optional<Integer> ownerId, Optional<String> plateNumber,
                              Optional<String> color, Optional<String> brand, Optional<VehicleType> type) {
        this.id = id;
        this.ownerId = ownerId;
        this.plateNumber = plateNumber;
        this.color = color;
        this.brand = brand;
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.getShop().editVehicle(id, ownerId, plateNumber, color, brand, type);
            model.resetSelected();
            model.selectVehicle(lst -> lst.stream().filter(v -> v.getId() == id).findFirst().get());
            return new CommandResult(String.format(MESSAGE_EDIT_VEHICLE_SUCCESS, id), Tab.VEHICLES);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}

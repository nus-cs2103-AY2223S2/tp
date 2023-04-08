package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_ID;

import java.time.LocalDate;
import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.service.ServiceStatus;

/**
 * Manages adding services
 */
public class AddServiceCommand extends Command {
    public static final String COMMAND_WORD = "addservice";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a service to the shop. "
            + "Parameters: "
            + PREFIX_VEHICLE_ID + "VEHICLE ID "
            + PREFIX_SERVICE_DESCRIPTION + "DESCRIPTION "
            + "Optional: " + PREFIX_SERVICE_DURATION + "SERVICE DURATION "
            + "Optional: " + PREFIX_SERVICE_STATUS + "STATUS "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_VEHICLE_ID + "10 "
            + PREFIX_SERVICE_DURATION + "8 "
            + PREFIX_SERVICE_STATUS + "in progress "
            + PREFIX_SERVICE_DESCRIPTION + "Customer says abc";

    public static final String MESSAGE_SUCCESS = "New service added";
    private final int vehicleId;
    private final Optional<LocalDate> entryDate;
    private final String description;
    private final Optional<LocalDate> estimatedFinishDate;
    private final Optional<ServiceStatus> serviceStatus;

    /**
     * Constructs command that adds service to the model
     *
     * @param vehicleId           ID of vehicle
     * @param entryDate           Optional: entry date of service
     * @param description         description of service
     * @param estimatedFinishDate Optional: estimated finish date of service
     * @param serviceStatus       Optional: status of service
     */
    public AddServiceCommand(int vehicleId, Optional<LocalDate> entryDate, String description,
                             Optional<LocalDate> estimatedFinishDate, Optional<ServiceStatus> serviceStatus) {
        this.vehicleId = vehicleId;
        this.entryDate = entryDate;
        this.description = description;
        this.estimatedFinishDate = estimatedFinishDate;
        this.serviceStatus = serviceStatus;
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
            model.getShop().addService(vehicleId, entryDate, description,
                    estimatedFinishDate, serviceStatus);
            model.updateFilteredServiceList(Model.PREDICATE_SHOW_ALL_SERVICES);
            model.selectService(lst -> lst.get(lst.size() - 1));
            return new CommandResult(MESSAGE_SUCCESS, Tab.SERVICES);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}

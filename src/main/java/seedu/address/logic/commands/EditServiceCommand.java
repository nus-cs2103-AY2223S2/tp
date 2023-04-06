package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_ID;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.service.PartMap;
import seedu.address.model.service.Service;
import seedu.address.model.service.ServiceStatus;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditServiceCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "editservice";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the service identified "
        + "by the id number displayed by listservice. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: "
        + PREFIX_INTERNAL_ID + "SERVICE_INDEX "
        + "[" + PREFIX_VEHICLE_ID + " VEHICLE ID]"
        + "[" + PREFIX_SERVICE_START + " SERVICE START DATE]"
        + "[" + PREFIX_SERVICE_END + " SERVICE END DATE]"
        + "[" + PREFIX_SERVICE_STATUS + " STATUS]"
        + "[" + PREFIX_SERVICE_DESCRIPTION + " DESCRIPTION]"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_VEHICLE_ID + "10 "
        + PREFIX_SERVICE_DURATION + "8 "
        + PREFIX_SERVICE_STATUS + "in progress "
        + PREFIX_SERVICE_DESCRIPTION + "Customer says abc";

    public static final String MESSAGE_EDIT_SERVICE_SUCCESS = "Edited service %d";

    private final int id;
    private final Optional<Integer> vehicleId;
    private final Optional<LocalDate> startDate;
    private final Optional<LocalDate> endDate;
    private final Optional<ServiceStatus> status;
    private final Optional<String> description;

    /**
     * @param id          ID of the service
     * @param vehicleId   ID of the vehicle
     * @param startDate   Start date of the service
     * @param endDate     End date of the service
     * @param status      Status of the service
     * @param description Description of the service
     */
    public EditServiceCommand(int id, Optional<Integer> vehicleId, Optional<LocalDate> startDate,
                              Optional<LocalDate> endDate, Optional<ServiceStatus> status,
                              Optional<String> description) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.description = description;
    }

    @Override
    public CommandResult executeUndoableCommand(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.getShop().editService(id, vehicleId, startDate, description, endDate, status);
            return new CommandResult(String.format(MESSAGE_EDIT_SERVICE_SUCCESS, id), Tab.SERVICES);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}

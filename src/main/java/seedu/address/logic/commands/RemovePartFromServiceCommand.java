package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_ID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Removes a specific part from a specific service
 */
public class RemovePartFromServiceCommand extends Command {
    public static final String COMMAND_WORD = "removeservicepart";
    public static final String MESSAGE_SUCCESS_FORMAT = "%s (x %d) removed from service %d";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Removes parts reserved for service and puts it back into global part storage. "
        + "Parameters: "
        + PREFIX_SERVICE_ID + "SERVICE ID "
        + PREFIX_PART_NAME + "PART NAME "
        + PREFIX_QUANTITY + "PART QUANTITY "
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_SERVICE_ID + "1 "
        + PREFIX_PART_NAME + "Cylinder Head "
        + PREFIX_QUANTITY + "50";

    private final int serviceId;
    private final String partName;
    private final int quantity;

    /**
     * @param serviceId ID of service
     * @param partName  Name of part
     * @param quantity  Quantity of part
     */
    public RemovePartFromServiceCommand(int serviceId, String partName, int quantity) {
        this.serviceId = serviceId;
        this.partName = partName;
        this.quantity = quantity;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            model.getShop().removePartFromService(partName, serviceId, quantity);
            model.selectService(lst -> lst.stream().filter(s -> s.getId() == serviceId)
                .findFirst().orElse(null));
            return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT, this.partName, this.quantity,
                this.serviceId), Tab.SERVICES);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}

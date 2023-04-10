package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PART_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_ID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a part to a specific existing service
 */
public class AddPartToServiceCommand extends Command {
    public static final String COMMAND_WORD = "addservicepart";
    public static final String MESSAGE_SUCCESS_FORMAT = "%d x (%s) added to service %d";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a part to the service. "
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
     * Constructs command that adds part to a specified service
     *
     * @param serviceId ID of service
     * @param partName  Name of part
     * @param quantity  Quantity of part
     */
    public AddPartToServiceCommand(int serviceId, String partName, int quantity) {
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
            model.getShop().addPartToService(serviceId, partName, quantity);
            model.selectService(lst -> lst.stream()
                .filter(s -> s.getId() == serviceId)
                .findFirst()
                .get());
            return new CommandResult(
                String.format(MESSAGE_SUCCESS_FORMAT, this.quantity, this.partName, this.serviceId),
                Tab.SERVICES);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}

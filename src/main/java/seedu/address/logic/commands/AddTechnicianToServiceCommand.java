package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TECHNICIAN_ID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds an existing technician to a specific service
 */
public class AddTechnicianToServiceCommand extends Command {
    public static final String COMMAND_WORD = "addservicetech";
    public static final String MESSAGE_SUCCESS_FORMAT = "Technician %d added to Service %d";
    public static final String COMMAND_USAGE =
        COMMAND_WORD + ": Assigns an existing technician to an existing service. "
            + "Parameters: "
            + PREFIX_TECHNICIAN_ID + "TECHNICIAN_ID "
            + PREFIX_SERVICE_ID + "SERVICE_ID "
            + "Example Usage: "
            + PREFIX_TECHNICIAN_ID + "2 "
            + PREFIX_SERVICE_ID + "3";

    private final int techId;
    private final int serviceId;

    /**
     * Constructs command that adds an existing technician to
     * specified service
     *
     * @param techId    ID of technician
     * @param serviceId ID of service
     */
    public AddTechnicianToServiceCommand(int techId, int serviceId) {
        this.techId = techId;
        this.serviceId = serviceId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            model.getShop().addTechnicianToService(techId, serviceId);
            model.selectService(lst -> lst.stream().filter(a -> a.getId() == serviceId).findFirst().get());
            return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT, this.techId, this.serviceId),
                Tab.SERVICES);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}

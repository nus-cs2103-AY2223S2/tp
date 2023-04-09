package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TECHNICIAN_ID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Removes a specific technician from a specific service
 */
public class RemoveTechnicianFromServiceCommand extends Command {
    public static final String COMMAND_WORD = "removeservicetech";
    public static final String MESSAGE_SUCCESS_FORMAT = "Technician %d unassigned from Service %d";
    public static final String COMMAND_USAGE =
        COMMAND_WORD + ": Unassigns an existing technician from an existing service. "
            + "Parameters: "
            + PREFIX_TECHNICIAN_ID + "TECHNICIAN_ID "
            + PREFIX_SERVICE_ID + "SERVICE_ID "
            + "Example Usage: "
            + PREFIX_TECHNICIAN_ID + "2 "
            + PREFIX_SERVICE_ID + "3";

    private final int techId;
    private final int serviceId;

    /**
     * @param techId    ID of technician
     * @param serviceId ID of service
     */
    public RemoveTechnicianFromServiceCommand(int techId, int serviceId) {
        this.techId = techId;
        this.serviceId = serviceId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            model.getShop().removeTechnicianFromService(techId, serviceId);
            model.selectService(lst -> lst.stream().filter(s -> s.getId() == serviceId)
                .findFirst().orElse(null));
            return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT, this.techId, this.serviceId),
                Tab.SERVICES);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}

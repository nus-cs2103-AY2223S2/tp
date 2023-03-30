package seedu.address.logic.commands;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.service.Vehicle;

/**
 * Manages Sorting of vehicles
 */
public class SortVehiclesCommand extends Command{
    public static final String COMMAND_WORD = "sortvehicles";
    public static final String MESSAGE_SUCCESS = "Sorted Vehicles";

    private final Comparator<Vehicle> cmp;

    public SortVehiclesCommand(Comparator<Vehicle> cmp) {
        this.cmp = cmp;
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
        model.updateVehicleComparator(cmp);
        return new CommandResult(MESSAGE_SUCCESS, Tab.VEHICLES);
    }
}

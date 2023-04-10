package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_REVERSE_SORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.service.Vehicle;

/**
 * Sorts displayed list of vehicles
 */
public class SortVehiclesCommand extends Command {
    public static final String COMMAND_WORD = "sortvehicles";
    public static final String MESSAGE_SUCCESS = "Sorted Vehicles";
    public static final String COMMAND_USAGE = COMMAND_WORD + ": Sorts vehicles by attribute. "
        + "Parameters: "
        + PREFIX_SORT_BY + "[id | owner id | plate | brand | color | type | service qty] "
        + "Optional: "
        + PREFIX_REVERSE_SORT;

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
        model.selectVehicle(lst -> lst.isEmpty() ? null : lst.get(0));
        return new CommandResult(MESSAGE_SUCCESS, Tab.VEHICLES);
    }
}

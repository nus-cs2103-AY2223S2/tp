package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_VEHICLES;

import seedu.address.model.Model;

/**
 * Lists all vehicles in the shop to the user.
 */
public class ListVehiclesCommand extends Command {

    public static final String COMMAND_WORD = "listvehicles";

    public static final String MESSAGE_SUCCESS = "Currently listing all vehicles.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredVehicleList(PREDICATE_SHOW_ALL_VEHICLES);
        model.updateVehicleComparator((a, b) -> a.getId() - b.getId());
        model.selectVehicle(lst -> lst.isEmpty() ? null : lst.get(0));
        return new CommandResult(MESSAGE_SUCCESS, Tab.VEHICLES);
    }
}











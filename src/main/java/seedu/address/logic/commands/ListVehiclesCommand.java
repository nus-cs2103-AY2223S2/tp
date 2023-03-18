package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_VEHICLES;

import seedu.address.model.Model;

/**
 * Lists all vehicles in the AutoM8 system to the user.
 */
public class ListVehiclesCommand extends Command {

    public static final String COMMAND_WORD = "listvehicles";

    public static final String MESSAGE_SUCCESS = "Listed all vehicles.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredVehicleList(PREDICATE_SHOW_ALL_VEHICLES);
        return new CommandResult(MESSAGE_SUCCESS, ResultType.LISTED_VEHICLES);
    }
}











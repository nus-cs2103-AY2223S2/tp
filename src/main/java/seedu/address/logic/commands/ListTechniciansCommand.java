package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TECHNICIANS;

import seedu.address.model.Model;

/**
 * Lists all customers in shop to the user.
 */
public class ListTechniciansCommand extends Command {

    public static final String COMMAND_WORD = "listtechnicians";

    public static final String MESSAGE_SUCCESS = "Currently listing all technicians.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTechnicianList(PREDICATE_SHOW_ALL_TECHNICIANS);
        model.updateTechnicianComparator((a, b) -> a.getId() - b.getId());
        model.selectTechnician(lst -> lst.isEmpty() ? null : lst.get(0));
        return new CommandResult(MESSAGE_SUCCESS, Tab.TECHNICIANS);
    }
}











package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SERVICES;

import seedu.address.model.Model;

/**
 * Lists all services in the shop to the user.
 */
public class ListServicesCommand extends Command {

    public static final String COMMAND_WORD = "listservices";

    public static final String MESSAGE_SUCCESS = "Currently listing all services.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredServiceList(PREDICATE_SHOW_ALL_SERVICES);
        model.updateServiceComparator((a, b) -> a.getId() - b.getId());
        model.selectService(lst -> lst.isEmpty() ? null : lst.get(0));
        return new CommandResult(MESSAGE_SUCCESS, Tab.SERVICES);
    }
}











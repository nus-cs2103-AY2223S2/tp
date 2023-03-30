package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.service.Service;
import seedu.address.model.service.ServiceIdPredicate;

/**
 * Finds and returns the service details of the provided id.
 */
public class ViewServiceCommand extends Command {

    public static final String COMMAND_WORD = "viewservice";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display service details given the service id. "
            + "Parameters: ID\n"
            + "Example: " + COMMAND_WORD + " 8";

    private final ServiceIdPredicate predicate;

    public ViewServiceCommand(ServiceIdPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredServiceList(predicate);
        Service current = model.getFilteredServiceList().get(0);
        model.selectService(current);
        return new CommandResult(
                String.format(Messages.MESSAGE_SERVICE_VIEW_OVERVIEW, current.getId()),
                Tab.SERVICES);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewServiceCommand // instanceof handles nulls
                && predicate.equals(((ViewServiceCommand) other).predicate)); // state check
    }
}

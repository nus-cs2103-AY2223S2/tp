package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.entity.person.TechnicianIdPredicate;

/**
 * Finds and returns the customer details of the provided id.
 */
public class ViewTechnicianCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "viewtechnician";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display technician details given the customer id."
            + "Parameters: ID\n"
            + "Example: " + COMMAND_WORD + " 8";

    private final TechnicianIdPredicate predicate;

    public ViewTechnicianCommand(TechnicianIdPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult executeUndoableCommand(Model model) {
        requireNonNull(model);
        model.updateFilteredTechnicianList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CUSTOMER_VIEW_OVERVIEW));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewTechnicianCommand // instanceof handles nulls
                && predicate.equals(((ViewTechnicianCommand) other).predicate)); // state check
    }
}

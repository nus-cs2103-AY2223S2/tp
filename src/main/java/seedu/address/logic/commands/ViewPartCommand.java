package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.service.PartIdPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewPartCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "viewpart";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display vehicle details given the vehicle id (not"
            + "vehicle plate).\n"
            + "Parameters: ID\n"
            + "Example: " + COMMAND_WORD + " 8";

    private final PartIdPredicate predicate;

    public ViewPartCommand(PartIdPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult executeUndoableCommand(Model model) {
        requireNonNull(model);
        model.updateFilteredPartList(predicate); // todo: FIX
        return new CommandResult(
                String.format(Messages.MESSAGE_PART_VIEW_OVERVIEW));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewPartCommand // instanceof handles nulls
                && predicate.equals(((ViewPartCommand) other).predicate)); // state check
    }
}

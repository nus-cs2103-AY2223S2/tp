package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all parts in the shop to the user.
 */
public class ListPartsCommand extends Command {

    public static final String COMMAND_WORD = "listparts";

    public static final String MESSAGE_SUCCESS = "Currently listing all parts.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPartMap(Model.PREDICATE_SHOW_ALL_PARTS);
        return new CommandResult(MESSAGE_SUCCESS, Tab.PARTS);
    }
}











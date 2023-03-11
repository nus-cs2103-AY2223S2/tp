package seedu.address.logic.commands.group;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a group to the address book.
 */
public class GroupDeleteCommand extends GroupCommand {
    public static final String SUB_COMMAND_WORD = "delete";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}

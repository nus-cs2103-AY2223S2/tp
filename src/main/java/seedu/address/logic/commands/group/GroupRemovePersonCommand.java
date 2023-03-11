package seedu.address.logic.commands.group;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Removes a person from a group in the address book.
 */
public class GroupRemovePersonCommand extends GroupCommand {
    public static final String SUB_COMMAND_WORD = "remove";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}

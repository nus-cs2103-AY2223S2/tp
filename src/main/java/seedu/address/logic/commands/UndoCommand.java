package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undo the previously executed command.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE_SUCCESS = "Undo success!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        try {
            model.getShop().revert();
            model.resetSelected();
            return new CommandResult(MESSAGE_USAGE_SUCCESS, Tab.ALL);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}

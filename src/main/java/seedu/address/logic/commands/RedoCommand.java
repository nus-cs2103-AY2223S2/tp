package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Redo the previously executed command.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo success!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        try {
            model.getShop().redo();
            model.resetSelected();
            return new CommandResult(MESSAGE_SUCCESS, Tab.ALL);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}

package arb.logic.commands.project;

import static java.util.Objects.requireNonNull;

import arb.commons.core.Messages;
import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.logic.commands.exceptions.CommandException;
import arb.model.ListType;
import arb.model.Model;

/**
 * Clears the project list of the address book.
 */
public class ClearProjectCommand extends Command {

    public static final String COMMAND_WORD = "clear-project";
    public static final String MESSAGE_SUCCESS = "Project list has been cleared!";


    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) throws CommandException {
        requireNonNull(model);

        if (currentListBeingShown != ListType.PROJECT) {
            throw new CommandException(Messages.MESSAGE_INVALID_LIST_PROJECT);
        }

        model.resetProjectList();

        return new CommandResult(MESSAGE_SUCCESS, ListType.PROJECT);
    }
}

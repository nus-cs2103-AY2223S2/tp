package arb.logic.commands.tag;

import static java.util.Objects.requireNonNull;

import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.model.ListType;
import arb.model.Model;

/**
 * Lists all tags in the address book to the user.
 */
public class ListTagCommand extends Command {

    public static final String COMMAND_WORD = "list-tag";

    public static final String MESSAGE_SUCCESS = "Listed all tags";

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, ListType.TAG);
    }
}

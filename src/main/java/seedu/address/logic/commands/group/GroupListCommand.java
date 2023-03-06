package seedu.address.logic.commands.group;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all groups in the address book to the user.
 */
public class GroupListCommand extends GroupCommand {

    public static final String SUB_COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all group";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // TODO: REQUIRE grouplist, replace line below
        // model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

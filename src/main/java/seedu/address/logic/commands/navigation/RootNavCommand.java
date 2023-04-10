package seedu.address.logic.commands.navigation;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Navigates to the root location.
 */
public class RootNavCommand extends NavCommand {

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.navigateToRoot();
        listAtRoot(model);
        return getSuccessfulCommandResult(model.getCurrentNavContext(), model);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof RootNavCommand);
    }
}

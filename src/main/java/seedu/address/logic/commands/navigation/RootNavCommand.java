package seedu.address.logic.commands.navigation;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class RootNavCommand extends NavCommand {

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.navigateToRoot();
        return new CommandResult(getSuccessfulNavMessage(model.getCurrentNavContext()));
    }
}

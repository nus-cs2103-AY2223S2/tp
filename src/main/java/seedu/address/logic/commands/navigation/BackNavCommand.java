package seedu.address.logic.commands.navigation;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.navigation.NavigationContext;

/**
 * Navigates backwards in the hierarchy.
 */
public class BackNavCommand extends Command {

    public static final String COMMAND_WORD = "navb";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Navigates to the previous location"
            + "Parameters: {module_code / lecture_name} \n" + "Example: " + COMMAND_WORD + "\n";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.navigateBack();

        NavigationContext navContext = model.getCurrentNavContext();
        list(navContext, model);

        return NavCommand.getSuccessfulCommandResult(navContext, model);
    }

    private void list(NavigationContext navContext, Model model) throws CommandException {
        switch (navContext.getLayer()) {
        case MODULE:
            new ListCommand(navContext.getModuleCode()).execute(model);
            break;
        case ROOT:
            new ListCommand().execute(model);
            break;
        default:
            break;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof BackNavCommand);
    }
}

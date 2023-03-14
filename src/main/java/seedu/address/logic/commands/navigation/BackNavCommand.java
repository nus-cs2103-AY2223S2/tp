package seedu.address.logic.commands.navigation;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class BackNavCommand extends Command {

    public static final String COMMAND_WORD = "navb";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Navigates to the previous location"
            + "Parameters: {module_code / lecture_name} \n" + "Example: " + COMMAND_WORD + "\n";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.navigateBack();
        return new CommandResult(NavCommand.getSuccessfulNavMessage(model.getCurrentNavContext()));
    }
}

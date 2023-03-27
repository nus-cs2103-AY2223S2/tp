package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Command to change to light theme
 */
public class LightCommand extends Command {
    public static final String COMMAND_WORD = "light";
    public static final String MESSAGE_SUCCESS = "Successfully changed to the light theme !";
    public static final String MESSAGE_ERROR = "Already in the light theme";
    private static final String cssFilePath = "view/LightTheme.css";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (cssFilePath.equals(model.getCssFilePath())) {
            return new CommandResult(MESSAGE_ERROR);
        } else {
            return new CommandResult(MESSAGE_SUCCESS, false);
        }
    }
}


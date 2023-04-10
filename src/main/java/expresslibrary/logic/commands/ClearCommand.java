package expresslibrary.logic.commands;

import static java.util.Objects.requireNonNull;

import expresslibrary.model.ExpressLibrary;
import expresslibrary.model.Model;

/**
 * Clears the express library.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "ExpressLibrary has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setExpressLibrary(new ExpressLibrary());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

package wingman.logic.toplevel.exit;

import javafx.application.Platform;
import wingman.logic.core.Command;
import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;

/**
 * The command that ends the whole program.
 */
public class ExitCommand implements Command {
    public ExitCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Platform.exit();
        return new CommandResult("Bye bye!");
    }
}

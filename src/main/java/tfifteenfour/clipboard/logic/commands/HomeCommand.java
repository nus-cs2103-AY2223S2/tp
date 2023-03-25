package tfifteenfour.clipboard.logic.commands;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.model.Model;

/**
 * Navigates back to homepage and lists all courses in CLIpboard to the user.
 */
public class HomeCommand extends Command {
    public static final String COMMAND_WORD = "home";

    public static final String MESSAGE_SUCCESS = "Back to homepage, listed all courses";

    public HomeCommand() {
        super(false);
    }

    @Override
    public CommandResult execute(Model model, CurrentSelection currentSelection) {
        return new CommandResult(this, MESSAGE_SUCCESS, willModifyState);
    }
}

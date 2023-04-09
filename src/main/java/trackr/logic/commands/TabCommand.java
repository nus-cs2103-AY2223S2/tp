package trackr.logic.commands;

import static java.util.Objects.requireNonNull;
import static trackr.logic.parser.CliSyntax.PREFIX_TAB;

import trackr.commons.core.index.Index;
import trackr.logic.commands.exceptions.CommandException;
import trackr.model.Model;
import trackr.model.ObservableTabIndex;

/**
 * Switches to a tab specified by the user
 */
public class TabCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Switched tab.";
    public static final String COMMAND_WORD = "tab";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to specified tab. "
            + "Parameters: "
            + PREFIX_TAB + "TAB NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAB + "HOME";

    private final Index targetTab;

    /**
     * Creates a SwitchTabCommand to switch to specified tab {@code index}
     */
    public TabCommand(Index targetTab) {
        requireNonNull(targetTab);
        this.targetTab = targetTab;
    }

    /**
     * Switches to the specified tab.
     *
     * @param unused {@code Model} which is not used.
     * @return Success message of the tab operation for display.
     */
    @Override
    public CommandResult execute(Model unused) {
        ObservableTabIndex.updateToTab(targetTab);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}

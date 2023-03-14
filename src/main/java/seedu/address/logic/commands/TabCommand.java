package seedu.address.logic.commands;

import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Switches between UI tabs in the application.
 */
public class TabCommand extends Command {
    public static final String COMMAND_WORD = "tab";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Switches between UI tabs in the application "
            + "by the index of the tab.\n"
            + "Parameters: INDEX (must be 1 or 2)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Tab command not implemented yet";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";

    private final Index index;

    /**
     * @param index Index of the tab to switch to.
     */
    public TabCommand(Index index) {
        Objects.requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        throw new CommandException(String.format(MESSAGE_ARGUMENTS, index.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TabCommand)) {
            return false;
        }

        // state check
        TabCommand e = (TabCommand) other;
        return index.equals(e.index);
    }
}

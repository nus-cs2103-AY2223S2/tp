package seedu.address.logic.commands.sortcommand;

import seedu.address.logic.commands.Command;

/**
 * Abstract parent class for sort command
 */
public abstract class SortCommand extends Command {
    public abstract boolean getInorder();
}

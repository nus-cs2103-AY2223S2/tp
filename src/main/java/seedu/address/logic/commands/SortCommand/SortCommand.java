package seedu.address.logic.commands.SortCommand;

import seedu.address.logic.commands.Command;

public abstract class SortCommand extends Command {
    public abstract boolean getInorder();
}

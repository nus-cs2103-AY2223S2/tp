package seedu.address.logic.commands.parent;

import seedu.address.logic.commands.Command;

/**
 * An abstract class for "parent" command
 */
public abstract class ParentCommand extends Command {

    public static final String COMMAND_WORD = "parent";

    public static final String MESSAGE_USAGE = "1. parent add\n"
            + "2. parent delete\n";

    public static final String MESSAGE_SUCCESS = "New parent added: <PARENT_NAME>";
}

package seedu.address.logic.commands.person;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandGroup;

/**
 * Defines PersonCommand.
 */
abstract class PersonCommand extends Command {
    public static final CommandGroup COMMAND_GROUP = CommandGroup.PERSON;
}

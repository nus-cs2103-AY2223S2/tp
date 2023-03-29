package tfifteenfour.clipboard.logic.commands.findcommand;

import tfifteenfour.clipboard.logic.commands.Command;

/**
 * Adds a student to the address book.
 */
public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public FindCommand() {
        super(true);
    }

}

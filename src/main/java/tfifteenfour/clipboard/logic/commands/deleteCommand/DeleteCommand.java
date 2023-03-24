package tfifteenfour.clipboard.logic.commands.deleteCommand;

import tfifteenfour.clipboard.logic.commands.Command;

/**
 * Adds a student to the address book.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public DeleteCommand() {
        super(true);
    }

}

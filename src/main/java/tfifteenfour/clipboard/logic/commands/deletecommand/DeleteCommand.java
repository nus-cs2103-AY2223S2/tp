package tfifteenfour.clipboard.logic.commands.deletecommand;

import tfifteenfour.clipboard.logic.commands.Command;

/**
 * Abstract command class to delete an object from the roster.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public DeleteCommand() {
        super(true);
    }

}

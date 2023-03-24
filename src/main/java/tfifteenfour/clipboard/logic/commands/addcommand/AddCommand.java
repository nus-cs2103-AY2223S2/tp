package tfifteenfour.clipboard.logic.commands.addcommand;

import tfifteenfour.clipboard.logic.commands.Command;

/**
 * Adds a student to the address book.
 */
public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public AddCommand() {
        super(true);
    }

}

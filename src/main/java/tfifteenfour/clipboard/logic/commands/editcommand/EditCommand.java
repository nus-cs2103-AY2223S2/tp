package tfifteenfour.clipboard.logic.commands.editcommand;

import tfifteenfour.clipboard.logic.commands.Command;

public abstract class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public EditCommand() {
        super(true);
    }
}

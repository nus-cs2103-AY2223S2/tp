package tfifteenfour.clipboard.logic.commands.editcommand;

import tfifteenfour.clipboard.logic.commands.Command;

/**
 * Edits an existing item in the clipboard. Available options
 * are course, group, session and student.
 */
public abstract class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public EditCommand() {
        super(true);
    }
}

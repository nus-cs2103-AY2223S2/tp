package trackr.logic.commands.menu;

import trackr.commons.core.index.Index;
import trackr.logic.commands.DeleteItemCommand;
import trackr.model.ModelEnum;
import trackr.model.menu.MenuItem;

/**
 * Deletes a task identified using it's displayed index from the task list.
 */
public class DeleteMenuItemCommand extends DeleteItemCommand<MenuItem> {

    public static final String COMMAND_WORD = "delete_item";
    public static final String COMMAND_WORD_SHORTCUT = "delete_i";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the menu item identified by the index number used in the displayed menu.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public DeleteMenuItemCommand(Index targetIndex) {
        super(targetIndex, ModelEnum.MENUITEM);
    }
}

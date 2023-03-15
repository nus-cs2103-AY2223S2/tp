package vimification.logic.commands;

import vimification.logic.commands.Command;
import vimification.model.ViewList;

public abstract class ViewCommand extends Command {
    private ViewList list;
    public ViewCommand(ViewList list) {
        this.list = list;
    }

    // Don't think this method will ever be used
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && this.list.equals(((ViewCommand) other).list));
    }
}

package seedu.address.logic.commands;

import seedu.address.model.ViewList;

public abstract class ViewCommand extends Command {
    private ViewList list;
    public ViewCommand(ViewList list) {
        this.list = list;
    }
}

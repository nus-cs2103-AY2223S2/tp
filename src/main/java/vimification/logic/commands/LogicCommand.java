package vimification.logic.commands;

import vimification.logic.commands.exceptions.CommandException;
import vimification.model.Model;

public abstract class LogicCommand extends Command {
    protected static final String NOT_UNDOABLE_MESSAGE = "This command is not undoable.";
    protected static final String FINISHED_EXECUTION_MESSAGE =
            "This command has been executed. It cannot be executed again.";
    private boolean undoable;

    protected LogicCommand() {
        this.undoable = false;
    }

    protected void setUndoable(boolean bool) {
        this.undoable = bool;
    }

    // protected boolean isUndoable() {
    // return undoable;
    // }

    protected void checkBeforeExecuting() throws CommandException {
        if (undoable) {
            throw new CommandException(FINISHED_EXECUTION_MESSAGE);
        }
    }

    protected void checkBeforeUndoing() throws CommandException {
        if (!undoable) {
            throw new CommandException(NOT_UNDOABLE_MESSAGE);
        }
    }

    public abstract CommandResult execute(Model model) throws CommandException;

    public abstract CommandResult undo(Model model) throws CommandException;
}

package vimification.internal.command.logic;

import javafx.collections.ObservableList;
import vimification.internal.command.Command;
import vimification.model.task.Task;

public abstract class LogicCommand implements Command {
    protected static final String NOT_UNDOABLE_MESSAGE = "This command is not undoable.";
    protected static final String FINISHED_EXECUTION_MESSAGE =
            "This command has been executed. It cannot be executed again.";

    @Override
    public ObservableList<Task> getViewTaskList() {
        return null;
    }
}

package vimification.internal.command.view;

import javafx.collections.transformation.FilteredList;
import vimification.internal.command.Command;
import vimification.internal.command.CommandResult;
import vimification.model.task.Task;


public abstract class ViewCommand implements Command {
    public abstract CommandResult execute(FilteredList<Task> taskList);
}

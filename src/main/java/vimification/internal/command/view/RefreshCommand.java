package vimification.internal.command.view;

import javafx.collections.transformation.FilteredList;
import vimification.internal.command.CommandResult;
import vimification.model.task.Task;

public class RefreshCommand extends ViewCommand {

    public static final String SUCCESS_MESSAGE = "The view has been refreshed.";

    public RefreshCommand() {}

    @Override
    public CommandResult execute(FilteredList<Task> taskList) {
        taskList.setPredicate(null);
        return new CommandResult(SUCCESS_MESSAGE);
    }


}

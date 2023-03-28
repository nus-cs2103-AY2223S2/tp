package vimification.internal.command.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;
import vimification.model.task.Priority;
import vimification.model.task.Task;

public class SearchByPriorityCommand extends SearchCommand {
    public static final String COMMAND_WORD = "search by priority";

    public SearchByPriorityCommand(int level) {
        super(task -> task.checkPriority(level));
    }

    public SearchByPriorityCommand(Priority priority) {
        super(task -> task.checkPriority(priority));
    }

    @Override
    public CommandResult execute(LogicTaskList taskList) throws CommandException {
        ObservableList<Task> viewTaskList =
                FXCollections.observableList(taskList.filter(getPredicate()));
        setViewTaskList(viewTaskList);
        return new CommandResult(SUCCESS_MESSAGE_FORMAT);
    }
}

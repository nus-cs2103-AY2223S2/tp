package vimification.internal.command.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;
import vimification.model.task.Priority;
import vimification.model.task.Task;

public class SearchByPriorityCommand extends SearchCommand {

    public static final String COMMAND_WORD = "s -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": search for tasks that has the same priority as the input priority.\n"
            + "Parameters: PRIORITY (high, med or low)\n"
            + "Example: " + COMMAND_WORD + " high";

    public SearchByPriorityCommand(int level) {
        super(task -> task.isSamePriority(level));
    }

    public SearchByPriorityCommand(Priority priority) {
        super(task -> task.isSamePriority(priority));
    }

    @Override
    public CommandResult execute(LogicTaskList taskList) throws CommandException {
        ObservableList<Task> viewTaskList =
                FXCollections.observableList(taskList.filter(getPredicate()));
        setViewTaskList(viewTaskList);
        return new CommandResult(SUCCESS_MESSAGE_FORMAT);
    }
}

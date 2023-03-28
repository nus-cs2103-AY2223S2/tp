package vimification.internal.command.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

public class SearchByTag extends SearchCommand {
    public static final String COMMAND_WORD = "s -t";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": search for tasks with tags matching the input tag.\n"
            + "Parameters: TAG\n"
            + "Example: " + COMMAND_WORD + " meeting";

    public SearchByTag(String tag) {
        super(task -> true); //task.containsTag(tag));
    }

    @Override
    public CommandResult execute(LogicTaskList taskList) throws CommandException {
        ObservableList<Task> viewTaskList =
                FXCollections.observableList(taskList.filter(getPredicate()));
        setViewTaskList(viewTaskList);
        return new CommandResult(SUCCESS_MESSAGE_FORMAT);
    }
}

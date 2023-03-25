package vimification.logic.commands.viewCommands;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vimification.logic.commands.CommandException;
import vimification.logic.commands.CommandResult;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

public class SearchByKeywordCommand extends SearchCommand {

    public SearchByKeywordCommand(String keyword) {
        super(task -> task.containsKeyword(keyword));
    }

    @Override
    public CommandResult execute(LogicTaskList taskList) throws CommandException {
        ObservableList<Task> viewTaskList =
                FXCollections.observableList(taskList.filter(getPredicate()));
        setViewTaskList(viewTaskList);
        return new CommandResult(SUCCESS_MESSAGE_FORMAT);
    }
}

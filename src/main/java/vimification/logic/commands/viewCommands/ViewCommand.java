package vimification.logic.commands.viewCommands;

import javafx.collections.ObservableList;
import vimification.logic.commands.Command;
import vimification.model.task.Task;


public abstract class ViewCommand extends Command {
    private ObservableList<Task> viewTaskList;

    ViewCommand() {
        super(true);
        viewTaskList = null;
    }

    protected void setViewTaskList(ObservableList<Task> list) {
        this.viewTaskList = list;
    }

    public ObservableList<Task> getViewTaskList() {
        return viewTaskList;
    }
}

package vimification.internal.commands.view;

import javafx.collections.ObservableList;
import vimification.internal.commands.Command;
import vimification.model.task.Task;


public abstract class ViewCommand extends Command {
    private ObservableList<Task> viewTaskList;

    ViewCommand() {
        viewTaskList = null;
    }

    protected void setViewTaskList(ObservableList<Task> list) {
        this.viewTaskList = list;
    }

    @Override
    public ObservableList<Task> getViewTaskList() {
        return viewTaskList;
    }
}

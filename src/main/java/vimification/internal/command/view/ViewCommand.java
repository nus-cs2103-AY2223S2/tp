package vimification.internal.command.view;

import javafx.collections.ObservableList;
import vimification.internal.command.Command;
import vimification.model.task.Task;


public abstract class ViewCommand implements Command {
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

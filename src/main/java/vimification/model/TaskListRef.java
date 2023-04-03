package vimification.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import vimification.model.task.Task;

public class TaskListRef {

    private List<Task> taskList;
    private boolean readOnly = false;

    public TaskListRef(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskListRef() {
        this.taskList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        Objects.requireNonNull(taskList);
        this.taskList = taskList;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}

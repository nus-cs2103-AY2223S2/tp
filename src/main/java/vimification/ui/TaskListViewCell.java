package vimification.ui;

import javafx.scene.control.ListCell;
import vimification.model.task.Task;

/**
 * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
 */
class TaskListViewCell extends ListCell<Task> {
    @Override
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);
        if (empty || task == null) {
            setGraphic(null);
            setText(null);
            return;
        }
        setGraphic(new TaskCard(task, getIndex() + 1).getRoot());
    }
}

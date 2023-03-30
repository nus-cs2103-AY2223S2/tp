package tfifteenfour.clipboard.ui.taskpage;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.model.task.Task;
import tfifteenfour.clipboard.ui.UiPart;
import tfifteenfour.clipboard.ui.sessionpage.SessionListPanel;

/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SessionListPanel.class);

    @FXML
    private ListView<Task> listView;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public TaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        listView.setItems(taskList);
        listView.setCellFactory(taskView -> new TaskListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskListCard}.
     */
    class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (task.getSelectionStatus()) {
                    setGraphic(new SelectedTaskListCard(task, getIndex() + 1).getRoot());
                } else {
                    setGraphic(new TaskListCard(task, getIndex() + 1).getRoot());
                }
            }
        }
    }

    public void setTaskListView(ObservableList<Task> taskList) {
        listView.setItems(taskList);
    }

}

package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "TaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @FXML
    private ListView<Task> taskListView;
    @FXML
    private Label name;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public TaskListPanel(Person person) {
        super(FXML);

        name.setText("No student being checked now");

        if (person != null) {
            taskListView.setItems(person.getTaskList().getInternalList());
        }
        taskListView.setCellFactory(listView -> new TaskListViewCell());

        if (person != null) {
            if (person.getTaskList().getInternalList().size() != 0) {
                name.setText("Tasks for " + person.getName().fullName);
            } else {
                name.setText("No task found for " + person.getName().fullName);
            }
        }
    }

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
            } else {
                setGraphic(new TaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }
}

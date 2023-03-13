package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "TaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @FXML
    private ListView<Person> taskListView;
    @FXML
    private Label name;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public TaskListPanel(ObservableList<Person> taskList) {
        super(FXML);
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
        name.setText(taskList.get(0).getName().fullName);
        SimpleStringProperty val = new SimpleStringProperty();
        val.setValue(taskList.get(0).getName().fullName);
        val.addListener((obs, oldVal, newVal) ->
                name.setText(taskList.get(0).getName().fullName));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class TaskListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person task, boolean empty) {
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

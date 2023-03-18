package vimification.taskui;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import vimification.model.task.Task;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart<VBox> {
    private static final String FXML = "TaskListPanel.fxml";

    private MainScreen mainScreen;

    @FXML
    private ListView<Task> taskListView;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public TaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
    }

    public void setMainScreen(MainScreen parent) {
        this.mainScreen = parent;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a
     * {@code TaskCard}.
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

    @FXML
    private void initialize() {
        // TODO: Implement Visual Mode
        // taskListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        // System.out.println("You are navigating in TaskListPanel");

        switch (event.getText()) {
        case "d":
            System.out.println("You've deleted a task!");
            break;
        case "l":
            Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
            mainScreen.loadDetailedTaskComponent(selectedTask);
            break;

        case "j":
            System.out.println("You've moved down");
            navigateToNextCell();
            break;
        case "k":
            System.out.println("You've moved up");
            navigateToPrevCell();
            break;
        }
    }

    private void navigateToNextCell() {
        int currIndex = taskListView.getFocusModel().getFocusedIndex();
        int lastIndex = taskListView.getItems().size();

        // We can only navigate to the next cell if we are not the last cell.
        boolean isCurrLastCell = currIndex == lastIndex;
        if (!isCurrLastCell) {
            taskListView.getFocusModel().focusNext();
            taskListView.getSelectionModel().select(currIndex + 1);
            taskListView.scrollTo(currIndex + 1);
        }
    }

    private void navigateToPrevCell() {
        int currIndex = taskListView.getFocusModel().getFocusedIndex();

        // We can only navigate to the previous cell if we are not the first cell.
        boolean isCurrFirstCell = currIndex == 0;
        if (!isCurrFirstCell) {
            taskListView.getFocusModel().focusPrevious();
            taskListView.getSelectionModel().select(currIndex - 1);
            taskListView.scrollTo(currIndex - 1);
        }
    }
}

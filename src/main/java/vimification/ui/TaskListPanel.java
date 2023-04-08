package vimification.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.function.Predicate;

import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import vimification.model.UiTaskList;
import vimification.model.task.Task;



/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart<VBox> {
    private static final String FXML = "TaskListPanel.fxml";

    private UiTaskList taskList;

    public UiTaskList getTaskList() {
        return taskList;
    }

    private MainScreen mainScreen;

    @FXML
    private ListView<Task> taskListView;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public TaskListPanel(UiTaskList taskList) {
        super(FXML);
        this.taskList = taskList;
        taskListView.setItems(taskList.getUiSource());
        taskListView.setCellFactory(listView -> new TaskListViewCell());
    }

    public void setMainScreen(MainScreen parent) {
        this.mainScreen = parent;
    }

    public void requestFocus() {
        taskListView.requestFocus();
        System.out.println(taskListView.getSelectionModel().getSelectedItem());
        if (taskListView.getSelectionModel().getSelectedItem() == null) {
            scrollToTaskIndex(1);
        }
    }

    /**
     * Scroll to the kth index on the TaskListPanel.
     *
     * @param displayedIndex
     */
    public void scrollToTaskIndex(int displayedIndex) {
        taskListView.getFocusModel().focus(displayedIndex - 1);
        taskListView.getSelectionModel().select(displayedIndex - 1);
        taskListView.scrollTo(displayedIndex - 1);
    }

    @FXML
    private void initialize() {
        // TODO: Implement Visual Mode
        // taskListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.getRoot().setFocusTraversable(true);
        taskListView.setFocusTraversable(true);
        Platform.runLater(() -> {
            // Hackish way of requesting focus
            // after every listItem has been
            // loaded
            taskListView.requestFocus();
            taskListView.getSelectionModel().selectFirst();
        });
    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        switch (event.getText()) {
        case "h":
            mainScreen.clearRightComponent();
            break;
        case "l":
            Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
            TaskDetailPanel detailTask = new TaskDetailPanel(selectedTask);
            mainScreen.loadRightComponent(detailTask);
            break;
        case "j":
            System.out.println("You've moved down");
            navigateToNextCell();
            break;
        case "k":
            System.out.println("You've moved up");
            navigateToPrevCell();
            break;
        default:
            System.out.println("You've pressed: " + event.getText());
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

    public void searchForTask(Predicate<? super Task> predicate) {
        taskList.setPredicate(predicate);
    }

    public void refreshTaskDetailPanel() {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();

        if (selectedTask == null) {
            mainScreen.clearRightComponent();
            return;
        }

        TaskDetailPanel taskDetailPanel = new TaskDetailPanel(selectedTask);
        mainScreen.loadRightComponent(taskDetailPanel);
    }
}

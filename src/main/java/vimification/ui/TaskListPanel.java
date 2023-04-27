package vimification.ui;

import java.util.function.Predicate;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
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

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList} and {@code MainScreen}.
     */
    public TaskListPanel(UiTaskList taskList, MainScreen mainScreen) {
        super(FXML);
        this.taskList = taskList;
        this.mainScreen = mainScreen;
        taskListView.setItems(taskList.getUiSource());
        taskListView.setCellFactory(listView -> new TaskListViewCell());
    }

    public void setMainScreen(MainScreen parent) {
        this.mainScreen = parent;
    }

    /**
     * Requests focus on the TaskListPanel.
     */
    public void requestFocus() {
        taskListView.requestFocus();
        boolean isNothingSelected = taskListView.getSelectionModel().isEmpty();
        if (isNothingSelected) {
            taskListView.getSelectionModel().selectFirst();
            return;
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

    /**
     * Initializes the TaskListPanel.
     */
    @FXML
    private void initialize() {
        getRoot().setFocusTraversable(true);
        taskListView.setFocusTraversable(true);
        Platform.runLater(() -> {
            // Hackish way of requesting focus
            // after every listItem has been
            // loaded
            taskListView.requestFocus();
            taskListView.getSelectionModel().selectFirst();
        });

        // taskListView.getItems().addListener();
    }

    /**
     * Listener for handling all keyboard events to TaskListPanel.
     *
     * @param event
     */
    @FXML
    private void handleKeyPressed(KeyEvent event) {
        switch (event.getText()) {
        case "h":
            mainScreen.clearRightComponent();
            break;
        case "l":
            loadTaskDetailPanel();
            break;
        case "j":
            navigateToNextCell();
            break;
        case "k":
            navigateToPrevCell();
            break;
        default:
            break;
        }
    }

    /**
     * Navigate to the next cell in the ListView.
     */
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

    /**
     * Navigate to the previous cell in the ListView.
     */
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

    /**
     * Loads the TaskDetailPanel.
     */
    public void loadTaskDetailPanel() {
        requestFocus();
        boolean isTaskListEmpty = taskListView.getItems().isEmpty();
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        boolean isNothingSelected = selectedTask == null;
        if (isTaskListEmpty) {
            mainScreen.clearRightComponent();
            return;
        }
        if (isNothingSelected) {
            return;
        }

        TaskDetailPanel taskDetailPanel = new TaskDetailPanel(selectedTask);
        mainScreen.loadRightComponent(taskDetailPanel);
    }

    public UiTaskList getUiTaskList() {
        return taskList;
    }
}

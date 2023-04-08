package vimification.ui;

import java.util.function.Predicate;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import vimification.internal.Logic;
import vimification.model.UiTaskList;
import vimification.model.task.Status;
import vimification.model.task.Task;

public class TaskTabPanel extends UiPart<VBox> {
    private static final String FXML = "TaskTabPanel.fxml";

    @FXML
    private TabPane taskTabPane;

    @FXML
    private VBox ongoingTaskListComponent;
    @FXML
    private VBox completedTaskListComponent;
    @FXML
    private VBox overdueTaskListComponent;

    private MainScreen mainScreen;
    private UiTaskList uiTaskList;

    private TaskListPanel ongoingTaskListPanel;
    private TaskListPanel completedTaskListPanel;
    private TaskListPanel overdueTaskListPanel;

    int taskTabIndex = 0;

    public TaskTabPanel(MainScreen mainScreen, Logic logic) {
        super(FXML);
        this.mainScreen = mainScreen;
        this.uiTaskList = logic.getUiTaskList();

        uiTaskList.setPredicate(task -> task.hasStatus(Status.NOT_DONE));

        ongoingTaskListPanel = new TaskListPanel(uiTaskList);
        completedTaskListPanel = new TaskListPanel(uiTaskList);
        overdueTaskListPanel = new TaskListPanel(uiTaskList);

        ongoingTaskListPanel.setMainScreen(mainScreen);
        completedTaskListPanel.setMainScreen(mainScreen);
        overdueTaskListPanel.setMainScreen(mainScreen);

        ongoingTaskListPanel.getRoot().prefHeightProperty()
                .bind(this.getRoot().prefHeightProperty());
        completedTaskListPanel.getRoot().prefHeightProperty()
                .bind(this.getRoot().prefHeightProperty());
        overdueTaskListPanel.getRoot().prefHeightProperty()
                .bind(this.getRoot().prefHeightProperty());

        ongoingTaskListComponent.getChildren().add(ongoingTaskListPanel.getRoot());
        completedTaskListComponent.getChildren().add(completedTaskListPanel.getRoot());
        overdueTaskListComponent.getChildren().add(overdueTaskListPanel.getRoot());
    }


    @Override
    public void requestFocus() {
        int selectedTabIndex = taskTabPane.getSelectionModel().getSelectedIndex();
        switch (selectedTabIndex) {
        case 0:
            ongoingTaskListPanel.requestFocus();
            break;
        case 1:
            completedTaskListPanel.requestFocus();
            break;
        case 2:
            overdueTaskListPanel.requestFocus();
            break;
        }
    }

    public void scrollToTaskIndex(int displayIndex) {
        int selectedTabIndex = taskTabPane.getSelectionModel().getSelectedIndex();

        switch (selectedTabIndex) {
        case 0:
            ongoingTaskListPanel.scrollToTaskIndex(displayIndex);
            break;
        case 1:
            completedTaskListPanel.scrollToTaskIndex(displayIndex);
            break;
        case 2:
            overdueTaskListPanel.scrollToTaskIndex(displayIndex);
            break;
        }
    }

    public void searchForTask(Predicate<? super Task> predicate, Status status) {
        int selectedTabIndex = taskTabPane.getSelectionModel().getSelectedIndex();

        switch (selectedTabIndex) {
        case 0:
            ongoingTaskListPanel.searchForTask(predicate);
            break;
        case 1:
            completedTaskListPanel.searchForTask(predicate);
            break;
        case 2:
            overdueTaskListPanel.searchForTask(predicate);
            break;
        }
    }

    public UiTaskList getUiTaskList() {
        return uiTaskList;
    }

    public TabPane getTaskTabPane() {
        return taskTabPane;
    }

    public MainScreen getMainScreen() {
        return mainScreen;
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            taskTabPane.requestFocus(); // Hackish way of requesting focus after everything has been
            // loaded.
        });
        initializeHandleTabChange();
    }


    private void initializeHandleTabChange() {
        taskTabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            int selectedTabIndex = taskTabPane.getSelectionModel().getSelectedIndex();
            switch (selectedTabIndex) {
            case 0: {
                uiTaskList.setPredicate(task -> task.hasStatus(Status.NOT_DONE));
                break;
            }
            case 1: {
                uiTaskList.setPredicate(task -> task.hasStatus(Status.COMPLETED));
                break;
            }
            case 2: {
                uiTaskList.setPredicate(task -> task.hasStatus(Status.OVERDUE));
                break;
            }
            }
        });
    }

    public void requestTabFocus() {
        taskTabPane.requestFocus();
    }

    public void refreshTaskDetailPanel() {
        int selectedTabIndex = taskTabPane.getSelectionModel().getSelectedIndex();

        switch (selectedTabIndex) {
        case 0:
            ongoingTaskListPanel.refreshTaskDetailPanel();
            break;
        case 1:
            completedTaskListPanel.refreshTaskDetailPanel();
            break;
        case 2:
            overdueTaskListPanel.refreshTaskDetailPanel();
            break;
        }
    }
}

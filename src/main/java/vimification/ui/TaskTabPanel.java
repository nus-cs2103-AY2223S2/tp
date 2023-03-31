package vimification.ui;

import java.util.function.Predicate;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import vimification.internal.Logic;
import vimification.model.task.Task;

public class TaskTabPanel extends UiPart<VBox> {
    private static final String FXML = "TaskTabPanel.fxml";

    @FXML
    private TabPane taskTabPane;
    @FXML
    private VBox ongoingTaskListComponent;
    @FXML
    private VBox completedTaskListComponent;

    private TaskListPanel ongoingTaskListPanel;
    private TaskListPanel completedTaskListPanel;

    MainScreen mainScreen;
    Logic logic;

    public TaskTabPanel(MainScreen mainScreen, Logic logic) {
        super(FXML);
        this.mainScreen = mainScreen;
        this.logic = logic;
        loadTaskListPanel();
    }

    // TODO: Refactor this.
    protected void loadTaskListPanel() {
        ongoingTaskListPanel = new TaskListPanel(logic.getUiTaskList());
        ongoingTaskListPanel.setMainScreen(mainScreen);

        completedTaskListPanel = new TaskListPanel(logic.getUiTaskList());
        completedTaskListPanel.setMainScreen(mainScreen);

        ongoingTaskListComponent.getChildren().clear();
        ongoingTaskListComponent.getChildren().add(ongoingTaskListPanel.getRoot());

        taskTabPane.prefHeightProperty().bind(this.getRoot().prefHeightProperty());
        ongoingTaskListPanel.getRoot().prefHeightProperty()
                .bind(this.getRoot().prefHeightProperty());
        completedTaskListPanel.getRoot().prefHeightProperty()
                .bind(this.getRoot().prefHeightProperty());

        completedTaskListComponent.getChildren().clear();
        completedTaskListComponent.getChildren().add(completedTaskListPanel.getRoot());

    }

    @Override
    public void requestFocus() {
        if (checkIsOngoingTabSelected()) {
            ongoingTaskListPanel.requestFocus();
        } else {
            completedTaskListPanel.requestFocus();
        }
    }

    public void scrollToTaskIndex(int displayIndex) {
        if (checkIsOngoingTabSelected()) {
            ongoingTaskListPanel.scrollToTaskIndex(displayIndex);
        } else {
            completedTaskListPanel.scrollToTaskIndex(displayIndex);
        }
    }

    private boolean checkIsOngoingTabSelected() {
        int selectedTabIndex = taskTabPane.getSelectionModel().getSelectedIndex();
        boolean isOngoingTabSelected = selectedTabIndex == 0;
        return isOngoingTabSelected;
    }

    public void searchForTask(Predicate<? super Task> predicate, int targetTabIndex) {
        ongoingTaskListPanel.searchForTask(predicate);
        completedTaskListPanel.searchForTask(predicate);
        taskTabPane.getSelectionModel().clearSelection(targetTabIndex);
    }
}

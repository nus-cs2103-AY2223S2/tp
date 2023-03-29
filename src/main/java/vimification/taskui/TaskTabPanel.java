package vimification.taskui;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.VBox;
import vimification.internal.Logic;

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
        taskTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }

    // TODO: Refactor this.
    protected void loadTaskListPanel() {
        ongoingTaskListPanel = new TaskListPanel(logic.getViewTaskList());
        ongoingTaskListPanel.setMainScreen(mainScreen);

        completedTaskListPanel = new TaskListPanel(logic.getViewTaskList());
        completedTaskListPanel.setMainScreen(mainScreen);

        ongoingTaskListComponent.getChildren().clear();
        ongoingTaskListComponent.getChildren().add(ongoingTaskListPanel.getRoot());

        completedTaskListComponent.getChildren().clear();
        completedTaskListComponent.getChildren().add(completedTaskListPanel.getRoot());
    }

    @Override
    public void requestFocus() {
        int selectedTabIndex = taskTabPane.getSelectionModel().getSelectedIndex();

        boolean isOngoingTabSelected = selectedTabIndex == 0;
        if (isOngoingTabSelected) {
            ongoingTaskListPanel.requestFocus();
        } else {
            completedTaskListPanel.requestFocus();
        }
    }
}

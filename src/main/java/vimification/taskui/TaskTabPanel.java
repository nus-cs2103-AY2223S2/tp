package vimification.taskui;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import vimification.internal.Logic;

public class TaskTabPanel extends UiPart<VBox> {
    private static final String FXML = "Tab.fxml";

    @FXML
    private Tab ongoingTab;
    @FXML
    private Tab completedTab;
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
        ongoingTaskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        ongoingTaskListPanel.setMainScreen(mainScreen);

        completedTaskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        completedTaskListPanel.setMainScreen(mainScreen);

        // this.ongoingTab.setContent(ongoingTaskList.getRoot());
        // this.completedTab.setContent(compeltedTaskList.getRoot());
        ongoingTaskListComponent.getChildren().clear();
        ongoingTaskListComponent.getChildren().add(ongoingTaskListPanel.getRoot());

        completedTaskListComponent.getChildren().clear();
        completedTaskListComponent.getChildren().add(completedTaskListPanel.getRoot());
    }

    @Override
    public void requestFocus() {
        super.requestFocus();
        ongoingTaskListPanel.requestFocus();
    }

}

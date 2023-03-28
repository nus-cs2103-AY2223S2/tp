package trackr.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import trackr.logic.Logic;

/**
 * The UI component that is resposnible for displaying Home tab.
 */
public class HomeView extends UiPart<Region> {
    private static final String FXML = "HomeView.fxml";
    private Logic logic;
    private TaskListPanel taskListPanel;
    private TaskListPanel sortedTaskListPanel;

    @FXML
    private StackPane homeList;

    /**
     * Creates an empty TabPane
     */
    public HomeView(Logic logic) {
        super(FXML);
        this.logic = logic;
        fillParts();
    }

    private void fillParts() {
        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        homeList.getChildren().add(taskListPanel.getRoot());
    }

    /**
     * Displays sorted task list.
     */
    public void showSortedTasks() {
        sortedTaskListPanel = new TaskListPanel(logic.getSortedTaskList());
        homeList.getChildren().add(sortedTaskListPanel.getRoot());
    }
}

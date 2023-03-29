package trackr.ui.dashboard;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import trackr.logic.Logic;
import trackr.ui.UiPart;
import trackr.ui.listpanels.SummarisedOrderListPanel;
import trackr.ui.listpanels.TaskListPanel;

/**
 * The UI component that is resposnible for displaying Home tab.
 */
public class HomeView extends UiPart<Region> {
    private static final String FXML = "HomeDashboard.fxml";
    private Logic logic;
    private TaskListPanel taskListPanel;
    private SummarisedOrderListPanel summarisedOrderListPanel;


    @FXML
    private GridPane homeWindow;

    @FXML
    private StackPane tasksPlaceholder;

    @FXML
    private StackPane ordersPlaceholder;


    /**
     * Creates the landing page
     */
    public HomeView(Logic logic) {
        super(FXML);
        this.logic = logic;
        fillParts();
    }

    /**
     * The Landing page consists of a 4x4 GridPane with elements that span
     * different rows
     */
    public void fillParts() {
        homeWindow = new GridPane();
        // Title text
        homeWindow.add(new Text("Sales"), 0, 0);
        homeWindow.add(new Text("At a glance..."), 0, 1, 2, 1);


        // Add tasks
        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        tasksPlaceholder.getChildren().add(taskListPanel.getRoot());

        // Add summarised orders
        summarisedOrderListPanel = new SummarisedOrderListPanel(logic.getFilteredOrderList());
        ordersPlaceholder.getChildren().add(summarisedOrderListPanel.getRoot());
    }

    private HBox createDashboard() {
        HBox dashboard = new HBox(10);
        dashboard.setPrefHeight(15.0);
        Text dummy = new Text("Profit");
        Text dummy2 = new Text("Revenue");
        HBox.setHgrow(dummy, Priority.ALWAYS);
        HBox.setHgrow(dummy2, Priority.ALWAYS);
        dashboard.getChildren().addAll(dummy, dummy2);
        return dashboard;
    }
}

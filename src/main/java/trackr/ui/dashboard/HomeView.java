package trackr.ui.dashboard;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import trackr.logic.Logic;
import trackr.model.order.Order;
import trackr.ui.UiPart;
import trackr.ui.listpanels.MenuListPanel;
import trackr.ui.listpanels.SummarisedOrderListPanel;
import trackr.ui.listpanels.TaskListPanel;

//@@author arkarsg
/**
 * The UI component that is resposnible for displaying Home tab.
 */
public class HomeView extends UiPart<Region> {
    private static final String FXML = "HomeDashboard.fxml";
    private Logic logic;
    private TaskListPanel taskListPanel;
    private SummarisedOrderListPanel summarisedOrderListPanel;
    private MenuListPanel menuListPanel;

    @FXML
    private Text profitPlaceholder;

    @FXML
    private Text revenuePlaceholder;

    @FXML
    private GridPane homeWindow;

    @FXML
    private StackPane tasksPlaceholder;

    @FXML
    private StackPane ordersPlaceholder;

    @FXML
    private StackPane menuPlaceholder;

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
        // Add tasks
        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        tasksPlaceholder.getChildren().add(taskListPanel.getRoot());

        // Add summarised orders
        summarisedOrderListPanel = new SummarisedOrderListPanel(logic.getFilteredOrderList());
        ordersPlaceholder.getChildren().add(summarisedOrderListPanel.getRoot());

        // Add menu items
        menuListPanel = new MenuListPanel(logic.getFilteredMenu());
        menuPlaceholder.getChildren().add(menuListPanel.getRoot());

        // Initial Profit
        profitPlaceholder.setText(logic.getTotalProfits().toString());
        // Initial Revenue
        revenuePlaceholder.setText(logic.getTotalSales().toString());

        // event change listener to update revenue and profit
        logic.getFilteredOrderList().addListener(new ListChangeListener<Order>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Order> change) {
                profitPlaceholder.setText(logic.getTotalProfits().toString());
                revenuePlaceholder.setText(logic.getTotalSales().toString());
            }
        });
    }
}

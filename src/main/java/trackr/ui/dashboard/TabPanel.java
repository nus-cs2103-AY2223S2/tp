package trackr.ui.dashboard;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import trackr.commons.core.index.Index;
import trackr.logic.Logic;
import trackr.ui.UiPart;
import trackr.ui.listpanels.MenuListPanel;
import trackr.ui.listpanels.OrderListPanel;
import trackr.ui.listpanels.SupplierListPanel;
import trackr.ui.listpanels.TaskListPanel;

/**
 * The UI component that is responsible for displaying Tab views.
 */
public class TabPanel extends UiPart<Region> {
    private static final String FXML = "TabPanel.fxml";
    private static TabPane tabPanel;

    private Logic logic;

    private HomeView homeView;
    private SupplierListPanel contactListPanel;
    private TaskListPanel taskListPanel;
    private OrderListPanel orderListPanel;
    private MenuListPanel menuListPanel;

    @FXML
    private Tab homeTab;

    @FXML
    private Tab orderTab;

    @FXML
    private Tab taskTab;

    @FXML
    private Tab contactTab;

    @FXML
    private Tab menuTab;

    @FXML
    private StackPane homePanelPlaceholder;

    @FXML
    private StackPane taskListPanelPlaceholder;

    @FXML
    private StackPane orderListPanelPlaceholder;

    @FXML
    private StackPane contactListPanelPlaceholder;

    @FXML
    private StackPane menuListPanelPlaceholder;

    /**
     * Creates an empty TabPane
     */
    public TabPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
        tabPanel = new TabPane();
        fillTabs();
    }

    /**
     * Adds all tab in order.
     */
    private void fillTabs() {
        homeView = new HomeView(logic);
        homePanelPlaceholder.getChildren().add(homeView.getRoot());
        addNewTab(homeTab, homePanelPlaceholder, "Home");

        orderListPanel = new OrderListPanel(logic.getFilteredOrderList());
        orderListPanelPlaceholder
                .getChildren()
                .add(orderListPanel.getRoot());
        addNewTab(orderTab, orderListPanelPlaceholder, "Orders");

        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        taskListPanelPlaceholder
                .getChildren()
                .add(taskListPanel.getRoot());
        addNewTab(taskTab, taskListPanelPlaceholder, "Tasks");

        contactListPanel = new SupplierListPanel(logic.getFilteredSupplierList());
        contactListPanelPlaceholder
                .getChildren()
                .add(contactListPanel.getRoot());
        addNewTab(contactTab, contactListPanelPlaceholder, "Contacts");

        // Create placeholder with text for menu
        menuListPanel = new MenuListPanel(logic.getFilteredMenu());
        menuListPanelPlaceholder
                .getChildren()
                .add(menuListPanel.getRoot());
        addNewTab(menuTab, menuListPanelPlaceholder, "Menu");
    }

    /**
     * Adds a {@code tab} with the given {@code tabContent} and {@code tabName}
     */
    private void addNewTab(Tab tab, StackPane tabContent, String tabName) {
        tab.setClosable(false);
        tab.setText(tabName);
        tab.setContent(tabContent);
        tabPanel.getTabs().add(tab);
    }

    public static void switchToTab(Index index) {
        tabPanel.getSelectionModel().select(index.getZeroBased());
    }

    public SupplierListPanel getContactListPanel() {
        return contactListPanel;
    }

    public TaskListPanel getTaskListPanel() {
        return taskListPanel;
    }

    public OrderListPanel getOrderListPanel() {
        return orderListPanel;
    }
}

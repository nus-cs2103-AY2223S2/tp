package trackr.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Region;
import trackr.commons.core.index.Index;
import trackr.logic.Logic;

/**
 * The UI component that is responsible for displaying Tab views.
 */
public class TabPanel extends UiPart<Region> {
    private static final String FXML = "TabPanel.fxml";
    private static TabPane tabPanel;

    private Logic logic;

    private SupplierListPanel supplierListPanel;
    private TaskListPanel taskListPanel;
    private OrderListPanel orderListPanel;

    @FXML
    private Tab supplierTab;

    @FXML
    private Tab orderTab;

    @FXML
    private Tab taskTab;

    @FXML
    private StackPane supplierListPanelPlaceholder;

    @FXML
    private StackPane taskListPanelPlaceholder;

    @FXML
    private StackPane orderListPanelPlaceholder;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

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
        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        taskListPanelPlaceholder
                .getChildren()
                .add(taskListPanel.getRoot());
        addNewTab(taskTab, taskListPanelPlaceholder, "Home");

        supplierListPanel = new SupplierListPanel(logic.getFilteredSupplierList());
        supplierListPanelPlaceholder
                .getChildren()
                .add(supplierListPanel.getRoot());
        addNewTab(supplierTab, supplierListPanelPlaceholder, "Suppliers");

        orderListPanel = new OrderListPanel(logic.getFilteredOrderList());
        orderListPanelPlaceholder
                .getChildren()
                .add(orderListPanel.getRoot());
        addNewTab(orderTab, orderListPanelPlaceholder, "Orders");
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

    public SupplierListPanel getSupplierListPanel() {
        return supplierListPanel;
    }

    public TaskListPanel getTaskListPanel() {
        return taskListPanel;
    }

    public OrderListPanel getOrderListPanel() {
        return orderListPanel;
    }
}

package trackr.ui.dashboard;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import trackr.logic.Logic;
import trackr.model.ObservableTabIndex;
import trackr.ui.UiPart;
import trackr.ui.listpanels.MenuListPanel;
import trackr.ui.listpanels.OrderListPanel;
import trackr.ui.listpanels.SupplierListPanel;
import trackr.ui.listpanels.TaskListPanel;

//@@author arkarsg
/**
 * The UI component that is responsible for displaying Tab views.
 */
public class TabPanel extends UiPart<Region> {
    private static final String FXML = "TabPanel.fxml";

    private Logic logic;

    private HomeView homeView;
    private SupplierListPanel contactListPanel;
    private TaskListPanel taskListPanel;
    private OrderListPanel orderListPanel;
    private MenuListPanel menuListPanel;

    @FXML
    private TabPane tabPanel;

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
        fillTabs();
        ObservableTabIndex.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> o, Number oldIdx, Number newIdx) {
                switchToTab(newIdx.intValue());
            }
        });
    }

    /**
     * Adds all tab in order.
     */
    private void fillTabs() {
        homeView = new HomeView(logic);
        homePanelPlaceholder.getChildren().add(homeView.getRoot());
        fillNewTab(homeTab, homePanelPlaceholder, "Home");

        orderListPanel = new OrderListPanel(logic.getFilteredOrderList());
        orderListPanelPlaceholder
                .getChildren()
                .add(orderListPanel.getRoot());
        fillNewTab(orderTab, orderListPanelPlaceholder, "Orders");

        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        taskListPanelPlaceholder
                .getChildren()
                .add(taskListPanel.getRoot());
        fillNewTab(taskTab, taskListPanelPlaceholder, "Tasks");

        contactListPanel = new SupplierListPanel(logic.getFilteredSupplierList());
        contactListPanelPlaceholder
                .getChildren()
                .add(contactListPanel.getRoot());
        fillNewTab(contactTab, contactListPanelPlaceholder, "Contacts");

        // Create placeholder with text for menu
        menuListPanel = new MenuListPanel(logic.getFilteredMenu());
        menuListPanelPlaceholder
                .getChildren()
                .add(menuListPanel.getRoot());
        fillNewTab(menuTab, menuListPanelPlaceholder, "Menu");
    }

    /**
     * Adds a {@code tab} with the given {@code tabContent} and {@code tabName}
     */
    private void fillNewTab(Tab tab, StackPane tabContent, String tabName) {
        tab.setText(tabName);
        tab.setContent(tabContent);
    }

    public void switchToTab(int index) {
        tabPanel.getSelectionModel().select(index);
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

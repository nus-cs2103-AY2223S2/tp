package seedu.loyaltylift.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.loyaltylift.commons.core.GuiSettings;
import seedu.loyaltylift.commons.core.LogsCenter;
import seedu.loyaltylift.logic.Logic;
import seedu.loyaltylift.logic.commands.CommandResult;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.ui.customer.CustomerInfo;
import seedu.loyaltylift.ui.order.OrderInfo;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private CustomerListPanel customerListPanel;
    private OrderListPanel orderListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private CustomerInfo customerInfo;
    private OrderInfo orderInfo;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private TabPane tableTabPane;

    @FXML
    private Tab customerTab;

    @FXML
    private Tab orderTab;

    @FXML
    private StackPane customerListPanelPlaceholder;

    @FXML
    private StackPane orderListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private VBox userCommandBox;

    @FXML
    private StackPane infoPane;

    @FXML
    private Label hintLabel;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();

        /* A bugfix to resolve blurry contents in a ScrollPane */
        infoPane.getChildren().addListener((InvalidationListener) e ->
                resetScrollPaneCacheProperty()
        );
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        customerListPanel = new CustomerListPanel(logic.getFilteredCustomerList(), this::showCustomerInfo);
        customerListPanelPlaceholder.getChildren().add(customerListPanel.getRoot());

        orderListPanel = new OrderListPanel(logic.getFilteredOrderList(), this::showOrderInfo);
        orderListPanelPlaceholder.getChildren().add(orderListPanel.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        userCommandBox.getChildren().add(commandBox.getRoot());

        resultDisplay = new ResultDisplay();
        userCommandBox.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * This recursive method aims to resolve the blurry contents in a ScrollPane. `ScrollPaneSkin` sets
     * cache to true manually, ignoring the property set in the FXML file.
     * Hence, this method aggressively attempts to reset the property.
     */
    private void resetScrollPaneCacheProperty() {
        Platform.runLater(() -> {
            StackPane stackPane = (StackPane) infoPane.lookup("ScrollPane .viewport");
            if (stackPane == null) {
                resetScrollPaneCacheProperty();
                return;
            }
            stackPane.setCache(false);
        });
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.loyaltylift.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isShowCustomerInfo() && commandResult.isShowOrderInfo()) {
                throw new CommandException("Cannot display both customer and order information at once!");
            }

            if (commandResult.isShowCustomerList() && commandResult.isShowOrderList()) {
                throw new CommandException("Cannot display both customer and order list  at once!");
            }

            if (commandResult.isClearInfoView()) {
                clearInformationPanel();
            }

            if (commandResult.isShowCustomerList()) {
                tableTabPane.getSelectionModel().select(customerTab);
            }

            if (commandResult.isShowOrderList()) {
                tableTabPane.getSelectionModel().select(orderTab);
            }

            if (commandResult.isShowCustomerInfo()) {
                Customer customerToDisplay = logic.getCustomerToDisplay();
                showCustomerInfo(customerToDisplay);
                customerListPanel.getSelectionModel().select(customerToDisplay);
            }

            if (commandResult.isShowOrderInfo()) {
                Order orderToDisplay = logic.getOrderToDisplay();
                showOrderInfo(orderToDisplay);
                orderListPanel.getSelectionModel().select(orderToDisplay);
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    /**
     * Handles the event where a CustomerCard is clicked and the customer info needs to be shown.
     * @param customer The customer to be displayed on the information pane.
     */
    private void showCustomerInfo(Customer customer) {
        // ensure order table cell is selectable again
        orderListPanel.getSelectionModel().clearSelection();
        clearInformationPanel();

        logic.updateFilteredCustomerOrderList(customer);
        customerInfo = new CustomerInfo(customer, logic.getFilteredCustomerOrderList());
        infoPane.getChildren().add(customerInfo.getRoot());
    }

    /**
     * Handles the event where a OrderCard is clicked and the order info needs to be shown.
     * @param order The order to be displayed on the information pane.
     */
    private void showOrderInfo(Order order) {
        // ensure customer table cell is selectable again
        customerListPanel.getSelectionModel().clearSelection();
        clearInformationPanel();

        orderInfo = new OrderInfo(order);
        infoPane.getChildren().add(orderInfo.getRoot());
    }

    private void clearInformationPanel() {
        infoPane.getChildren().clear();
    }
}

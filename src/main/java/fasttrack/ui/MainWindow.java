package fasttrack.ui;

import java.util.logging.Logger;

import fasttrack.commons.core.GuiSettings;
import fasttrack.commons.core.LogsCenter;
import fasttrack.logic.Logic;
import fasttrack.logic.commands.CommandResult;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.logic.parser.exceptions.ParseException;
import fasttrack.model.AnalyticModelManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
    private ExpenseListPanel expenseListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private ResultsHeader resultsHeader;
    private ResultsDetails resultsDetails;
    private StatisticsPanel statisticsPanel;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane listPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane resultsHeaderPlaceholder;

    @FXML
    private StackPane resultsDetailsPlaceholder;

    @FXML
    private StackPane statisticsPlaceholder;


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

        expenseListPanel = new ExpenseListPanel(logic.getFilteredExpenseList());
        listPanelPlaceholder.getChildren().add(expenseListPanel.getRoot());
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        resultsHeader = new ResultsHeader(logic.getAppliedCategoryFilter());
        resultsHeaderPlaceholder.getChildren().add(resultsHeader.getRoot());

        resultsDetails = new ResultsDetails(logic.getFilteredExpenseList(),
                logic.getRecurringExpenseManagerList(),
                logic.getFilteredCategoryList(), logic.getAppliedTimeSpanFilter());
        resultsDetailsPlaceholder.getChildren().add(resultsDetails.getRoot());

        statisticsPanel = new StatisticsPanel(new AnalyticModelManager(logic.getExpenseTracker()));
        statisticsPlaceholder.getChildren().add(statisticsPanel.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        SuggestionListPanel suggestionListPanel = new SuggestionListPanel(logic.getFilteredCategoryList(), commandBox);


        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        resultDisplayPlaceholder.getChildren().add(suggestionListPanel.getRoot());

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
     * Toggles the display between the different screens
     * @param screenType the screen to be shown
     */
    public void switchListPanel(ScreenType screenType) {
        resultsHeader.setHeader(screenType);
        resultsDetails.switchDetails(screenType);
        Parent listPanelRoot;
        switch (screenType) {
        case EXPENSE_SCREEN:
            listPanelRoot = new ExpenseListPanel(logic.getFilteredExpenseList()).getRoot();
            break;
        case CATEGORY_SCREEN:
            listPanelRoot = new CategoryListPanel(logic.getFilteredCategoryList(),
                    logic.getFilteredExpenseList()).getRoot();
            break;
        case RECURRING_EXPENSE_SCREEN:
            listPanelRoot = new RecurringExpensePanel(logic.getRecurringExpenseManagerList()).getRoot();
            break;
        default:
            throw new IllegalArgumentException("Screen type does not exist");
        }
        listPanelPlaceholder.getChildren().setAll(listPanelRoot);
    }

    /**
     * Executes the command and returns the result.
     *
     * @see fasttrack.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            expenseListPanel.refreshList();
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            switchListPanel(commandResult.getScreenType());
            if (commandResult.isShowHelp()) {
                handleHelp();
            }
            if (commandResult.isExit()) {
                handleExit();
            }
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}

package mycelium.mycelium.ui;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mycelium.mycelium.commons.core.GuiSettings;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.logic.commands.CommandResult;
import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.logic.uievent.UiEventManager;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.ui.commandbox.CommandBox;
import mycelium.mycelium.ui.commandbox.mode.CommandMode;
import mycelium.mycelium.ui.commandbox.mode.Mode;
import mycelium.mycelium.ui.commandbox.mode.Mode.ModeType;
import mycelium.mycelium.ui.commandlog.CommandLog;
import mycelium.mycelium.ui.entitypanel.EntityPanel;
import mycelium.mycelium.ui.helpwindow.HelpWindow;
import mycelium.mycelium.ui.statisticsbox.StatisticsBox;
import mycelium.mycelium.ui.statusbarfooter.StatusBarFooter;
import mycelium.mycelium.ui.utils.TabsPanelList;

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
    private HelpWindow helpWindow;
    private CommandBox commandBox;
    private CommandLog commandLog;

    private EntityPanel entityPanel;
    private StatisticsBox statisticsBox;
    private TabsPanelList tabsPanelList;

    private StatusBarFooter statusBarFooter;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane commandLogPlaceholder;

    @FXML
    private StackPane entityPanelPlaceholder;
    @FXML
    private StackPane statisticsBoxPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

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

        setEventHandlers();
    }

    /**
     * Return the primary stage of the application.
     *
     * @return the primary stage of the application.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setEventHandlers() {
        getRoot().addEventFilter(
            UiEventManager.TYPE,
            new UiEventManager(logic, this).getEventHandler());
    }

    /**
     * Fills up all the placeholders of this window.
     */
    private void fillInnerParts() {
        logger.info("Filling inner parts of MainWindow...");
        helpWindow = new HelpWindow();

        commandBox = new CommandBox(new CommandMode(this));
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        commandLog = new CommandLog();
        commandLogPlaceholder.getChildren().add(commandLog.getRoot());

        entityPanel = new EntityPanel(logic.getFilteredProjectList(), logic.getFilteredClientList());
        entityPanelPlaceholder.getChildren().add(entityPanel.getRoot());

        statisticsBox = new StatisticsBox(logic);
        statisticsBoxPlaceholder.getChildren().add(statisticsBox.getRoot());

        tabsPanelList = new TabsPanelList(entityPanel, statisticsBox);

        statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
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

    /**
     * Closes the application.
     */
    @FXML
    public void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
            (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    void show() {
        primaryStage.show();
        fillInnerParts();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    public CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            commandLog.setFeedbackToUser(commandResult.getFeedbackToUser());
            commandResult.executeUiAction(this);
            return commandResult;
        } catch (CommandException e) {
            logger.info("Command exception: " + commandText);
            commandLog.setFeedbackToUser(e.getMessage());
            e.executeUiAction(this);
            throw e;
        } catch (ParseException e) {
            logger.info("Invalid command: " + commandText);
            commandLog.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }


    // CommandBox methods ======================================================

    /**
     * Returns the current mode type of the command box.
     *
     * @return the current mode type of the command box.
     * @see CommandBox#getModeType()
     */
    public ModeType getCommandBoxModeType() {
        return commandBox.getModeType();
    }

    /**
     * Sets the mode of the command box.
     *
     * @param mode the mode to set the command box to.
     * @see CommandBox#setMode(Mode)
     */
    public void setCommandBoxMode(Mode mode) {
        commandBox.setMode(mode);
    }

    /**
     * Sets the input of the command box.
     *
     * @param string the input to set the command box to.
     * @see CommandBox#setInput(String)
     */
    public void setCommandBoxInput(String string) {
        commandBox.setInput(string);
    }

    /**
     * Appends the input of the command box.
     *
     * @param string the input to append to the command box.
     */
    public void appendCommandBoxHighlighted(String string) {
        commandBox.appendHighlighted(string);
    }

    /**
     * Moves the cursor of the command box to the end of the line.
     *
     * @see CommandBox#moveToEndOfLine()
     */
    public void moveCommandBoxToEndOfLine() {
        commandBox.moveToEndOfLine();
    }

    /**
     * Moves the cursor of the command box to the start of the line.
     *
     * @see CommandBox#moveToStartOfLine()
     */
    public void moveCommandBoxToStartOfLine() {
        commandBox.moveToStartOfLine();
    }

    /**
     * Sets the style of the command box to error.
     *
     * @see CommandBox#setStyleError()
     */
    public void setCommandBoxStyleError() {
        commandBox.setStyleError();
    }

    /**
     * Sets the style of the command box to default.
     *
     * @see CommandBox#setStyleDefault()
     */
    public void setCommandBoxStyleDefault() {
        commandBox.setStyleDefault();
    }

    /**
     * Sets the style of the command box to listening.
     *
     * @see CommandBox#setStyleListening()
     */
    public void setCommandBoxStyleListening() {
        commandBox.setStyleListening();
    }

    /**
     * Sets the style of the command box to listening.
     *
     * @see CommandBox#setStyleListening()
     */
    public void focusCommandBox() {
        commandBox.requestFocus();
    }

    // CommandLog methods ======================================================
    /**
     * Sets the feedback to the user.
     *
     * @param feedback the feedback to set.
     * @see CommandLog#setFeedbackToUser(String)
     */
    public void setFeedbackToUser(String feedback) {
        commandLog.setFeedbackToUser(feedback);
    }

    // TabsPanelList methods =====================================================
    /**
     * Switches between the statistic panel and the entity panel.
     */
    public void nextTabPanel() {
        tabsPanelList.nextTabPanel();
    }

    /**
     * Sets the entity panel to the next tab.
     *
     * @see EntityPanel#nextTab()
     */
    public void nextTab() {
        tabsPanelList.nextTab();
    }

    /**
     * Selects the next item in the current entity panel.
     *
     * @see TabsPanelList#nextItem()
     */
    public void nextItem() {
        tabsPanelList.nextItem();
    }

    /**
     * Selects the previous item in the current entity panel.
     */
    public void prevItem() {
        tabsPanelList.prevItem();
    }

    /**
     * Focus the entity panel.
     */
    public void focusEntityPanel() {
        assert tabsPanelList.get(0) instanceof EntityPanel;
        tabsPanelList.focusTabPanel(0);
    }

    // EntityPanel methods ======================================================
    /**
     * Selects the client tab.
     *
     * @see EntityPanel#selectClientTab()
     */
    public void selectClientTab() {
        entityPanel.selectClientTab();
    }

    /**
     * Selects the project tab.
     *
     * @see EntityPanel#selectProjectTab()
     */
    public void selectProjectTab() {
        entityPanel.selectProjectTab();
    }

    /**
     * Sets the projects to be displayed in the entity panel.
     *
     * @param projectList the list of projects to be displayed.
     * @see EntityPanel#setProjects(ObservableList)
     */
    public void setProjects(ObservableList<Project> projectList) {
        entityPanel.setProjects(projectList);
    }

    /**
     * Sets the clients to be displayed in the entity panel.
     *
     * @param clientList the list of clients to be displayed.
     * @see EntityPanel#setClients(ObservableList)
     */
    public void setClients(ObservableList<Client> clientList) {
        entityPanel.setClients(clientList);
    }

    /**
     * Returns the identifier of the currently selected entity.
     * Entity refers to either project or client.
     *
     * @return the identifier of the currently selected entity.
     * @see EntityPanel#getSelectedEntityIdentifier()
     */
    public Optional<String> getSelectedEntityIdentifier() {
        return entityPanel.getSelectedEntityIdentifier();
    }
}

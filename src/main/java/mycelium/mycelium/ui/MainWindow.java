package mycelium.mycelium.ui;

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
import mycelium.mycelium.ui.statusbarfooter.StatusBarFooter;

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
    public ModeType getCommandBoxModeType() {
        return commandBox.getModeType();
    }

    public void setCommandBoxMode(Mode mode) {
        commandBox.setMode(mode);
    }

    public void setCommandBoxInput(String string) {
        commandBox.setInput(string);
    }

    public void setCommandBoxStyleError() {
        commandBox.setStyleError();
    }

    public void setCommandBoxStyleDefault() {
        commandBox.setStyleDefault();
    }

    public void setCommandBoxStyleListening() {
        commandBox.setStyleListening();
    }

    // CommandLog methods ======================================================
    public void setFeedbackToUser(String feedback) {
        commandLog.setFeedbackToUser(feedback);
    }

    // EntityPanel methods =====================================================
    public void nextTab() {
        entityPanel.nextTab();
    }

    public void selectClientTab() {
        entityPanel.selectClientTab();
    }

    public void selectProjectTab() {
        entityPanel.selectProjectTab();
    }

    public void setProjects(ObservableList<Project> projectList) {
        entityPanel.setProjects(projectList);
    }

    public void setClients(ObservableList<Client> clientList) {
        entityPanel.setClients(clientList);
    }
}

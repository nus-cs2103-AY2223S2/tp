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
import mycelium.mycelium.model.FuzzyManager;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.ui.commandbox.CommandBox;
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

    public EntityPanel getEntityPanel() {
        return entityPanel;
    }

    public CommandBox getCommandBox() {
        return commandBox;
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

        commandBox =
            new CommandBox(this, this::executeCommand, new FuzzyManager(logic.getAddressBook()), (mainWindow) -> {
            }, (mainWindow) -> {
                // Just to "reset" the lists back to its state in the address book.
                mainWindow.setClients(logic.getFilteredClientList());
                mainWindow.setProjects(logic.getFilteredProjectList());
            });
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
        fillInnerParts(); //This should be called before creating other UI parts
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
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


    /**
     * Sets the clients in the entity panel.
     */
    public void setClients(ObservableList<Client> list) {
        entityPanel.setClients(list);
    }

    /**
     * Sets the projects in the entity panel.
     */
    public void setProjects(ObservableList<Project> list) {
        entityPanel.setProjects(list);
    }
}

package arb.ui;

import java.util.logging.Logger;

import arb.commons.core.GuiSettings;
import arb.commons.core.LogsCenter;
import arb.logic.Logic;
import arb.logic.commands.CommandResult;
import arb.logic.commands.exceptions.CommandException;
import arb.logic.parser.exceptions.ParseException;
import arb.model.ListType;
import arb.storage.JsonStorageState;
import arb.ui.client.ClientListPanel;
import arb.ui.project.ProjectListPanel;
import arb.ui.tag.TagMappingListPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
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
    private ClientListPanel clientListPanel;
    private ProjectListPanel projectListPanel;
    private TagMappingListPanel tagMappingListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private Label listLabel;

    @FXML
    private StackPane listPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

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
        clientListPanel = new ClientListPanel(logic.getSortedClientList());
        projectListPanel = new ProjectListPanel(logic.getSortedProjectList());
        tagMappingListPanel = new TagMappingListPanel(logic.getTagMappingList());

        setCurrentlyShownList(ListType.CLIENT);

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        listLabel.prefWidth(primaryStage.getWidth());
        listLabel.setFont(new Font(24));

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the initial message upon startup.
     */
    void setInitialMessage(JsonStorageState initialStorageState) {
        switch (initialStorageState) {
        case VALID:
            resultDisplay.setFeedbackToUser("Data file found. Saved data successfully loaded.");
            break;
        case NOTFOUND:
            resultDisplay.setFeedbackToUser("Data file not found. Sample data successfully loaded.");
            break;
        case INVALID:
            resultDisplay.setFeedbackToUser("Data file not in the correct format. "
                    + "No data loaded and data file has been wiped.");
            break;
        default:
            break;
        }
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

    public ClientListPanel getClientListPanel() {
        return clientListPanel;
    }

    public ProjectListPanel getProjectListPanel() {
        return projectListPanel;
    }

    public TagMappingListPanel getTagMappingListPanel() {
        return tagMappingListPanel;
    }

    private void replaceShownList(Node newList) {
        this.listPanelPlaceholder.getChildren().clear();
        this.listPanelPlaceholder.getChildren().add(newList);
    }

    private void showClientList() {
        this.logic.setListType(ListType.CLIENT);
        this.listLabel.setText("Client List");
        this.replaceShownList(clientListPanel.getRoot());
    }

    private void showProjectList() {
        this.logic.setListType(ListType.PROJECT);
        this.listLabel.setText("Project List");
        this.replaceShownList(projectListPanel.getRoot());
    }

    private void showTagList() {
        this.logic.setListType(ListType.TAG);
        this.listLabel.setText("Tag List");
        this.replaceShownList(tagMappingListPanel.getRoot());
    }

    private void setCurrentlyShownList(ListType listToBeShown) {
        switch (listToBeShown) {
        case PROJECT:
            showProjectList();
            break;
        case CLIENT:
            showClientList();
            break;
        case TAG:
            showTagList();
            break;
        default:
            break;
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @see arb.logic.Logic#execute(String)
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

            logic.setLinkMode(commandResult.shouldEnterLinkMode());

            logger.info("Display " + commandResult.getListToBeShown() + " list.");
            setCurrentlyShownList(commandResult.getListToBeShown());

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}

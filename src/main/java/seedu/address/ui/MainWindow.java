package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;

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
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private UserProfilePanel userProfilePanel;
    private MeetListPanel meetListPanel;
    private ScheduledMeetsListPanel scheduledMeetsListPanel;


    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private Button helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane userProfilePlaceholder;

    @FXML
    private VBox meetPlaceholder;

    @FXML
    private Label userName;

    @FXML
    private StackPane scheduledMeetsPlaceholder;

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

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getObservablePersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand, logic);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        userProfilePanel = new UserProfilePanel(logic);
        userProfilePlaceholder.getChildren().add(userProfilePanel.getRoot());

        meetListPanel = new MeetListPanel(logic.getObservableRecommendationList());
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(meetListPanel.getRoot());
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        meetPlaceholder.getChildren().add(scrollPane);

        scheduledMeetsListPanel = new ScheduledMeetsListPanel(logic.getObservableMeetUpList());
        scheduledMeetsPlaceholder.getChildren().add(scheduledMeetsListPanel.getRoot());

        setUserName(logic.getUser());
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

    @FXML
    public void handleShowUserProfile() {
        updateUserProfilePanel(logic.getUser());
    }

    /**
     * Updates user profile panel with specified person.
     * @param person The contact to show in user profile panel.
     */
    public void updateUserProfilePanel(Person person) {
        userProfilePanel = new UserProfilePanel(person);
        userProfilePlaceholder.getChildren().clear();
        userProfilePlaceholder.getChildren().add(userProfilePanel.getRoot());
    }

    public void setUserName(User user) {
        String name = String.valueOf(user.getName());
        userName.setText(name);
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

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isToShowNewPerson() && commandResult.getDisplayPerson().isPresent()) {
                Person newPerson = commandResult.getDisplayPerson().get();
                userProfilePanel = new UserProfilePanel(newPerson);
                userProfilePlaceholder.getChildren().clear();
                userProfilePlaceholder.getChildren().add(userProfilePanel.getRoot());
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            //updates user name in upper left corner
            userName.setText(logic.getUser().getName().getValue());

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}

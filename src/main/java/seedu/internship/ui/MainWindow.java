package seedu.internship.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.internship.commons.core.GuiSettings;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.logic.Logic;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.internship.Internship;

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
    private InternshipListPanel internshipListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private InternshipDetailsCard internshipDetailsCard;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane internshipListPanelPlaceholder;

    @FXML
    private StackPane internshipDetailsPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;
    @FXML
    private ScrollPane rightPanel;
    @FXML
    private Text introOne;
    @FXML
    private Text introTwo;
    @FXML
    private ImageView introThree;

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

        introThree.setImage(new Image(this.getClass().getResourceAsStream("/images/internbuddy-hero.png")));

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
        internshipListPanel = new InternshipListPanel(logic.getFilteredInternshipList(), logic.getModel(), this);
        internshipListPanelPlaceholder.getChildren().add(internshipListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getInternBuddyFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
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
     * Executes the command and returns the result.
     *
     * @see seedu.internship.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            updateRightPanel();

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

    /**
     * Updates the right panel whenever user calls a valid view command
     */
    public void updateRightPanel() {
        Internship selectedInternship = logic.getSelectedInternship();
        if (selectedInternship != null) {
            //Update with internship information
            internshipDetailsCard = new InternshipDetailsCard(selectedInternship, primaryStage.getScene());
            internshipDetailsPanelPlaceholder.getChildren().clear();
            internshipDetailsPanelPlaceholder.getChildren().add(internshipDetailsCard.getRoot());
            internshipDetailsPanelPlaceholder.setAlignment(Pos.TOP_CENTER);
        } else {
            //Reset to original introduction information
            internshipDetailsPanelPlaceholder.getChildren().clear();
            internshipDetailsPanelPlaceholder.getChildren().add(introOne);
            internshipDetailsPanelPlaceholder.getChildren().add(introTwo);
            internshipDetailsPanelPlaceholder.getChildren().add(introThree);
            internshipDetailsPanelPlaceholder.setAlignment(Pos.CENTER);
        }
    }


}

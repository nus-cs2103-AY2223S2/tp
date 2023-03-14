package seedu.careflow.ui;

import java.util.Objects;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.careflow.commons.core.GuiSettings;
import seedu.careflow.commons.core.LogsCenter;
import seedu.careflow.logic.CareFlowLogic;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;

    private final CareFlowLogic logic;

    private PatientListPanel patientListPanel;

    private DrugListPanel drugListPanel;

    private DrugPieChartPanel drugPieChartPanel;

    private HospitalListPanel hospitalRecordlistPanel;

    private ResultDisplay resultDisplay;
    private final HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane patientListPanelPlaceholder;

    @FXML
    private MenuItem light;

    @FXML
    private MenuItem dark;

    @FXML
    private StackPane patientDrugListPanelPlaceholder;

    @FXML
    private StackPane drugListPanelPlaceholder;

    @FXML
    private StackPane drugPieChartPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane hospitalRecordPanelPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private Scene scene;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab patientTab;

    @FXML
    private Tab drugTab;

    @FXML
    private Tab hospitalTab;

    private String theme;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, CareFlowLogic logic) {
        super(FXML, primaryStage);
        primaryStage.setTitle("CareFlow");

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());
        helpWindow = new HelpWindow();
        setTheme(logic.getGuiSettings());

        setAccelerators();

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
        setAccelerator(light, KeyCombination.valueOf("CTRL+SHIFT+L"));
        setAccelerator(dark, KeyCombination.valueOf("CTRL+SHIFT+D"));
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

        //make the tabPane fill its parent VBox
        VBox.setVgrow(tabPane, Priority.ALWAYS);

        patientListPanel = new PatientListPanel(logic.getFilteredPatientList());
        patientListPanelPlaceholder.getChildren().add(patientListPanel.getRoot());

        drugListPanel = new DrugListPanel(logic.getFilteredDrugList());
        drugListPanelPlaceholder.getChildren().add(drugListPanel.getRoot());

        drugPieChartPanel = new DrugPieChartPanel(logic.getFilteredDrugList(), logic);
        drugPieChartPanelPlaceholder.getChildren().add(drugPieChartPanel.getRoot());

        hospitalRecordlistPanel = new HospitalListPanel(logic.getHospitalList());
        hospitalRecordPanelPlaceholder.getChildren().add(hospitalRecordlistPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter =
                new StatusBarFooter(logic.getPatientRecordFilePath(), logic.getDrugInventoryFilePath());
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
     * Sets the default theme based on {@code guiSettings}
     */
    private void setTheme(GuiSettings guiSettings) {
        String theme = guiSettings.getTheme();
        if (theme.equals("DARK")) {
            toggleDarkTheme();
        } else {
            toggleLightTheme();
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
                (int) primaryStage.getX(), (int) primaryStage.getY(), theme);
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }


    /**
     * Toggles the Light theme version of the app
     */
    @FXML
    public void toggleLightTheme() {
        // enable style
        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("/stylesheet/LightTheme.css")).toExternalForm());
        helpWindow.getScene().getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("/stylesheet/HelpWindowLight.css")).toExternalForm());

        // disable style
        scene.getStylesheets().remove(Objects.requireNonNull(
                getClass().getResource("/stylesheet/DarkTheme.css")).toExternalForm());
        scene.getStylesheets().remove(Objects.requireNonNull(
                getClass().getResource("/stylesheet/Extensions.css")).toExternalForm());
        helpWindow.getScene().getStylesheets().remove(Objects.requireNonNull(
                getClass().getResource("/stylesheet/HelpWindow.css")).toExternalForm());
        theme = "LIGHT";

    }

    /**
     * Toggles the Dark theme version of the app
     */
    @FXML
    public void toggleDarkTheme() {
        // enable style
        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("/stylesheet/DarkTheme.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("/stylesheet/Extensions.css")).toExternalForm());
        helpWindow.getScene().getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("/stylesheet/HelpWindow.css")).toExternalForm());


        // disable style
        scene.getStylesheets().remove(Objects.requireNonNull(
                getClass().getResource("/stylesheet/LightTheme.css")).toExternalForm());
        helpWindow.getScene().getStylesheets().remove(Objects.requireNonNull(
                getClass().getResource("/stylesheet/HelpWindowLight.css")).toExternalForm());
        theme = "DARK";
    }
    /**
     * Executes the command and returns the result.
     *
     * @see seedu.careflow.logic.CareFlowLogic#execute(String)
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

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}

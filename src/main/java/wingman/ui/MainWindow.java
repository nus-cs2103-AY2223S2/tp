package wingman.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import wingman.commons.core.GuiSettings;
import wingman.commons.core.LogsCenter;
import wingman.commons.util.AppUtil;
import wingman.logic.Logic;
import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.logic.core.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static final String WINGMAN_LOGO = "/images/Wingman.png";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private FlightListPanel flightListPanel;
    private CrewListPanel crewListPanel;
    private PlaneListPanel planeListPanel;
    private PilotListPanel pilotListPanel;
    private LocationListPanel locationListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private ImageView wingmanLogo;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane menuBarPlaceholder;

    @FXML
    private StackPane flightListPanelPlaceholder;

    @FXML
    private StackPane crewListPanelPlaceholder;

    @FXML
    private StackPane pilotListPanelPlaceholder;

    @FXML
    private StackPane planeListPanelPlaceholder;

    @FXML
    private StackPane locationListPanelPlaceholder;

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
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(
            MenuItem menuItem,
            KeyCombination keyCombination
    ) {
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
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(
                    event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        wingmanLogo.setImage(AppUtil.getImage(WINGMAN_LOGO));

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter =
                new StatusBarFooter(logic.getOperationMode());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        MenuBar menuBar =
                new MenuBar();
        menuBarPlaceholder.getChildren().add(menuBar.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        flightListPanel = new FlightListPanel(logic.getFilteredFlightList());
        Region flight = flightListPanel.getRoot();
        flightListPanelPlaceholder.getChildren().add(flight);

        crewListPanel = new CrewListPanel(logic.getFilteredCrewList());
        Region crew = crewListPanel.getRoot();
        crewListPanelPlaceholder.getChildren().add(crew);

        planeListPanel = new PlaneListPanel(logic.getFilteredPlaneList());
        Region plane = planeListPanel.getRoot();
        planeListPanelPlaceholder.getChildren().add(plane);

        pilotListPanel = new PilotListPanel(logic.getFilteredPilotList());
        Region pilot = pilotListPanel.getRoot();
        pilotListPanelPlaceholder.getChildren().add(pilot);

        locationListPanel = new LocationListPanel(logic.getFilteredLocationList());
        Region location = locationListPanel.getRoot();
        locationListPanelPlaceholder.getChildren().add(location);
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
        GuiSettings guiSettings = new GuiSettings(
                primaryStage.getWidth(),
                primaryStage.getHeight(),
                (int) primaryStage.getX(),
                (int) primaryStage.getY()
        );
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
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
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            StatusBarFooter statusBarFooter =
                    new StatusBarFooter(logic.getOperationMode());
            statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

            MenuBar menuBar =
                    new MenuBar();
            menuBarPlaceholder.getChildren().add(menuBar.getRoot());

            flightListPanel = new FlightListPanel(logic.getFilteredFlightList());
            Region flight = flightListPanel.getRoot();
            flightListPanelPlaceholder.getChildren().add(flight);

            crewListPanel = new CrewListPanel(logic.getFilteredCrewList());
            Region crew = crewListPanel.getRoot();
            crewListPanelPlaceholder.getChildren().add(crew);

            planeListPanel = new PlaneListPanel(logic.getFilteredPlaneList());
            Region plane = planeListPanel.getRoot();
            planeListPanelPlaceholder.getChildren().add(plane);

            pilotListPanel = new PilotListPanel(logic.getFilteredPilotList());
            Region pilot = pilotListPanel.getRoot();
            pilotListPanelPlaceholder.getChildren().add(pilot);

            locationListPanel = new LocationListPanel(logic.getFilteredLocationList());
            Region location = locationListPanel.getRoot();
            locationListPanelPlaceholder.getChildren().add(location);

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

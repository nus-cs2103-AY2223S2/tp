package seedu.address.ui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.ui.main.ResultDisplay;
import seedu.address.ui.main.StatusBarFooter;

/**
 * Controller for a timetable page
 */
public class TimetableWindow extends UiPart<Stage> implements Initializable {

    private static final String FXML = "TimetableWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());
    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ResultDisplay resultDisplay;

    @FXML
    private TreeView<String> listOfMonthsPlaceholder;

    @FXML
    private StackPane monthlyDeliveryJobListPanelPlaceholder;


    @FXML
    private StackPane commandBoxPlaceholder;
    @FXML
    private StackPane jobListPanelPlaceholder;
    @FXML
    private StackPane resultDisplayPlaceholder;
    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code JobWindow} with the given {@code Stage} and {@code Logic}.
     */
    public TimetableWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        TreeItem<String> year = new TreeItem<String>(String.format("Year"));

        for (int i = 1; i < 13; i++) {
            TreeItem<String> month = new TreeItem<String>(String.format("Month %d", i));
            TreeItem<String> jobListInMonth = new TreeItem<String>();
            month.getChildren().add(month);
            year.getChildren().add(month);
        }

        listOfMonthsPlaceholder.setShowRoot(false);
        listOfMonthsPlaceholder.setRoot(year);


    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing timetable page of job list.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the job window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the job window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the job window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        //MonthlyDeliveryJobListPanel monthPanel = new MonthlyDeliveryJobListPanel();
        //monthlyDeliveryJobListPanelPlaceholder.getChildren().add(monthPanel.getRoot());


        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        //  CommandBox commandBox = new CommandBox(this::executeCommand);
        //commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
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

    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        primaryStage.hide();
    }

    @FXML
    private void selectItem() {

    }

}


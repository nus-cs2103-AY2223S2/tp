package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.stats.TotalJobs;
import seedu.address.ui.job.DeliveryJobDetailPane;
import seedu.address.ui.job.DeliveryJobListPanel;
import seedu.address.ui.main.ResultDisplay;

/**
 * Controller for a statistics page
 */
public class StatisticsWindow extends UiPart<Stage> {
    public static final String STATISTICS = "Here are your statistics: ";
    private static final String FXML = "StatisticsWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());
    private Stage primaryStage;
    private Logic logic;

    private ResultDisplay resultDisplay;
    private DeliveryJobListPanel deliveryJobListPanel;
    @FXML
    private StackPane commandBoxPlaceholder;
    @FXML
    private StackPane jobListPanelPlaceholder;
    @FXML
    private StackPane resultDisplayPlaceholder;
    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private Label statsMessage;
    @FXML
    private Label totalJob;
    @FXML
    private StackPane deliveryJobListPanelPlaceholder;
    @FXML
    private StackPane deliveryJobDetailPlaceholder;

    /**
     * Creates a {@code StatisticsWindow} with the given {@code Stage} and {@code Logic}.
     */
    public StatisticsWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());
        statsMessage.setText(STATISTICS);
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
        logger.fine("Showing stats page about the driver.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the stats window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the stats window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the stats window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    public DeliveryJobListPanel getDeliveryJobListPanel() {
        return deliveryJobListPanel;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {

        deliveryJobListPanel = new DeliveryJobListPanel(logic.getFilteredDeliveryJobList(), (idx, job) -> {
            deliveryJobDetailPlaceholder.getChildren().clear();
            DeliveryJobDetailPane detailPane = new DeliveryJobDetailPane(job, idx);
            deliveryJobDetailPlaceholder.getChildren().add(detailPane.getRoot());
        });
        deliveryJobListPanelPlaceholder.getChildren().add(deliveryJobListPanel.getRoot());

        ObservableList<DeliveryJob> list = logic.getFilteredDeliveryJobList();
        totalJob.setText(new TotalJobs(list.size()).toString());

        //resultDisplay = new ResultDisplay();
        //resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        //StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        //statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        //CommandBox commandBox = new CommandBox(this::executeCommand);
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

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}

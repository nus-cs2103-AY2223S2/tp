package seedu.address.ui;

import java.io.FileNotFoundException;
import java.time.LocalDate;
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
import seedu.address.model.stats.StatisticItemList;
import seedu.address.model.stats.TotalCompleted;
import seedu.address.model.stats.TotalEarnings;
import seedu.address.model.stats.TotalJobs;
import seedu.address.model.stats.TotalPending;
import seedu.address.ui.jobs.DeliveryJobListPanel;
import seedu.address.ui.main.ResultDisplay;

/**
 * Controller for a statistics page
 */
public class StatisticsWindow extends UiPart<Stage> {
    public static final String ALL_STATS = "All time statistics: ";
    public static final String LAST_WEEK = "Last Week's statistics:";
    public static final String STATISTICS = "Here are your statistics: ";
    private static final String FXML = "StatisticsWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());
    private Stage primaryStage;
    private Logic logic;

    private ResultDisplay resultDisplay;
    private DeliveryJobListPanel deliveryJobListPanel;
    @FXML
    private Label header;
    @FXML
    private Label allStats;
    @FXML
    private Label lastWeekStats;
    @FXML
    private Label titleAllStats;
    @FXML
    private Label titleLastWeek;
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

        header.setText(STATISTICS);
        titleAllStats.setText(ALL_STATS);
        titleLastWeek.setText(LAST_WEEK);
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
     * Returns a String that represents the statistics to be displayed
     *
     * @param list List of delivery jobs to generate statistic from
     */
    public String fillStats(ObservableList<DeliveryJob> list) {
        TotalJobs totalJobs = new TotalJobs(list.size());
        TotalEarnings totalEarnings = new TotalEarnings(logic.getTotalEarnings(list));
        TotalCompleted totalCompleted = new TotalCompleted(logic.getTotalCompleted(list));
        TotalPending totalPending = new TotalPending(logic.getTotalPending(list));

        StatisticItemList statisticItemList = new StatisticItemList();

        statisticItemList.addStats((totalJobs));
        statisticItemList.addStats(totalEarnings);
        statisticItemList.addStats(totalCompleted);
        statisticItemList.addStats(totalPending);

        return statisticItemList.printStats();
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        ObservableList<DeliveryJob> list = logic.getFilteredDeliveryJobList();
        LocalDate lastWeekDate = LocalDate.now().minusDays(7);
        ObservableList<DeliveryJob> currWeekJobs = logic.weekJobsList(list, lastWeekDate);
        allStats.setText(fillStats(list));
        lastWeekStats.setText(fillStats(currWeekJobs));
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
    private CommandResult executeCommand(String commandText)
            throws CommandException, ParseException, FileNotFoundException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            //resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        } catch (FileNotFoundException e) {
            logger.info("File not found: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            //resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}

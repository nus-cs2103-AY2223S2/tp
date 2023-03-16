package seedu.address.ui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.ui.job.DeliveryJobListPanel;
import seedu.address.ui.main.CommandBox;
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
    private LocalDate focusDate;
    private int focusDayOfWeek;

    // Independent Ui parts residing in this Ui container
    private ResultDisplay resultDisplay;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane deliveryJobListPanelPlaceholder1;

    @FXML
    private StackPane deliveryJobListPanelPlaceholder2;

    @FXML
    private StackPane deliveryJobListPanelPlaceholder3;

    @FXML
    private StackPane deliveryJobListPanelPlaceholder4;

    @FXML
    private StackPane deliveryJobListPanelPlaceholder5;

    @FXML
    private StackPane deliveryJobListPanelPlaceholder6;

    @FXML
    private StackPane deliveryJobListPanelPlaceholder7;

    @FXML
    private Text day1;

    @FXML
    private Text day2;

    @FXML
    private Text day3;

    @FXML
    private Text day4;

    @FXML
    private Text day5;

    @FXML
    private Text day6;

    @FXML
    private Text day7;

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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        focusDate = LocalDate.now();
        focusDayOfWeek = focusDate.getDayOfWeek().getValue();
    }

    /**
     * Shows the timetable window.
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
        logger.fine("Showing timetable.");
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
        //Get year and month of week
        year.setText(String.valueOf(focusDate.getYear()));
        month.setText(String.valueOf(focusDate.getMonth()));

        focusDate = logic.getFocusDate();
        setDayText();

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        logic.setWeekDeliveryJobList(focusDate);
        Map<LocalDate, ArrayList<ArrayList<DeliveryJob>>> weekJobList = logic.getWeekDeliveryJobList();

        addJobSlotsToPanel(deliveryJobListPanelPlaceholder1, logic.getDayofWeekJob(1));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder2, logic.getDayofWeekJob(2));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder3, logic.getDayofWeekJob(3));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder4, logic.getDayofWeekJob(4));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder5, logic.getDayofWeekJob(5));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder6, logic.getDayofWeekJob(6));
        addJobSlotsToPanel(deliveryJobListPanelPlaceholder7, logic.getDayofWeekJob(7));

    }

    private void setDayText() {
        int focusDayOfWeek = focusDate.getDayOfWeek().getValue();

        int day1Text = focusDate.plusDays(1 - focusDayOfWeek).getDayOfMonth();
        int day2Text = focusDate.plusDays(2 - focusDayOfWeek).getDayOfMonth();
        int day3Text = focusDate.plusDays(3 - focusDayOfWeek).getDayOfMonth();
        int day4Text = focusDate.plusDays(4 - focusDayOfWeek).getDayOfMonth();
        int day5Text = focusDate.plusDays(5 - focusDayOfWeek).getDayOfMonth();
        int day6Text = focusDate.plusDays(6 - focusDayOfWeek).getDayOfMonth();
        int day7Text = focusDate.plusDays(7 - focusDayOfWeek).getDayOfMonth();

        day1.setText(String.valueOf(day1Text));
        day2.setText(String.valueOf(day2Text));
        day3.setText(String.valueOf(day3Text));
        day4.setText(String.valueOf(day4Text));
        day5.setText(String.valueOf(day5Text));
        day6.setText(String.valueOf(day6Text));
        day7.setText(String.valueOf(day7Text));

    }
    private void addJobSlotsToPanel(StackPane panelPlaceholder, ArrayList<ArrayList<DeliveryJob>> jobListInDay) {
        for (int i = 0; i < jobListInDay.size(); i++) {
            ObservableList<DeliveryJob> jobListInSlot = FXCollections.observableList(jobListInDay.get(i));
            DeliveryJobListPanel deliveryJobListPanel = new DeliveryJobListPanel(jobListInSlot);
            StackPane deliveryJobListPanelPlaceholder = new StackPane();
            Text slotNo = new Text();
            slotNo.setText(String.format("Slot %d", i + 1));
            deliveryJobListPanelPlaceholder.getChildren().add(slotNo);
            deliveryJobListPanelPlaceholder.getChildren().add(deliveryJobListPanel.getRoot());
            panelPlaceholder.getChildren().add(deliveryJobListPanelPlaceholder);
        }
    }

    /*private void addDayTextToTimetableStackPane() {
        logic.setWeekDeliveryJobList(focusDate);
        Map<LocalDate, ArrayList<ArrayList<DeliveryJob>>> weekJobList = logic.getWeekDeliveryJobList();
        List<LocalDate> dates = new ArrayList<LocalDate>(7);
        weekJobList.forEach((k,v) -> {
            dates.add(k);
        });
        for (int i = 0; i < dates.size(); i++) {
            Text date = new Text(String.valueOf(dates.get(i)));
          //  double textTranslationY = - (deliveryJobListPanelPlaceholder1.getHeight() / 2) * 0.75;
          //  date.setTranslateY(textTranslationY);
            deliveryJobListPanelPlaceholder1.getChildren().add(date);
        }
    }*/

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

    /*@Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        TreeItem<String> year = new TreeItem<String>(String.format("Year"));
        monthDeliveryJobListTreeView.setRoot(year);

        for (int i = 1; i < 13; i++) {
            TreeItem<String> month = new TreeItem<String>(String.format("Month %d", i));
            month.getChildren().add(new TreeItem<String>("09:00"));
            year.getChildren().add(month);
        }

        monthDeliveryJobListTreeView.setShowRoot(false);

    }*/

    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        primaryStage.hide();
    }

    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());


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

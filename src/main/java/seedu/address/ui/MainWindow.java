package seedu.address.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandGroup;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.jobs.CompleteDeliveryJobCommand;
import seedu.address.logic.commands.jobs.DeleteDeliveryJobCommand;
import seedu.address.logic.commands.jobs.ImportDeliveryJobCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.sorters.DeliveryFilterOption;
import seedu.address.model.jobs.sorters.DeliverySortOption;
import seedu.address.model.jobs.sorters.SortbyDate;
import seedu.address.model.jobs.sorters.SortbyDelivered;
import seedu.address.model.jobs.sorters.SortbyEarning;
import seedu.address.ui.jobs.AddDeliveryJobWindow;
import seedu.address.ui.jobs.DeliveryJobDetailPane;
import seedu.address.ui.jobs.DeliveryJobListPanel;
import seedu.address.ui.main.CommandBox;
import seedu.address.ui.main.ResultDisplay;
import seedu.address.ui.main.StatusBarFooter;
import seedu.address.ui.person.AddressBookDialog;
import seedu.address.ui.timetable.CompleteWindow;
import seedu.address.ui.timetable.UnscheduleWindow;

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
    private AddressBookDialog addressBookWindow;
    private AddDeliveryJobWindow addDeliveryJobWindow;
    private CompleteWindow completeWindow;
    private DeliveryJobListPanel deliveryJobListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private TimetableWindow timetableWindow;
    private ReminderListWindow reminderListWindow;
    private StatisticsWindow statsWindow;
    private UnscheduleWindow unscheduleWindow;

    @FXML
    private StackPane commandBoxPlaceholder;
    @FXML
    private StackPane deliveryJobDetailPlaceholder;
    @FXML
    private MenuItem helpMenuItem;
    @FXML
    private MenuItem timetableMenuItem;
    @FXML
    private MenuItem reminderListMenuItem;
    @FXML
    private MenuItem statsItem;
    @FXML
    private MenuItem addressBookMenuItem;
    @FXML
    private StackPane deliveryJobListPanelPlaceholder;
    @FXML
    private StackPane emptyDeliveryJobListPanelPlaceholder;
    @FXML
    private StackPane resultDisplayPlaceholder;
    @FXML
    private StackPane statusbarPlaceholder;

    private Consumer<DeliveryJob> completeDeliveryJobHandler = (job) -> {
        try {
            logger.info("[completeDeliveryJobHandler] complete: " + job.getJobId());
            CommandResult commandResult = logic
                    .execute(new CompleteDeliveryJobCommand(job.getJobId(), !job.getDeliveredStatus()));
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
        } catch (ParseException | CommandException e) {
            logger.warning(e.getMessage());
        } catch (FileNotFoundException | IllegalArgumentException e) {
            logger.warning(e.getMessage());
        }
    };

    private Consumer<DeliveryJob> editDeliveryJobHandler = (job) -> {
        logger.info("[editDeliveryJobHandler] edit: " + job.getJobId());
        if (addDeliveryJobWindow != null) {
            addDeliveryJobWindow.hide();
        }
        addDeliveryJobWindow = new AddDeliveryJobWindow(new Stage(), logic, job);
        addDeliveryJobWindow.setResultHandler(commandResult -> {
            logger.info("[editDeliveryJobHandler] edit complete: " + job.getJobId());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
        });
        addDeliveryJobWindow.show();
        addDeliveryJobWindow.fillInnerParts();
    };

    private Consumer<DeliveryJob> deleteDeliveryJobHandler = job -> {
        try {
            logger.info("[deleteDeliveryJobHandler] delete: " + job.getJobId());
            CommandResult commandResult = logic.execute(new DeleteDeliveryJobCommand(job.getJobId()));
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
        } catch (ParseException | CommandException | FileNotFoundException | IllegalArgumentException e) {
            logger.warning(e.getMessage());
        }
    };

    private BiConsumer<Integer, DeliveryJob> selectDeliveryJobHandler = (idx, job) -> {
        logger.info("[selectDeliveryJobHandler] select: " + idx);
        refreshDeliveryJobListView();

        if (idx >= 0) {
            DeliveryJobDetailPane detailPane = new DeliveryJobDetailPane(job);
            detailPane.fillInnerParts(logic.getAddressBook());
            deliveryJobDetailPlaceholder.getChildren().add(detailPane.getRoot());
            detailPane.setCompleteHandler(completeDeliveryJobHandler);
            detailPane.setEditHandler(editDeliveryJobHandler);
            detailPane.setDeleteHandler(deleteDeliveryJobHandler);
            return;
        }
    };

    private BiFunction<DeliverySortOption, Boolean, ObservableList<DeliveryJob>> sortDeliveryJobHandler = (by, asc) -> {
        logger.info("[sortDeliveryJobHandler] sort: " + by);
        String feedback = "List empty!";
        if (deliveryJobListPanel.size() > 0) {
            switch (by) {
            case COM:
                logic.updateSortedDeliveryJobListByComparator(new SortbyDelivered(asc));
                feedback = "Sort by delivery status.";
                break;
            case EARN:
                logic.updateSortedDeliveryJobListByComparator(new SortbyEarning(asc));
                feedback = "Sort by earning.";
                break;
            default:
                logic.updateSortedDeliveryJobListByComparator(new SortbyDate(asc));
                feedback = "Sort by delivery schedule.";
                break;
            }
        }
        resultDisplay.setFeedbackToUser(feedback + (asc ? " (ASC)" : " (DESC)"));
        return logic.getSortedDeliveryJobList();
    };

    private Consumer<DeliveryFilterOption> filterDeliveryJobHandler = by -> {
        logger.info("[filterDeliveryJobHandler] filter: " + by);
        switch (by) {
        case COM:
            logic.updateFilteredDeliveryJobList(job -> job.getDeliveredStatus());
            resultDisplay.setFeedbackToUser("Filter completed delivery.");
            break;
        case PEN:
            logic.updateFilteredDeliveryJobList(job -> !job.getDeliveredStatus());
            resultDisplay.setFeedbackToUser("Filter pending delivery.");
            break;
        default:
            logic.updateFilteredDeliveryJobList(job -> true);
            logic.updateSortedDeliveryJobListByComparator(new SortbyDate(true));
            resultDisplay.setFeedbackToUser("Show all delivery jobs.");
            break;
        }
    };

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
        timetableWindow = new TimetableWindow(new Stage(), logic, this.helpWindow);
        unscheduleWindow = new UnscheduleWindow(new Stage(), logic);
        completeWindow = new CompleteWindow(new Stage(), logic);
        reminderListWindow = new ReminderListWindow(new Stage(), logic);
        statsWindow = new StatisticsWindow(new Stage(), logic);
        addressBookWindow = new AddressBookDialog(new Stage(), logic, (person) -> {}, helpWindow);
    }

    /**
     * Returns primary stage of Main Window
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Returns help window of Main Window
     */
    public HelpWindow getHelpWindow() {
        return helpWindow;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
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

    private void refreshDeliveryJobListView() {
        logger.info("[refreshDeliveryJobListView] Refresh List (size): " + deliveryJobListPanel.size());
        if (deliveryJobListPanel.size() > 0) {
            emptyDeliveryJobListPanelPlaceholder.setVisible(false);
        } else {
            deliveryJobDetailPlaceholder.getChildren().clear();
            emptyDeliveryJobListPanelPlaceholder.setVisible(true);
        }
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        // Append views
        deliveryJobListPanel = new DeliveryJobListPanel(logic.getFilteredDeliveryJobList());
        deliveryJobListPanel.setSelectHandler(selectDeliveryJobHandler);
        deliveryJobListPanel.setCheckHandler(completeDeliveryJobHandler);
        deliveryJobListPanel.setDeleteHandler(deleteDeliveryJobHandler);
        deliveryJobListPanel.setOrderByHandler(sortDeliveryJobHandler);
        deliveryJobListPanel.setFilterHandler(filterDeliveryJobHandler);
        deliveryJobListPanelPlaceholder.getChildren().add(deliveryJobListPanel.getRoot());
        deliveryJobListPanel.selectDefault();

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getDeliveryJobSystemFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        // Set default ordering
        logic.updateSortedDeliveryJobListByComparator(new SortbyDate(true));

        // Initialise detail panel
        refreshDeliveryJobListView();
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
     * Focuses on the main window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
            logger.info("Opened help window.");
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Reloads and opens Timetable window.
     */
    @FXML
    public void handleTimetable() {
        logger.info("Opened timetable window of current week.");
        try {
            CommandResult commandResult = logic.execute("timetable");
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
        } catch (CommandException | ParseException e) {
            resultDisplay.setFeedbackToUser(e.getMessage());
        } catch (FileNotFoundException | IllegalArgumentException e) {
            resultDisplay.setFeedbackToUser(e.getMessage());
        }
        if (timetableWindow.isShowing()) {
            timetableWindow.focus();
        } else {
            timetableWindow.show();
        }
        timetableWindow.fillInnerParts();
    }

    /**
     * Opens timetable window
     */
    public void openTimetable() {
        if (!timetableWindow.isShowing()) {
            logger.info("Opened timetable window of week containing " + logic.getFocusDate());
            timetableWindow.show();
            timetableWindow.fillInnerParts();
        } else {
            timetableWindow.focus();
            timetableWindow.fillInnerParts();
        }
    }

    /**
     * Opens updated unscheduled jobs window
     */
    @FXML
    public void handleUnscheduledTimetable() {
        if (!unscheduleWindow.isShowing()) {
            logger.info("Opened window of unscheduled jobs.");
            try {
                CommandResult commandResult = logic.execute("timetable_unscheduled");
                resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            } catch (CommandException | ParseException e) {
                resultDisplay.setFeedbackToUser(e.getMessage());
            } catch (FileNotFoundException e) {
                resultDisplay.setFeedbackToUser(e.getMessage());
            }
            unscheduleWindow.show();
            unscheduleWindow.fillInnerParts();
        } else {
            unscheduleWindow.focus();
        }
    }

    /**
     * Opens updated completed jobs window
     */
    @FXML
    public void handleCompletedTimetable() {
        if (!completeWindow.isShowing()) {
            logger.info("Opened window of completed jobs.");
            try {
                CommandResult commandResult = logic.execute("timetable_completed");
                resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            } catch (CommandException | ParseException e) {
                resultDisplay.setFeedbackToUser(e.getMessage());
            } catch (FileNotFoundException | IllegalArgumentException e) {
                resultDisplay.setFeedbackToUser(e.getMessage());
            }
            completeWindow.show();
            completeWindow.fillInnerParts();
        } else {
            completeWindow.focus();
        }
    }

    /**
     * Opens Reminder List window.
     */
    @FXML
    public void handleReminderList() {
        if (!reminderListWindow.isShowing()) {
            reminderListWindow.show();
            reminderListWindow.fillInnerParts();
            logger.info("Opened reminder window.");
        } else {
            reminderListWindow.focus();
        }
    }

    /**
     * Opens Statistics window.
     */
    @FXML
    public void handleStats() {
        if (!statsWindow.isShowing()) {
            statsWindow.show();
            statsWindow.fillInnerParts();
            logger.info("Opened statistics window");
        } else {
            statsWindow.focus();
        }
    }

    /**
     * Opens Address book window.
     */
    @FXML
    public void handleAddressBook() {
        if (addDeliveryJobWindow != null) {
            addDeliveryJobWindow.hide();
        }

        if (!addressBookWindow.isShowing()) {
            addressBookWindow.show();
            addressBookWindow.fillInnerParts();
            logger.info("Opened address book window.");
        } else {
            addressBookWindow.focus();
        }
    }

    /**
     * Returns delivery job list panel
     */
    public DeliveryJobListPanel getDeliveryJobListPanel() {
        return deliveryJobListPanel;
    }

    /**
     * Handles create job ui.
     */
    @FXML
    private void handleDeliveryJobSystemCreateAction() {
        if (addDeliveryJobWindow != null) {
            addDeliveryJobWindow.hide();
        }
        addDeliveryJobWindow = new AddDeliveryJobWindow(new Stage(), logic);
        addDeliveryJobWindow.setResultHandler(commandResult -> {
            logger.info("[editDeliveryJobHandler] add complete.");
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
        });
        addDeliveryJobWindow.show();
        addDeliveryJobWindow.fillInnerParts();
    }

    /**
     * Handles mass import job ui.
     */
    @FXML
    private void handleDeliveryJobSystemImportAction() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Files");
            File selectedFile = fileChooser.showOpenDialog(new Stage());

            logic.execute(new ImportDeliveryJobCommand(selectedFile));
        } catch (ParseException | CommandException e) {
            logger.warning("[Event] importDeliveryJob" + e.getMessage());
        } catch (FileNotFoundException | IllegalArgumentException e) {
            logger.warning("[Event] importDeliveryJob" + e.getMessage());
        }
    }

    /**
     * Shows main window
     */
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
        timetableWindow.hide();
        unscheduleWindow.hide();
        completeWindow.hide();
        statsWindow.hide();
        reminderListWindow.hide();
        addressBookWindow.hide();
        if (addDeliveryJobWindow != null) {
            addDeliveryJobWindow.hide();
        }
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
            CommandResult commandResult = logic.execute(commandText, g -> !g.equals(CommandGroup.PERSON));
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isShowTimetable()) {
                if (commandResult.isShowTimetableDate()) {
                    openTimetable();
                } else {
                    handleTimetable();
                }
            }

            if (commandResult.isShowUnschedule()) {
                handleUnscheduledTimetable();
            }

            if (commandResult.isShowComplete()) {
                handleCompletedTimetable();
            }

            if (commandResult.isShowStatistics()) {
                handleStats();
            }

            if (commandResult.isShowReminderList()) {
                handleReminderList();
            }

            if (commandResult.isShowAddressBook()) {
                handleAddressBook();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException | IllegalArgumentException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        } catch (FileNotFoundException e) {
            logger.info("File not found: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}

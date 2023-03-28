package seedu.address.ui;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.jobs.CompleteDeliveryJobCommand;
import seedu.address.logic.commands.jobs.DeleteDeliveryJobCommand;
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
import seedu.address.ui.person.AddressBookWindow;
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
    // private PersonListPanel personListPanel;
    private AddressBookWindow addressBookWindow;
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
            logic.execute(new CompleteDeliveryJobCommand(job.getJobId(), !job.getDeliveredStatus()));
            refreshDeliveryJobDetailPane();
        } catch (ParseException | CommandException e) {
            logger.warning(e.getMessage());
        }
    };

    private Consumer<DeliveryJob> eidtDeliveryJobHandler = (job) -> {
        if (addDeliveryJobWindow != null) {
            addDeliveryJobWindow.getRoot().close();
        }
        addDeliveryJobWindow = new AddDeliveryJobWindow(new Stage(), logic, job, () -> {
            refreshDeliveryJobDetailPane();
        });
        addDeliveryJobWindow.show();
        addDeliveryJobWindow.fillInnerParts();
    };

    private BiConsumer<Integer, DeliveryJob> selectDeliveryJobHandler = (idx, job) -> {
        logger.info("[JobListView] select: " + idx);
        deliveryJobDetailPlaceholder.getChildren().clear();

        if (idx >= 0) {
            DeliveryJobDetailPane detailPane = new DeliveryJobDetailPane(job);
            detailPane.fillInnerParts(logic.getAddressBook());
            deliveryJobDetailPlaceholder.getChildren().add(detailPane.getRoot());
            detailPane.setCompleteHandler(completeDeliveryJobHandler);
            detailPane.setEditHandler(eidtDeliveryJobHandler);
            return;
        }

        emptyDeliveryJobListPanelPlaceholder.setVisible(true);
    };

    private Consumer<DeliveryJob> deleteDeliveryJobHandler = job -> {
        try {
            deliveryJobListPanel.selectPrevious();
            logic.execute(new DeleteDeliveryJobCommand(job.getJobId()));
        } catch (ParseException | CommandException e) {
            logger.warning(e.getMessage());
        }
    };

    private BiFunction<DeliverySortOption, Boolean, ObservableList<DeliveryJob>> sortDeliveryJobHandler = (by, asc) -> {
        switch (by) {
        case COM:
            logic.updateSortedDeliveryJobListByComparator(new SortbyDelivered(asc));
            break;
        case EARN:
            logic.updateSortedDeliveryJobListByComparator(new SortbyEarning(asc));
            break;
        default:
            logic.updateSortedDeliveryJobListByComparator(new SortbyDate(asc));
            break;
        }
        return logic.getSortedDeliveryJobList();
    };

    private Consumer<DeliveryFilterOption> filterDeliveryJobHandler = by -> {
        switch (by) {
        case COM:
            logic.updateFilteredDeliveryJobList(job -> job.getDeliveredStatus());
            break;
        case PEN:
            logic.updateFilteredDeliveryJobList(job -> !job.getDeliveredStatus());
            break;
        default:
            logic.updateFilteredDeliveryJobList(job -> true);
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
        timetableWindow = new TimetableWindow(new Stage(), logic);
        unscheduleWindow = new UnscheduleWindow(new Stage(), logic);
        completeWindow = new CompleteWindow(new Stage(), logic);
        reminderListWindow = new ReminderListWindow(new Stage(), logic);
        statsWindow = new StatisticsWindow(new Stage(), logic);
        addressBookWindow = new AddressBookWindow(new Stage(), logic);
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
        logger.info("[JobListView] Refresh List: " + deliveryJobListPanel.size());
        if (deliveryJobListPanel.size() > 0) {
            emptyDeliveryJobListPanelPlaceholder.setVisible(false);
        } else {
            deliveryJobDetailPlaceholder.getChildren().clear();
            emptyDeliveryJobListPanelPlaceholder.setVisible(true);
        }
    }

    private void refreshDeliveryJobDetailPane() {
        logger.info("[JobListView] Refresh Detail");
        deliveryJobListPanel.refresh();
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        // Append views
        deliveryJobListPanel = new DeliveryJobListPanel(logic.getFilteredDeliveryJobList(), selectDeliveryJobHandler,
                completeDeliveryJobHandler,
                deleteDeliveryJobHandler);
        deliveryJobListPanel.setOrderByHandler(sortDeliveryJobHandler);
        deliveryJobListPanel.setFilterHandler(filterDeliveryJobHandler);
        deliveryJobListPanelPlaceholder.getChildren().add(deliveryJobListPanel.getRoot());
        deliveryJobListPanel.selectItem(0);

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getDeliveryJobSystemFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        // Attach listeners
        logic.getFilteredDeliveryJobList().addListener(new ListChangeListener<DeliveryJob>() {
            @Override
            public void onChanged(Change<? extends DeliveryJob> c) {
                refreshDeliveryJobListView();
            }
        });
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
            logger.info("Opened help window.");
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Opens Timetable window.
     */
    @FXML
    private void handleTimetable() {
        if (!timetableWindow.isShowing()) {
            logger.info("Opened timetable window of current week.");
            timetableWindow.show();
            timetableWindow.fillInnerParts();
        } else {
            timetableWindow.focus();
        }
    }

    /**
     * Opends unscheduled jobs window
     */
    @FXML
    private void handleUnscheduledTimetable() {
        if (!unscheduleWindow.isShowing()) {
            logger.info("Opened window of unscheduled jobs.");
            unscheduleWindow.show();
            unscheduleWindow.fillInnerParts();
        } else {
            unscheduleWindow.focus();
        }
    }

    /**
     * Opends completed jobs window
     */
    @FXML
    private void handleCompletedTimetable() {
        if (!completeWindow.isShowing()) {
            logger.info("Opened window of completed jobs.");
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
    private void handleReminderList() {
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
    private void handleStats() {
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
    private void handleAddressBook() {
        if (addDeliveryJobWindow != null) {
            addDeliveryJobWindow.getRoot().close();
        }

        if (!addressBookWindow.isShowing()) {
            addressBookWindow.show();
            addressBookWindow.fillInnerParts();
            logger.info("Opened address book window.");
        } else {
            addressBookWindow.focus();
        }
    }

    public DeliveryJobListPanel getDeliveryJobListPanel() {
        return deliveryJobListPanel;
    }

    /**
     * Handles create job ui.
     */
    @FXML
    private void handleDeliveryJobSystemCreateAction() {
        addDeliveryJobWindow = new AddDeliveryJobWindow(new Stage(), logic);
        addDeliveryJobWindow.show();
        addDeliveryJobWindow.fillInnerParts();
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
        timetableWindow.hide();
        unscheduleWindow.hide();
        statsWindow.hide();
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
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isShowTimetable()) {
                handleTimetable();
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

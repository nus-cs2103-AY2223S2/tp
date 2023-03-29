package seedu.fitbook.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.fitbook.commons.core.GuiSettings;
import seedu.fitbook.commons.core.LogsCenter;
import seedu.fitbook.logic.Logic;
import seedu.fitbook.logic.commands.CommandResult;
import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.logic.parser.exceptions.ParseException;
import seedu.fitbook.model.client.Client;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {
    private static final String TITLE = "FitBook";
    private static final String EXERCISE = "Exercise";
    private static final String SCHEDULE = "Schedule";
    private static final String STATISTIC = "Statistic";
    private static final String SUMMARY = "Summary";
    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    @FXML
    private Label mainTitle;

    @FXML
    private Label subTitle;

    @FXML
    private AnchorPane pagePane;

    // Independent Ui parts residing in this Ui container
    private ClientListPanel clientListPanel;
    private SchedulePanel schedulePanel;
    private ExercisePanel exercisePanel;
    private ExerciseListPanel exerciseListPanel;
    private SummaryPanel summaryPanel;
    private SummaryListPanel summaryListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private int tabNumber = 0;
    @FXML
    private Label exercisePanelTitle;

    @FXML
    private HBox exerciseHolder;
    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;
    @FXML
    private MenuItem exerciseMenuItem;

    @FXML
    private StackPane clientListPanelPlaceholder;
    @FXML
    private StackPane rightPanelPlaceholder;

    @FXML
    private StackPane leftPanelPlaceholder;


    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private Pane schedulePanelTitleHolder;

    @FXML
    private Pane exercisePanelTitleHolder;

    @FXML
    private Label scheduleListListPanelTitle;
    @FXML
    private Label exerciseListListPanelTitle;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private LineChart<String, Number> lineChart;
    private XYChart.Series<String, Number> series;
    private Scene scene;
    private Pane pane;

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
        clientListPanel = new ClientListPanel(logic.getFilteredClientList());
        leftPanelPlaceholder.getChildren().add(clientListPanel.getRoot());

        schedulePanel = new SchedulePanel(logic.getFilteredClientList());
        rightPanelPlaceholder.getChildren().add(schedulePanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getFitBookFilePath());
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

    private void setTabNumber(int tabNumber) {
        this.tabNumber = tabNumber;
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

    private void setMainTitleText(String text) {
        mainTitle.setText(text);
    }

    private void setSubTitle(String text) {
        subTitle.setText(text);
    }
    @FXML
    private void handleExercise() {
        setMainTitleText(EXERCISE);
        setSubTitle(EXERCISE);
        rightPanelPlaceholder.setManaged(false);
        leftPanelPlaceholder.setManaged(false);

        exercisePanel = new ExercisePanel(logic.getFilteredRoutineList());
        rightPanelPlaceholder.getChildren().add(exercisePanel.getRoot());

        exerciseListPanel = new ExerciseListPanel(logic.getFilteredClientList());
        leftPanelPlaceholder.getChildren().add(exerciseListPanel.getRoot());

        rightPanelPlaceholder.setManaged(true);
        leftPanelPlaceholder.setManaged(true);
    }

    @FXML
    private void handleStatistics(Client client) {
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        lineChart = new LineChart<String, Number>(xAxis, yAxis);
        series = new XYChart.Series<String, Number>();

        xAxis.setLabel("Day");
        yAxis.setLabel("Weights");
        updateSeries(client);

        pane = new Pane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(lineChart);
        scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void updateSeries(Client client) {
        series.getData().clear();
        List<String> graphXAxis = new ArrayList<>();
        List<Integer> graphYAxis = new ArrayList<>();

        for (int i = 0; i < client.getWeightHistory().getListSize(); i++) {
            int weightValue = Integer.parseInt(client.getWeightHistory().getWeightValue(i));
            graphYAxis.add(weightValue);
        }


        for (int i = 0; i < client.getWeightHistory().getListSize(); i++) {
            String dateValue = client.getWeightHistory().getDateValue(i).toString();
            graphXAxis.add(dateValue);
        }

        // add data to the series
        for (int i = 0; i < graphXAxis.size(); i++) {
            series.getData().add(new XYChart.Data(graphXAxis.get(i), graphYAxis.get(i)));
            System.out.println(graphYAxis.get(i));
        }
        series.setName("Weight History");
        lineChart.getData().add(series);
    }

    @FXML
    private void handleSummary() {
        setMainTitleText(SUMMARY);
        setSubTitle(SUMMARY);
        rightPanelPlaceholder.setManaged(false);
        leftPanelPlaceholder.setManaged(false);

        ObservableList<Client> list = FXCollections.observableArrayList();
        summaryPanel = new SummaryPanel(list);
        rightPanelPlaceholder.getChildren().add(summaryPanel.getRoot());

        summaryListPanel = new SummaryListPanel(logic.getFilteredClientList());
        leftPanelPlaceholder.getChildren().add(summaryListPanel.getRoot());


        rightPanelPlaceholder.setManaged(true);
        leftPanelPlaceholder.setManaged(true);
    }

    @FXML
    private void handleSummaryCommand(Client clientToView) {
        setMainTitleText(SUMMARY);
        setSubTitle(SUMMARY);
        rightPanelPlaceholder.setManaged(false);
        leftPanelPlaceholder.setManaged(false);

        summaryListPanel = new SummaryListPanel(logic.getFilteredClientList());
        leftPanelPlaceholder.getChildren().add(summaryListPanel.getRoot());

        ObservableList<Client> list = FXCollections.observableArrayList(clientToView);
        summaryPanel = new SummaryPanel(list);
        rightPanelPlaceholder.getChildren().add(summaryPanel.getRoot());

        rightPanelPlaceholder.setManaged(true);
        leftPanelPlaceholder.setManaged(true);
    }
    @FXML
    private void handleSchedule() {
        setMainTitleText(TITLE);
        setSubTitle(SCHEDULE);

        rightPanelPlaceholder.setManaged(false);
        leftPanelPlaceholder.setManaged(false);

        clientListPanel = new ClientListPanel(logic.getFilteredClientList());
        leftPanelPlaceholder.getChildren().add(clientListPanel.getRoot());

        schedulePanel = new SchedulePanel(logic.getFilteredClientList());
        rightPanelPlaceholder.getChildren().add(schedulePanel.getRoot());


        rightPanelPlaceholder.setManaged(true);
        leftPanelPlaceholder.setManaged(true);

    }

    private void handleCommand(CommandResult commandResult) {
        if (commandResult.equals("view")) {
            summaryPanel = new SummaryPanel(logic.getFilteredClientList());
            rightPanelPlaceholder.getChildren().add(summaryPanel.getRoot());
        }
    }

    public ClientListPanel getClientListPanel() {
        return clientListPanel;
    }
    public ExercisePanel getExercisePanel() {
        return exercisePanel;
    }
    public SchedulePanel getSchedulePanel() {
        return schedulePanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.fitbook.logic.Logic#execute(String)
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
            if (commandResult.isView()) {
                handleSummaryCommand(commandResult.getClientToView());
            }
            if (commandResult.isShowRoutine()) {
                handleExercise();
            }
            if (commandResult.isShowGraph()) {
                handleStatistics(commandResult.getClientToView());
            }
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}

package seedu.address.ui;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.TaskType;
import seedu.address.ui.popups.PopupAddInternship;
import seedu.address.ui.popups.PopupEditInternship;
import seedu.address.ui.task.note.NoteListPanel;
import seedu.address.ui.task.todo.TodoListPanel;

/**
 * The Main Window. Provides the basic application layout where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ApplicationListPanel applicationListPanel;
    private QuickAccessToolbar quickAccessToolbar;
    private HelpWindow helpWindow;
    private TodoListPanel todoListPanel;
    private NoteListPanel noteListPanel;
    private ViewContentPanel viewContentPanel;
    private StatsInformationListPanel statsInformationListPanel;
    private MixedPanel mixedPanel;
    private CommandBox commandBox;
    private ReminderWindow reminderWindow;
    private PopupAddInternship popupAddInternship;
    private ArrayList<PopupEditInternship> popupEditInternships = new ArrayList<>();

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private StackPane applicationListPanelPlaceholder;

    @FXML
    private StackPane viewContentPanelPlaceholder;

    @FXML
    private StackPane statsInformationListPanelPlaceholder;

    @FXML
    private StackPane quickAccessToolbarPlaceholder;

    @FXML
    private VBox mainContainer;

    @FXML
    private GridPane headerGridPane;

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
        quickAccessToolbar = new QuickAccessToolbar(this::executeCommand);
        quickAccessToolbarPlaceholder.getChildren().add(quickAccessToolbar.getRoot());

        reminderWindow = new ReminderWindow(new Stage(), logic.getReminderApplication(), this);

        helpWindow = new HelpWindow();
        headerGridPane.maxWidthProperty().bind(primaryStage.widthProperty());
        commandBoxPlaceholder.maxWidthProperty().bind(primaryStage.widthProperty());
    }

    /**
     * Getter for primary stage.
     *
     * @return current primary stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        commandBox.focusCommandPrompt();
        viewContentPanel = new ViewContentPanel();
        viewContentPanelPlaceholder.getChildren().add(viewContentPanel.getRoot());

        // Initialise statsInformationListPanel
        statsInformationListPanel = new StatsInformationListPanel(logic.getStatsManager());
        statsInformationListPanelPlaceholder.getChildren().add(statsInformationListPanel.getRoot());

        applicationListPanel = new ApplicationListPanel(logic.getSortedFilteredInternshipList(), this,
                viewContentPanel);
        todoListPanel = new TodoListPanel(logic.getFilteredTodoList(), viewContentPanel);
        noteListPanel = new NoteListPanel(logic.getFilteredNoteList(), viewContentPanel);
        mixedPanel = new MixedPanel(logic.getFilteredTodoList(), logic.getFilteredNoteList(), viewContentPanel);
        applicationListPanelPlaceholder.getChildren().addAll(todoListPanel.getRoot(), noteListPanel.getRoot(),
                mixedPanel.getRoot(), applicationListPanel.getRoot());

        setHeightConstraints();
    }

    private void setHeightConstraints() {
        applicationListPanel.getContainer().maxHeightProperty().bind(primaryStage.heightProperty().multiply(0.9));
        todoListPanel.getContainer().maxHeightProperty().bind(primaryStage.heightProperty().multiply(0.9));
        noteListPanel.getContainer().maxHeightProperty().bind(primaryStage.heightProperty().multiply(0.9));
        mixedPanel.getContainer().maxHeightProperty().bind(primaryStage.heightProperty().multiply(0.9));

        viewContentPanel.getContainer().maxHeightProperty().bind(
                primaryStage.heightProperty().multiply(0.75 * 0.73));
        viewContentPanel.getContainer().prefHeightProperty().bind(
                primaryStage.heightProperty().multiply(0.75 * 0.73));
        statsInformationListPanel.getContainer().maxHeightProperty().bind(
                primaryStage.heightProperty().multiply(0.75 * 0.22));
        statsInformationListPanel.getContainer().prefHeightProperty().bind(
                primaryStage.heightProperty().multiply(0.75 * 0.22));
    }

    private void changePanelPlaceholder(MainWindow m, TaskType type) {
        m.getApplicationListPanel().getRoot().setVisible(type == TaskType.NONE);
        m.getTodoListPanel().getRoot().setVisible(type == TaskType.TODO);
        m.getNoteListPanel().getRoot().setVisible(type == TaskType.NOTE);
        m.getMixedPanel().getRoot().setVisible(type == TaskType.BOTH);
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

    /**
     * Opens the reminder window or focuses on it if it's already opened.
     */
    @FXML
    public void handleReminder() {
        if (!reminderWindow.isShowing()) {
            reminderWindow.show();
        } else {
            reminderWindow.focus();
        }
    }

    /**
     * Shows the primary stage.
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
        reminderWindow.hide();
        primaryStage.hide();
        popupAddInternship.hide();
        for (PopupEditInternship e : popupEditInternships) {
            e.hide();
        }
    }

    /**
     * Getter for application list panel.
     *
     * @return application list panel
     */
    public ApplicationListPanel getApplicationListPanel() {
        return applicationListPanel;
    }

    /**
     * Getter for todo list panel.
     *
     * @return todo list panel
     */
    public TodoListPanel getTodoListPanel() {
        return todoListPanel;
    }

    /**
     * Getter for note list panel.
     *
     * @return note list panel
     */
    public NoteListPanel getNoteListPanel() {
        return noteListPanel;
    }

    /**
     * Getter for mix panel.
     *
     * @return person list panel
     */
    public MixedPanel getMixedPanel() {
        return mixedPanel;
    }

    /**
     * Set add internship popup
     */
    public void setPopupAddInternship(PopupAddInternship popupAddInternship) {
        this.popupAddInternship = popupAddInternship;
    }

    /**
     * Add edit internship popup into the array
     */
    public void setPopupEditInternships(PopupEditInternship popupEditInternship) {
        this.popupEditInternships.add(popupEditInternship);
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    public CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            changePanelPlaceholder(this, commandResult.getType());
            commandBox.clearCommandTextField();
            ResultDialog.displayResultDialog(commandResult.getFeedbackToUser(), primaryStage);
            reminderWindow = new ReminderWindow(new Stage(), logic.getReminderApplication(), this);
            statsInformationListPanel.updateDisplay();

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isRemind()) {
                handleReminder();
            }
            commandBox.focusCommandPrompt();
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            ResultDialog.displayResultDialog(e.getMessage(), primaryStage);
            throw e;
        }
    }
}

package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.Mnemonic;
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
import seedu.address.model.tag.TodoType;
import seedu.address.ui.task.note.NoteListPanel;
import seedu.address.ui.task.todo.TodoListPanel;

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
    private ApplicationListPanel applicationListPanel;
    private QuickAccessToolbar quickAccessToolbar;
    private HelpWindow helpWindow;
    private TodoListPanel todoListPanel;
    private NoteListPanel noteListPanel;
    private ViewContentPanel viewContentPanel;
    private SummaryPanel summaryPanel;
    private MixedPanel mixedPanel;
    private CommandBox commandBox;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private StackPane applicationListPanelPlaceholder;

    @FXML
    private StackPane viewContentPanelPlaceholder;

    @FXML
    private StackPane summaryPanelPlaceholder;

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

        setAccelerators();
        helpWindow = new HelpWindow();
        headerGridPane.maxWidthProperty().bind(primaryStage.widthProperty());
        commandBoxPlaceholder.maxWidthProperty().bind(primaryStage.widthProperty());
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


    private void setAccelerators() {
        setAccelerator(quickAccessToolbar.getHelpButton(), KeyCombination.valueOf("F1"));
    }



    /**
     * Sets the accelerator of a button.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(Button helpButton, KeyCombination keyCombination) {
        Mnemonic m = new Mnemonic(helpButton, keyCombination);
        primaryStage.getScene().addMnemonic(m);

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
                helpButton.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        viewContentPanel = new ViewContentPanel();
        viewContentPanelPlaceholder.getChildren().add(viewContentPanel.getRoot());

        applicationListPanel = new ApplicationListPanel(logic.getFilteredInternshipList(), viewContentPanel);
        todoListPanel = new TodoListPanel(logic.getFilteredTodoList(), viewContentPanel);
        noteListPanel = new NoteListPanel(logic.getFilteredNoteList(), viewContentPanel);
        mixedPanel = new MixedPanel(logic.getFilteredTodoList(), logic.getFilteredNoteList(), viewContentPanel);
        applicationListPanelPlaceholder.getChildren().addAll(todoListPanel.getRoot(), noteListPanel.getRoot(),
                mixedPanel.getRoot(), applicationListPanel.getRoot());

        summaryPanel = new SummaryPanel();
        summaryPanelPlaceholder.getChildren().add(summaryPanel.getRoot());

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
        summaryPanel.getContainer().maxHeightProperty().bind(
                primaryStage.heightProperty().multiply(0.75 * 0.22));
        summaryPanel.getContainer().prefHeightProperty().bind(
                primaryStage.heightProperty().multiply(0.75 * 0.22));
    }

    private void changePanelPlaceholder(MainWindow m, TodoType type) {
        m.getApplicationListPanel().getRoot().setVisible(type == TodoType.NONE);
        m.getTodoListPanel().getRoot().setVisible(type == TodoType.TODO);
        m.getNoteListPanel().getRoot().setVisible(type == TodoType.NOTE);
        m.getMixedPanel().getRoot().setVisible(type == TodoType.BOTH);
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
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public ApplicationListPanel getApplicationListPanel() {
        return applicationListPanel;
    }

    public TodoListPanel getTodoListPanel() {
        return todoListPanel;
    }

    public NoteListPanel getNoteListPanel() {
        return noteListPanel;
    }

    public MixedPanel getMixedPanel() {
        return mixedPanel;
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
            changePanelPlaceholder(this, commandResult.getType());
            commandBox.clearCommandTextField();
            ResultDialog.displayResultDialog(commandResult.getFeedbackToUser(), primaryStage);

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            ResultDialog.displayResultDialog(e.getMessage(), primaryStage);
            throw e;
        }
    }
}

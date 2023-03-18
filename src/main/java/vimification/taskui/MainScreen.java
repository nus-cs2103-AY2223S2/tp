package vimification.taskui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import vimification.logic.Logic;
import vimification.model.task.Task;

/**
 * The Main Scene. Provides the basic application layout containing a menu bar and space where other
 * JavaFX elements can be placed.
 */
public class MainScreen extends UiPart<VBox> {

    private static final String FXML = "MainScreen.fxml";

    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private TaskListPanel taskListPanel;
    private TaskCreationPanel taskCreationPanel;
    private CommandInput commandInput;

    @FXML
    private VBox leftComponent;

    @FXML
    private VBox rightComponent;

    @FXML
    private VBox commandInputComponent;

    /**
     * Creates a {@code MainWindow} with {@code Logic}.
     */
    public MainScreen(Logic logic) {
        super(FXML);
        this.logic = logic;
        setup();
    }

    @FXML
    private void initialize() {
        this.getRoot().setFocusTraversable(true); // Important
    }

    private void setup() {
        intializeCommandInput();
        initializeTaskListPanel();
    }

    private void initializeTaskListPanel() {
        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        taskListPanel.setMainScreen(this);
        leftComponent.getChildren().add(taskListPanel.getRoot());
    }

    private void intializeCommandInput() {
        commandInput = new CommandInput(this.getRoot());
        commandInputComponent.getChildren().add(commandInput.getRoot());
    }

    /**
     * Listener for handling all keyboard events to Vimification.
     *
     * @param event
     */
    @FXML
    private void handleKeyPressed(KeyEvent event) {
        KeyCodeCombination colonKey =
                new KeyCodeCombination(KeyCode.SEMICOLON, KeyCombination.SHIFT_DOWN);
        boolean isKeyPressedColon = colonKey.match(event);

        if (isKeyPressedColon) {
            loadCommandInputComponent();
            return;
        }

        switch (event.getText()) {
        case "i":
            loadTaskCreationPanelComponent();
            break;
        }
    }

    private void loadTaskCreationPanelComponent() {
        taskCreationPanel = new TaskCreationPanel(this.getRoot());
        loadRightComponent(taskCreationPanel);
        taskCreationPanel.requestFocus();
    }

    private void loadCommandInputComponent() {
        commandInput.setVisible(true);
        commandInput.requestFocus();
    }

    public void loadDetailedTaskComponent(Task task) {
        TaskDetailPanel detailTask = new TaskDetailPanel(task);
        loadRightComponent(detailTask);
    }

    private <T extends Node> void loadRightComponent(UiPart<T> component) {
        rightComponent.getChildren().clear();
        rightComponent.getChildren().add(component.getRoot());
    }
}

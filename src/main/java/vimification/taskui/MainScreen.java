package vimification.taskui;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import vimification.logic.Logic;
// import vimification.ui.PersonListPanel;

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
        init();
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
            handleCommand();
            return;
        }

        switch (event.getText()) {
        case "i":
            System.out.println("You've created a task!");
            handleTaskCreation();
            break;
        case "d":
            System.out.println("You've deleted a task!");
            break;
        case "h":
            System.out.println("You've moved to the left");
            break;
        case "l":
            System.out.println("You've moved to the right");
            break;
        case "j":
            System.out.println("You've moved up");
            break;
        case "k":
            System.out.println("You've moved down");
            break;
        }
    }

    private void handleTaskCreation() {
        taskCreationPanel = new TaskCreationPanel(this.getRoot());
        rightComponent.getChildren().clear();
        rightComponent.getChildren().add(taskCreationPanel.getRoot());
        taskCreationPanel.requestFocus();
    }

    private void handleCommand() {
        commandInput.setVisible(true);
        commandInput.requestFocus();
    }

    private void init() {
        this.getRoot().setFocusTraversable(true); // Important

        intializeCommandInput();
        initializeTaskListPanel();
    }

    private void initializeTaskListPanel() {
        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        leftComponent.getChildren().add(taskListPanel.getRoot());
    }

    private void intializeCommandInput() {
        commandInput = new CommandInput(this.getRoot());
        commandInputComponent.getChildren().add(commandInput.getRoot());
    }
}

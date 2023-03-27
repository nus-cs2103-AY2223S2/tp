package vimification.taskui;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vimification.internal.Logic;
import vimification.model.task.Task;

/**
 * The Main Scene. Provides the basic application layout containing a menu bar and space where other
 * JavaFX elements can be placed.
 */
public class MainScreen extends UiPart<VBox> {

    private static final String FXML = "MainScreen.fxml";

    private static ReadOnlyDoubleProperty stageHeight;
    private static DoubleBinding topComponentHeight; // Height of left and right component
    private static DoubleBinding bottomComponentHeight;
    private static ReadOnlyDoubleProperty windowWidth;

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
    private HBox bottomComponent;

    /**
     * Creates a {@code MainWindow} with {@code Logic}.
     */
    public MainScreen(Logic logic, Stage stage) {
        super(FXML);
        this.logic = logic;
        stageHeight = stage.heightProperty();
        windowWidth = stage.widthProperty();

        topComponentHeight = stageHeight.multiply(0.9);
        bottomComponentHeight = stageHeight.multiply(0.1);
        setup();
    }

    public TaskListPanel getTaskListPanel() {
        return this.taskListPanel;
    }

    @FXML
    private void initialize() {
        this.getRoot().setFocusTraversable(true); // Important
    }

    private void setup() {
        leftComponent.prefHeightProperty().bind(topComponentHeight);
        rightComponent.prefHeightProperty().bind(topComponentHeight);
        bottomComponent.prefHeightProperty().bind(bottomComponentHeight);

        intializeCommandInput();
        initializeTaskListPanel();
    }

    public void initializeTaskListPanel() {
        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        taskListPanel.setMainScreen(this);
        loadLeftComponent(taskListPanel);
    }

    private void intializeCommandInput() {
        commandInput = new CommandInput(this, logic);
        // commandInput.getRoot().prefHeightProperty().bind(stageHeight.multiply(0.1));
        // bottomComponent.getChildren().add(commandInput.getRoot());
        // loadBottomComponent(commandInput);
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
        case "j":
        case "k":
            taskListPanel.requestFocus();
            break;
        }
    }

    private void loadTaskCreationPanelComponent() {
        taskCreationPanel = new TaskCreationPanel(this.getRoot());
        loadRightComponent(taskCreationPanel);
        taskCreationPanel.requestFocus();
    }

    private void loadCommandInputComponent() {
        loadBottomComponent(commandInput);
        // commandInput.setVisible(true);
        commandInput.requestFocus();
    }

    public void loadCommandResultComponent(CommandResult resultComponent) {
        loadBottomComponent(resultComponent);
    }

    public void loadDetailedTaskComponent(Task task) {
        TaskDetailPanel detailTask = new TaskDetailPanel(task);
        loadRightComponent(detailTask);
    }

    /**
     * Clears the right component.
     */
    public void clearRightComponent() {
        rightComponent.getChildren().clear();
    }

    public void clearBottomComponent() {
        bottomComponent.getChildren().clear();
    }

    private <T extends Pane> void loadLeftComponent(UiPart<T> component) {
        leftComponent.getChildren().clear();
        leftComponent.getChildren().add(component.getRoot());
        component.getRoot().prefHeightProperty().bind(topComponentHeight);
    }

    private <T extends Pane> void loadRightComponent(UiPart<T> component) {
        rightComponent.getChildren().clear();
        rightComponent.getChildren().add(component.getRoot());
        component.getRoot().prefHeightProperty().bind(topComponentHeight);
    }

    private <T extends Pane> void loadBottomComponent(UiPart<T> component) {
        bottomComponent.getChildren().clear();
        bottomComponent.getChildren().add(component.getRoot());
        component.getRoot().prefHeightProperty().bind(bottomComponentHeight);
        component.getRoot().prefWidthProperty().bind(windowWidth);
    }
}

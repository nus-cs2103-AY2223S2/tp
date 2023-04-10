package vimification.ui;

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
import vimification.internal.Logic;
import vimification.internal.command.CommandResult;

/**
 * The Main Scene. Provides the basic application layout containing a menu bar and space where other
 * JavaFX elements can be placed.
 */
public class MainScreen extends UiPart<VBox> {

    private static final String FXML = "MainScreen.fxml";

    private static ReadOnlyDoubleProperty WINDOW_HEIGHT;
    private static ReadOnlyDoubleProperty WINDOW_WIDTH;

    private static final double TOP_COMPONENT_HEIGHT_PROPORTION = 0.9;
    private static final double BOTTOM_COMPONENT_HEIGHT_PROPORTION =
            1 - TOP_COMPONENT_HEIGHT_PROPORTION;

    private static final double LEFT_COMPONENT_WIDTH_PROPORTION = 0.4;
    private static final double RIGHT_COMPONENT_WIDTH_PROPORTION =
            1 - LEFT_COMPONENT_WIDTH_PROPORTION;

    private static DoubleBinding TOP_COMPONENT_HEIGHT;// Height of left and right
                                                      // component
    private static DoubleBinding BOTTOM_COMPONENT_HEIGHT;
    private static DoubleBinding LEFT_COMPONENT_WIDTH;
    private static DoubleBinding RIGHT_COMPONENT_WIDTH;

    private Logic logic;

    private TaskListPanel taskListPanel;

    @FXML
    private VBox leftComponent;
    @FXML
    private VBox rightComponent;
    @FXML
    private HBox bottomComponent;

    /**
     * Creates a {@code MainWindow} with {@code Logic}.
     */
    public MainScreen(Logic logic) {
        super(FXML);
        this.logic = logic;
        WINDOW_HEIGHT = this.getRoot().heightProperty();
        WINDOW_WIDTH = this.getRoot().widthProperty();

        bindHeightAndWidth();
        setupComponents();
    }


    public TaskListPanel getTaskListPanel() {
        return taskListPanel;
    }

    @FXML
    private void initialize() {
        this.getRoot().setFocusTraversable(true); // Important
    }

    /**
     * Initialize the components of the main screen.
     */
    private void setupComponents() {
        initializeTaskListPanel();
        loadRightComponent(new WelcomePanel());
    }

    /**
     * Bind the height and width of the components to the window size.
     */
    private void bindHeightAndWidth() {
        TOP_COMPONENT_HEIGHT = WINDOW_HEIGHT.multiply(TOP_COMPONENT_HEIGHT_PROPORTION);
        BOTTOM_COMPONENT_HEIGHT = WINDOW_HEIGHT.multiply(BOTTOM_COMPONENT_HEIGHT_PROPORTION);
        LEFT_COMPONENT_WIDTH = WINDOW_WIDTH.multiply(LEFT_COMPONENT_WIDTH_PROPORTION);
        RIGHT_COMPONENT_WIDTH = WINDOW_WIDTH.multiply(RIGHT_COMPONENT_WIDTH_PROPORTION);

        leftComponent.prefHeightProperty().bind(TOP_COMPONENT_HEIGHT);
        rightComponent.prefHeightProperty().bind(TOP_COMPONENT_HEIGHT);

        bottomComponent.prefHeightProperty().bind(BOTTOM_COMPONENT_HEIGHT);
    }

    /**
     * Initialize the task list panel.
     */
    public void initializeTaskListPanel() {
        taskListPanel = new TaskListPanel(logic.getUiTaskList(), this);
        loadLeftComponent(taskListPanel);
        taskListPanel.requestFocus();
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
        case "j":
        case "k":
            taskListPanel.requestFocus();
            break;
        }
    }

    /**
     * Loads the command input component into the bottom component.
     */
    private void loadCommandInputComponent() {
        CommandInput commandInput = new CommandInput(this, logic);
        loadBottomComponent(commandInput);
        commandInput.requestFocus();
    }

    /**
     * Loads the result of the command into the right component.
     *
     * @param result
     */
    protected void loadCommandResultComponent(CommandResult result) {
        CommandResultPanel resultPanel = new CommandResultPanel(this);
        resultPanel.display(result);
    }

    /**
     * Clears the right component.
     */
    public void clearRightComponent() {
        rightComponent.getChildren().clear();
    }

    /**
     * Clears the bottom component.
     */
    public void clearBottomComponent() {
        bottomComponent.getChildren().clear();
    }

    /**
     * Loads the given component into the left component.
     *
     * @param component
     */
    private <T extends Pane> void loadLeftComponent(UiPart<T> component) {
        leftComponent.getChildren().clear();
        leftComponent.getChildren().add(component.getRoot());
        component.getRoot().prefHeightProperty().bind(TOP_COMPONENT_HEIGHT);
        component.getRoot().prefWidthProperty().bind(LEFT_COMPONENT_WIDTH);
    }

    /**
     * Loads the given component into the right component.
     *
     * @param component
     */
    public <T extends Pane> void loadRightComponent(UiPart<T> component) {
        rightComponent.getChildren().clear();
        rightComponent.getChildren().add(component.getRoot());
        component.getRoot().prefHeightProperty().bind(TOP_COMPONENT_HEIGHT);
        component.getRoot().prefWidthProperty().bind(RIGHT_COMPONENT_WIDTH);
    }

    /**
     * Loads the given component into the bottom component.
     *
     * @param component
     */
    public <T extends Pane> void loadBottomComponent(UiPart<T> component) {
        bottomComponent.getChildren().clear();
        bottomComponent.getChildren().add(component.getRoot());
        component.getRoot().prefHeightProperty().bind(BOTTOM_COMPONENT_HEIGHT);
        component.getRoot().prefWidthProperty().bind(WINDOW_WIDTH);
    }

    public Logic getLogic() {
        return this.logic;
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
    }

    public void setTaskListPanel(TaskListPanel taskListPanel) {
        this.taskListPanel = taskListPanel;
    }

    public VBox getLeftComponent() {
        return this.leftComponent;
    }

    public void setLeftComponent(VBox leftComponent) {
        this.leftComponent = leftComponent;
    }

    public VBox getRightComponent() {
        return this.rightComponent;
    }

    public void setRightComponent(VBox rightComponent) {
        this.rightComponent = rightComponent;
    }

    public HBox getBottomComponent() {
        return this.bottomComponent;
    }

    public void setBottomComponent(HBox bottomComponent) {
        this.bottomComponent = bottomComponent;
    }

}

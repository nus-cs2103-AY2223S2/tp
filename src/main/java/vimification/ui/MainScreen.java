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

    // Independent Ui parts residing in this Ui container
    private TaskTabPanel taskTabPanel;
    // private TaskCreationPanel taskCreationPanel;
    private CommandInput commandInput;

    @FXML
    private VBox leftComponent;

    @FXML
    private VBox rightComponent;

    @FXML
    protected HBox bottomComponent;

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

    /**
     * Returns the {@code TaskTabPanel} component
     *
     * @return
     */
    public TaskTabPanel getTaskTabPanel() {
        return taskTabPanel;
    }

    @FXML
    private void initialize() {
        this.getRoot().setFocusTraversable(true); // Important
    }

    private void setupComponents() {
        loadRightComponent(new WelcomePanel());
        intializeCommandInput();
        initializeTaskTabPanel();
    }

    private void bindHeightAndWidth() {
        TOP_COMPONENT_HEIGHT = WINDOW_HEIGHT.multiply(TOP_COMPONENT_HEIGHT_PROPORTION);
        BOTTOM_COMPONENT_HEIGHT = WINDOW_HEIGHT.multiply(BOTTOM_COMPONENT_HEIGHT_PROPORTION);
        LEFT_COMPONENT_WIDTH = WINDOW_WIDTH.multiply(LEFT_COMPONENT_WIDTH_PROPORTION);
        RIGHT_COMPONENT_WIDTH = WINDOW_WIDTH.multiply(RIGHT_COMPONENT_WIDTH_PROPORTION);

        leftComponent.prefHeightProperty().bind(TOP_COMPONENT_HEIGHT);
        rightComponent.prefHeightProperty().bind(TOP_COMPONENT_HEIGHT);

        bottomComponent.prefHeightProperty().bind(BOTTOM_COMPONENT_HEIGHT);
    }

    public void initializeTaskTabPanel() {
        taskTabPanel = new TaskTabPanel(this, logic);
        loadLeftComponent(taskTabPanel);
        taskTabPanel.requestFocus();
    }

    private void intializeCommandInput() {
        commandInput = new CommandInput(this, logic);
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

        KeyCodeCombination macControlTab =
                new KeyCodeCombination(KeyCode.TAB, KeyCodeCombination.CONTROL_DOWN);

        KeyCodeCombination windowsControlTab =
                new KeyCodeCombination(KeyCode.TAB, KeyCodeCombination.ALT_DOWN);


        boolean isKeyPressedColon = colonKey.match(event);
        boolean isContorlTab = macControlTab.match(event) || windowsControlTab.match(event);

        if (isKeyPressedColon) {
            loadCommandInputComponent();
            return;
        }
        if (isContorlTab) {
            taskTabPanel.requestTabFocus();
            return;
        }

        switch (event.getText()) {
        case "j":
        case "k":
            taskTabPanel.requestFocus();
            break;
        }
    }

    private void loadCommandInputComponent() {
        loadBottomComponent(commandInput);
        commandInput.requestFocus();
    }

    protected void loadCommandResultComponent(CommandResult result) {
        CommandResultPanel resultPanel = new CommandResultPanel(this);
        resultPanel.display(result);
    }

    /**
     * Clears the right component.
     */
    protected void clearRightComponent() {
        rightComponent.getChildren().clear();
    }

    /**
     * Clears the bottom component.
     */
    protected void clearBottomComponent() {
        bottomComponent.getChildren().clear();
    }

    public <T extends Pane> void loadLeftComponent(UiPart<T> component) {
        leftComponent.getChildren().clear();
        leftComponent.getChildren().add(component.getRoot());
        component.getRoot().prefHeightProperty().bind(TOP_COMPONENT_HEIGHT);
        component.getRoot().prefWidthProperty().bind(LEFT_COMPONENT_WIDTH);

    }

    public <T extends Pane> void loadRightComponent(UiPart<T> component) {
        rightComponent.getChildren().clear();
        rightComponent.getChildren().add(component.getRoot());
        component.getRoot().prefHeightProperty().bind(TOP_COMPONENT_HEIGHT);
        component.getRoot().prefWidthProperty().bind(RIGHT_COMPONENT_WIDTH);
    }

    public <T extends Pane> void loadBottomComponent(UiPart<T> component) {
        bottomComponent.getChildren().clear();
        bottomComponent.getChildren().add(component.getRoot());
        component.getRoot().prefHeightProperty().bind(BOTTOM_COMPONENT_HEIGHT);
        component.getRoot().prefWidthProperty().bind(WINDOW_WIDTH);
    }
}

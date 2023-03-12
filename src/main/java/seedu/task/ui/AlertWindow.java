package seedu.task.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.task.commons.core.GuiSettings;
import seedu.task.commons.core.LogsCenter;
import seedu.task.logic.Logic;

/**
 * Controller for a help page
 */
public class AlertWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(AlertWindow.class);
    private static final String FXML = "AlertWindow.fxml";
    private static final String ALERT_MESSAGE = "Here are the alerts due";
    private Logic logic;
    private TaskListPanel taskListPanel;

    @FXML
    private StackPane taskListPanelPlaceholder;

    @FXML
    private Label alertMessage;

    /**
     * Creates a new AlertWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public AlertWindow(Stage root) {
        super(FXML, root);
        alertMessage.setText(ALERT_MESSAGE);
        // Configure the UI
        // setWindowDefaultSize(logic.getGuiSettings());

    }

    /**
     * Creates a new AlertWindow.
     */
    public AlertWindow(Logic logic) {
        this(new Stage());
        this.logic = logic;
    }

    /**
     * Shows the alert window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing alert page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        getRoot().setHeight(guiSettings.getWindowHeight());
        getRoot().setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            getRoot().setX(guiSettings.getWindowCoordinates().getX());
            getRoot().setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    void fillInnerParts() {
        taskListPanel = new TaskListPanel(logic.getAlertTaskList());
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
    }


    /**
     * Returns true if the alert window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the alert window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the alert window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}

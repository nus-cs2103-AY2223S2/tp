package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.InternshipApplication;

/**
 * Controller for a reminder window.
 */
public class ReminderWindow extends UiPart<Stage> {

    public static final String EMPTY_REMINDER = "There are no reminders at the moment!";
    private static final Logger logger = LogsCenter.getLogger(ReminderWindow.class);

    private static final String FXML = "ReminderWindow.fxml";

    @FXML
    private Label reminderApplication;

    /**
     * Creates a {@code ReminderWindow} with the given {@code InternshipApplication}
     */
    public ReminderWindow(Stage root, InternshipApplication application, MainWindow mainWindow) {
        super(FXML, root);
        if (application == null) {
            reminderApplication.setText(EMPTY_REMINDER);
        } else {
            reminderApplication.setGraphic(new ReminderApplicationCard(application, mainWindow).getRoot());
        }
    }

    /**
     * Shows the reminder window.
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
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}

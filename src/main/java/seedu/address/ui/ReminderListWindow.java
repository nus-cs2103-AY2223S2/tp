package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.reminder.Reminder;

/**
 * The reminder list Window. Displays all the reminders that have been added so far.
 */
public class ReminderListWindow extends UiPart<Stage> {
    private static final String FXML = "ReminderListWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());
    private Logic logic;
    private Stage stage;
    @FXML
    private ListView<Reminder> reminderList;

    /**
     * Creates a new ReminderListWindow.
     *
     * @param logic Stores the reminder list.
     */
    public ReminderListWindow(Stage root, Logic logic) {
        super(FXML, root);
        // Set dependencies
        this.stage = root;
        this.logic = logic;
        reminderList = new ListView<>(logic.getReminderList());
        stage.setScene(new Scene(reminderList));
    }

    /**
     * Shows the help window.
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
        logger.fine("Showing reminder list window.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the reminder list window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the reminder list window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the reminder list window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        reminderList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Reminder item, boolean empty) {
                super.updateItem(item, empty);
                int i = super.getIndex() + 1;
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(i + ". " + item.getDescription() + " " + item.reminderDateTimeToString());
                }
            }
        });
        stage.show();
    }
}

package tfifteenfour.clipboard.ui.taskpage;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.MainApp;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.model.task.Task;
import tfifteenfour.clipboard.ui.UiPart;

/**
 * A UI component that displays information of a {@code Task}.
 */
public class SelectedTaskListCard extends UiPart<Region> {

    private static final String FXML = "SelectedListCard.fxml";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;

    @FXML
    private Label id;
    @FXML
    private Label name;

    /**
     * Creates a {@code TaskListCard} with the given {@code Task} and index to display.
     */
    public SelectedTaskListCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        name.setText(task.getTaskName());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SelectedTaskListCard)) {
            return false;
        }

        // state check
        SelectedTaskListCard card = (SelectedTaskListCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}

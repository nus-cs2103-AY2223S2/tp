package seedu.address.ui;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;

/**
 * A UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {
    private static final String FXML = "TaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As
     * a consequence, UI elements' variable names cannot be set to such keywords or an exception
     * will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on
     *      Mathutoring level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label taskName;
    @FXML
    private Label id;
    @FXML
    private ImageView taskStatus;


    /**
     * Creates a {@code PersonCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        requireAllNonNull(this.getClass().getResourceAsStream("/images/inProgress.png"),
                this.getClass().getResourceAsStream("/images/late.png"),
                this.getClass().getResourceAsStream("/images/complete.png"));

        this.task = task;
        id.setText(displayedIndex + ". ");
        taskName.setText(task.getName().fullName);

        TaskStatus statusOfTask = task.getStatus();

        switch (statusOfTask) {

        case INPROGRESS:
            taskStatus.setImage(new Image(this.getClass().getResourceAsStream("/images/inProgress.png")));
            break;

        case LATE:
            taskStatus.setImage(new Image(this.getClass().getResourceAsStream("/images/late.png")));
            break;

        case COMPLETE:
            taskStatus.setImage(new Image(this.getClass().getResourceAsStream("/images/complete.png")));
            break;

        default:
            taskStatus.setImage(new Image(this.getClass().getResourceAsStream("/images/inProgress.png")));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText()) && task.equals(card.task);
    }
}

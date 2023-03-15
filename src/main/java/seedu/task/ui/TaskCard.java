package seedu.task.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.Deadline;
import seedu.task.model.task.Event;
import seedu.task.model.task.Task;


/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on TaskBook level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label time;
    @FXML
    private Label effort;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        name.setText(task.getName().fullName);
        description.setText(task.getDescription().value);
        time.setText("No specified time");
        effort.setText("Effort: " + task.getEffort().toString());
        if (task instanceof Deadline) {
            Deadline tmp = (Deadline) task;
            time.setText("Deadline: " + tmp.getDeadline().toString());
        }
        if (task instanceof Event) {
            Event tmp = (Event) task;
            time.setText("From: " + tmp.getFrom().toString() + " to: " + tmp.getTo().toString());
        }
        task.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(this::makeLabel);
    }

    /**
     * Returns a color code to be used for the corresponding tag name.
     * This process is deterministic.
     *
     * @return String color code chosen.
     */
    public String chooseColor(String tagName) {
        String[] arr = new String[] {
            // Color examples courtesy of https://sashamaps.net/docs/resources/20-colors/.
            "#e6194b", "#3cb44b", "#ffe119", "#4363d8", "#f58231",
            "#911eb4", "#46f0f0", "#f032e6", "#bcf60c", "#fabebe",
            "#008080", "#e6beff", "#9a6324", "#fffac8", "#800000",
            "#aaffc3", "#808000", "#ffd8b1", "#808080", "#ffffff"
        };
        int pos = Math.abs(tagName.hashCode() % arr.length);
        return arr[pos];
    }

    /**
     * Chooses a color to be used to display tags based on the hashcode of the tag's name.
     *
     * @param tag to choose color based on.
     */
    public void makeLabel(Tag tag) {
        Label newLabel = new Label(tag.tagName);
        String str = chooseColor(tag.tagName);

        newLabel.setStyle("-fx-text-fill: black;\n"
                + "-fx-background-color: " + str + ";\n"
                + "-fx-padding: 1 3 1 3;\n"
                + "-fx-border-radius: 2;\n"
                + "-fx-background-radius: 2;\n"
                + "-fx-font-size: 11;");
        tags.getChildren().add(newLabel);
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
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}

package vimification.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import vimification.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<HBox> {

    private static final String FXML = "TaskCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label deadline;
    @FXML
    private Label priorityLevel;

    @FXML
    private Label recurrence;
    @FXML
    private Label id;

    @FXML
    private FlowPane labels;
    // @FXML
    // private FlowPane tags;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        // this.task = task;
        id.setText(displayedIndex + ". ");
        title.setText(task.getTitle());
        if (task.getDeadline() != null) {
            deadline.setText("deadline: " + task.getDeadlineToString());
        } else {
            deadline.setText("deadline: -");
        }
        priorityLevel.setText(task.getPriority().toString());
        task.getLabels().stream().forEach(label -> labels.getChildren().add(new Label(label)));
    }

}

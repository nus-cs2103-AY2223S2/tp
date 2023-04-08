package vimification.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import vimification.model.task.Priority;
import vimification.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<HBox> {

    private static final String FXML = "TaskCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;

    @FXML
    private Label title;
    @FXML
    private Label deadline;
    @FXML
    private Label priorityMessage;

    @FXML
    private FlowPane priority;


    // @FXML
    // private FlowPane tags;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        id.setText(displayedIndex + ". ");

        title.setText(task.getTitle());

        if (task.getDeadline() != null) {
            deadline.setText("deadline: " + task.getDeadlineAsString());
        } else {
            deadline.setText("deadline: -");
        }
        Priority p = task.getPriority();
        priorityMessage.setText(task.getPriority().toString());
        if (p.equals(Priority.UNKNOWN)) {
            priorityMessage.setStyle("-fx-background-color: #3e7b91");
        } else if (p.equals(Priority.NOT_URGENT)) {
            priorityMessage.setStyle("-fx-background-color: #8abc79");
        } else if (p.equals(Priority.URGENT)) {
            priorityMessage.setStyle("-fx-background-color: #d3bc75");
        } else {
            priorityMessage.setStyle("-fx-background-color: #d46b70");
        }
    }

}

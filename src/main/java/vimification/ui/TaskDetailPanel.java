package vimification.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vimification.model.task.Priority;
import vimification.model.task.Status;
import vimification.model.task.Task;


/**
 * Panel containing task details, such as description, the type of the task, etc.
 */
public class TaskDetailPanel extends UiPart<VBox> {
    private static final String FXML = "TaskDetailPanel.fxml";

    private Task task;

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
    private Label statusMessage;

    @FXML
    private ProgressBar status;

    @FXML
    private FlowPane labels;

    @FXML
    private FlowPane priority;

    /**
     * Creates a {@code TaskDetailPanel}.
     */
    public TaskDetailPanel(Task task) {
        super(FXML);
        this.task = task;
        setup();
    }

    private void setup() {
        title.setText(task.getTitle());

        if (task.getDeadline() != null) {
            deadline.setText(task.getDeadlineAsString());
        } else {
            deadline.setText("-");
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

        task.getLabels().stream().forEach(label -> labels.getChildren().add(new Label(label)));

        Status s = task.getStatus();
        String message = s.toString();
        if (s.equals(Status.NOT_DONE)) {
            status.setProgress(0.0);
        } else if (s.equals(Status.IN_PROGRESS)) {
            status.setProgress(0.5);
        } else if (s.equals(Status.COMPLETED)) {
            status.setProgress(1.0);
        } else {
            status.setProgress(1.0);
            status.setStyle("-fx-accent:#d46b70");
        }
        statusMessage.setText(message);
    }

}

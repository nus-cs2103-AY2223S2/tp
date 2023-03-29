package vimification.taskui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import vimification.model.task.Task;


/**
 * Panel containing task details, such as description, the type of the task, etc.
 */
public class TaskDetailPanel extends UiPart<VBox> {
    private static final String FXML = "TaskDetailPanel.fxml";

    private Task task;

    @FXML
    private Label typeField;
    @FXML
    private Label descriptionField;

    @FXML
    private Label statusField;
    @FXML
    private Label priorityField;

    @FXML
    private HBox durationComponent;

    /**
     * Creates a {@code TaskDetailPanel}.
     */
    public TaskDetailPanel(Task task) {
        super(FXML);
        this.task = task;
        setup();
    }

    private void setup() {
        String taskType = getTaskType(task);
        typeField.setText(taskType);

        descriptionField.setText(task.getDescription());
        statusField.setText(task.isDone() ? "Completed" : "Incomplete");
        priorityField.setText(task.getPriority().toString());

        boolean isTaskDeadline = taskType.equals("Deadline");
        durationComponent.setVisible(isTaskDeadline);
    }

    /**
     * Get the String of the Task instance.
     *
     * @param task
     * @return {@code Deadline} or {@code Todo}
     */
    private String getTaskType(Task task) {
        return task.getClass().getSimpleName();
    }

}

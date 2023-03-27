package vimification.taskui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import vimification.model.task.Task;
import vimification.model.task.Todo;


/**
 * Panel containing task details, such as description, the type of the task, etc.
 */
public class TaskDetailPanel extends UiPart<VBox> {
    private static final String FXML = "TaskDetailPanel.fxml";

    private Task task;

    @FXML
    private Label typeField;
    @FXML
    private Label descriptionText;

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

    public void setup() {
        descriptionText.setText(task.getDescription());
        priorityField.setText("High");
        statusField.setText("Not completed");

        String taskType = getTaskType(task);
        System.out.println("taskType:" + taskType);
        typeField.setText(taskType);

        // TODO: Refactor this
        switch (taskType) {
        case "Todo":
            durationComponent.setVisible(false);
            break;
        default:
            break;
        }
    }

    public String getTaskType(Task task) {
        if (task instanceof Todo) {
            return "Todo";
        }

        return "Deadline";
    }

}

package vimification.taskui;

// import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import vimification.model.task.Description;
import vimification.model.task.Task;
import vimification.taskui.UiPart;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label id;
    // @FXML
    // private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        // this.task = task;
        id.setText(displayedIndex + ". ");
        description.setText(task.getDescription().description);

        // phone.setText(task.getPhone().value);
        // address.setText(task.getAddress().value);
        // email.setText(task.getEmail().value);

        // task.getTags().stream()
        // .sorted(Comparator.comparing(tag -> tag.tagName))
        // .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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
        // TODO: FIX CLASH WITH JIAYUE
        // TaskCard card = (TaskCard) other;
        // return id.getText().equals(card.id.getText())
        // && task.equals(card.task);

        // TODO: REMOVE THIS AFTER FIX ABOVE
        return false;
    }
}

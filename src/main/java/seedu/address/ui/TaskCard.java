package seedu.address.ui;

import java.text.ParseException;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import seedu.address.model.task.Score;
import seedu.address.model.task.Task;

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
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label taskDesc;
    @FXML
    private Label taskDeadline;
    @FXML
    private Label id;
    @FXML
    private Label personAssigned;
    @FXML
    private Label personRole;

    @FXML
    private Label taskComment;

    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;

    @FXML
    private Circle circle4;

    @FXML
    private Circle circle5;

    @FXML
    private Label status;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) throws ParseException {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        taskDesc.setText(task.getDescription().toString());
        if (task.getPersonAssignedName() == null) {
            personAssigned.setText("Not Assigned");
        } else {
            personAssigned.setText(task.getPersonAssignedName());
        }
        taskDeadline.setText(task.getDate());

        if (task.getPersonAssignedRole() == null) {
            personRole.setText("None");
        } else {
            personRole.setText(task.getPersonAssignedRole());
        }
        if (task.getTaskComment() == null) {
            taskComment.setText("");
        } else {
            taskComment.setText(task.getTaskComment().toString());
        }
        setScore(task);
        setTaskStatus(task);
    }

    public void setScore(Task task) {
        if (task.isDone()) {
            Score score = task.getScore();
            if (score == null) {
                circle1.setFill(Paint.valueOf("#ffffff"));
                circle2.setFill(Paint.valueOf("#ffffff"));
                circle3.setFill(Paint.valueOf("#ffffff"));
                circle4.setFill(Paint.valueOf("#ffffff"));
                circle5.setFill(Paint.valueOf("#ffffff"));
            } else {
                if (score.getValue() == 5) {
                    circle1.setFill(Paint.valueOf("#fad02c"));
                    circle2.setFill(Paint.valueOf("#fad02c"));
                    circle3.setFill(Paint.valueOf("#fad02c"));
                    circle4.setFill(Paint.valueOf("#fad02c"));
                    circle5.setFill(Paint.valueOf("#fad02c"));
                } else if (score.getValue() == 4) {
                    circle1.setFill(Paint.valueOf("#fad02c"));
                    circle2.setFill(Paint.valueOf("#fad02c"));
                    circle3.setFill(Paint.valueOf("#fad02c"));
                    circle4.setFill(Paint.valueOf("#fad02c"));
                } else if (score.getValue() == 3) {
                    circle1.setFill(Paint.valueOf("#fad02c"));
                    circle2.setFill(Paint.valueOf("#fad02c"));
                    circle3.setFill(Paint.valueOf("#fad02c"));
                } else if (score.getValue() == 2) {
                    circle1.setFill(Paint.valueOf("#fad02c"));
                    circle2.setFill(Paint.valueOf("#fad02c"));
                } else if (score.getValue() == 1) {
                    circle1.setFill(Paint.valueOf("#fad02c"));
                }
            }
        }
        if (!task.isDone()) {
            circle1.setFill(Paint.valueOf("#ffffff"));
            circle2.setFill(Paint.valueOf("#ffffff"));
            circle3.setFill(Paint.valueOf("#ffffff"));
            circle4.setFill(Paint.valueOf("#ffffff"));
            circle5.setFill(Paint.valueOf("#ffffff"));
        }
    }

    public void setTaskStatus(Task task) {
        status.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        try {
            if (task.isDone()) {
                status.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            } else if (task.isDueToday()) {
                status.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
            } else if (task.isOverdue()) {
                status.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        } catch (IndexOutOfBoundsException | ParseException e) {
            status.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
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
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}

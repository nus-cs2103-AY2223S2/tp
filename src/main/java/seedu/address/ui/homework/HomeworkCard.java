package seedu.address.ui.homework;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.student.Homework;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class HomeworkCard extends UiPart<Region> {
    private static final String FXML = "HomeworkListCard.fxml";
    private static final String COMPLETED_ICON = "images/completed.png";
    private static final String UNCOMPLETED_ICON = "images/uncompleted.png";
    private static final String DOT = ". ";
    private static final String DEADLINE_LABEL = "Deadline: ";

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private ImageView statusIcon;
    @FXML
    private Label description;
    @FXML
    private Label deadline;

    /**
     * Creates a {@code HomeworkCard} with the given {@code Homework} and index to display.
     *
     * @param homework to be displayed in the card
     * @param id of the homework in the list
     */
    public HomeworkCard(Homework homework, int id) {
        super(FXML);
        this.id.setText(id + DOT);
        description.setText(homework.getDescription());
        deadline.setText(String.format(DEADLINE_LABEL, homework.getDeadlineString()));

        // Set the status icon to completed or uncompleted
        if (homework.isCompleted()) {
            statusIcon.setImage(new Image(COMPLETED_ICON));
        } else {
            statusIcon.setImage(new Image(UNCOMPLETED_ICON));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof HomeworkCard)) {
            return false;
        }

        // state check
        HomeworkCard card = (HomeworkCard) other;
        return description.getText().equals(card.description.getText());
    }
}

package seedu.address.ui.lesson;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.student.Lesson;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class LessonCard extends UiPart<Region> {
    private static final DateTimeFormatter PRINT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    private static final String FXML = "LessonListCard.fxml";
    private static final String DOT = ". ";

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;

    /**
     * Creates a {@code LessonCode} with the given {@code Lesson} and index to display.
     *
     * @param lesson The lesson to be displayed.
     * @param id The index of the lesson to be displayed.
     */
    public LessonCard(Lesson lesson, int id) {
        super(FXML);
        title.setText(lesson.getTitle());
        startTime.setText(lesson.getStartTime().format(PRINT_FORMATTER));
        endTime.setText(lesson.getEndTime().format(PRINT_FORMATTER));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof LessonCard)) {
            return false;
        }
        // state check
        LessonCard card = (LessonCard) other;
        return title.getText().equals(card.title.getText())
                && startTime.getText().equals(card.startTime.getText())
                && endTime.getText().equals(card.endTime.getText());
    }
}

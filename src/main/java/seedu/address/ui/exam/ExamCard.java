package seedu.address.ui.exam;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.student.Exam;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class ExamCard extends UiPart<Region> {
    private static final DateTimeFormatter PRINT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    private static final String FXML = "ExamListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;

    /**
     * Creates a {@code ExamCard} with the given {@code Exam} and index to display.
     *
     * @param exam the exam to be displayed
     * @param id the index of the exam
     */
    public ExamCard(Exam exam, int id) {
        super(FXML);
        title.setText(exam.getDescription());
        startTime.setText(exam.getStartTime().format(PRINT_FORMATTER));
        endTime.setText(exam.getEndTime().format(PRINT_FORMATTER));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof ExamCard)) {
            return false;
        }
        // state check
        ExamCard card = (ExamCard) other;
        return title.getText().equals(card.title.getText())
                && startTime.getText().equals(card.startTime.getText())
                && endTime.getText().equals(card.endTime.getText());
    }
}

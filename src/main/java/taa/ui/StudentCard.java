package taa.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import taa.model.assignment.Submission;
import taa.model.student.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on ClassList level 4</a>
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label attendance;
    @FXML
    private Label assignment;
    @FXML
    private Label groups;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        Submission latestSubmission = student.getLatestSubmission();
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        attendance.setText(String.format("Attendance: %d/12, Participation points: %.2f",
                this.student.getNumWeeksPresent(), this.student.getPartPoints()));
        assignment.setText("Latest assignment: "
                + (latestSubmission == null ? "None" : latestSubmission.describeSubmission()));
        groups.setText("Groups: None");
        student.getClassTags().stream()
                .sorted(Comparator.comparing(classTag -> classTag.tagName))
                .forEach(classTag -> tags.getChildren().add(new Label(classTag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentCard)) {
            return false;
        }

        // state check
        StudentCard card = (StudentCard) other;
        return id.getText().equals(card.id.getText())
                && student.equals(card.student);
    }
}

package tfifteenfour.clipboard.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.MainApp;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.model.student.StudentWithGrades;

/**
 * An UI component that displays the attendance information of a {@code Student}.
 */
public class GradeListCard extends UiPart<Region> {

    private static final String FXML = "GradeListCard.fxml";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final StudentWithGrades student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label studentId;
    @FXML
    private Label grade;

    /**
     * Creates an AttendanceListCard with the given StudentWithAttendance and index to display.
     */
    public GradeListCard(StudentWithGrades student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        studentId.setText(student.getStudentId().value);
        if (student.getGrade() != null) {
            grade.setText(Integer.toString(student.getGrade()));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GradeListCard)) {
            return false;
        }

        // state check
        GradeListCard card = (GradeListCard) other;
        return id.getText().equals(card.id.getText())
                && student.equals(card.student);
    }
}

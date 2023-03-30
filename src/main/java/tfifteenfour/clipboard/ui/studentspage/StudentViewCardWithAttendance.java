package tfifteenfour.clipboard.ui.studentspage;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.model.student.SessionWithAttendance;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.ui.UiPart;

/**
 * A UI component that displays information of a {@code Student}.
 */
public class StudentViewCardWithAttendance extends UiPart<Region> {
    private static final String FXML = "StudentViewCardWithAttendance.fxml";

    @FXML
    private HBox viewCardPlaceholder;
    @FXML
    private HBox attendancePlaceholder;

    /**
     * Creates a {@code StudentViewCardWithAttendance}.
     */
    public StudentViewCardWithAttendance(Student viewedStudent,
                                         ObservableList<SessionWithAttendance> attendanceList, int showAttendanceFlag) {
        super(FXML);
        viewCardPlaceholder.getChildren().add(new StudentViewCard(viewedStudent).getRoot());
        if (showAttendanceFlag == 1) {
            attendancePlaceholder.getChildren().add(new StudentAttendanceListPanel(attendanceList).getRoot());
        }
    }
}

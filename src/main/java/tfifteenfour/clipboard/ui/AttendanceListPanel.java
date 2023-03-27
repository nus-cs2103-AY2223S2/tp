package tfifteenfour.clipboard.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.model.student.StudentWithAttendance;

/**
 * Panel containing the list of student attendance.
 */
public class AttendanceListPanel extends UiPart<Region> {
    private static final String FXML = "AttendanceListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CourseListPanel.class);

    @FXML
    private ListView<StudentWithAttendance> attendanceListView;

    /**
     * Creates a {@code AttendanceListPanel} with the given {@code ObservableList}.
     */
    public AttendanceListPanel(ObservableList<StudentWithAttendance> studentList) {
        super(FXML);
        attendanceListView.setItems(studentList);
        attendanceListView.setCellFactory(listView -> new AttendanceListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code AttendanceListCard}.
     */
    class AttendanceListViewCell extends ListCell<StudentWithAttendance> {
        @Override
        protected void updateItem(StudentWithAttendance student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (student.getAttendance() == 1) {
                    setGraphic(new AttendanceListCard(student, getIndex() + 1).getRoot());
                } else {
                    setGraphic(new AbsentListCard(student, getIndex() + 1).getRoot());
                }
            }
        }
    }

    public void setAttendanceListView(ObservableList<StudentWithAttendance> studentList) {
        attendanceListView.setItems(studentList);
    }

}

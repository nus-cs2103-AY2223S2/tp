package tfifteenfour.clipboard.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.model.student.Student;

/**
 * Panel containing the list of student attendance.
 */
public class AttendanceListPanel extends UiPart<Region> {
    private static final String FXML = "AttendanceListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CourseListPanel.class);

    @FXML
    private ListView<Student> attendanceListView;

    /**
     * Creates a {@code AttendanceListPanel} with the given {@code ObservableList}.
     */
    public AttendanceListPanel(ObservableList<Student> studentList) {
        super(FXML);
        attendanceListView.setItems(studentList);
        attendanceListView.setCellFactory(listView -> new AttendanceListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code AttendanceListCard}.
     */
    class AttendanceListViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AttendanceListCard(student, getIndex() + 1).getRoot());
            }
        }
    }

    public void setAttendanceListView(ObservableList<Student> studentList) {
        attendanceListView.setItems(studentList);
    }

}

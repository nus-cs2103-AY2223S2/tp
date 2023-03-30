package tfifteenfour.clipboard.ui.studentspage;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.model.student.SessionWithAttendance;
import tfifteenfour.clipboard.ui.UiPart;

/**
 * A UI component for a student's attendance.
 */
public class StudentAttendanceListPanel extends UiPart<Region> {
    private static final String FXML = "StudentAttendanceListPanel.fxml";

    @FXML
    private ListView<SessionWithAttendance> listView;

    /**
     * Creates a {@code StudentAttendancePanel} with the given {@code ObservableList}.
     */
    public StudentAttendanceListPanel(ObservableList<SessionWithAttendance> attendanceList) {
        super(FXML);
        listView.setItems(attendanceList);
        listView.setCellFactory(listView -> new SessionAttendanceListViewCell());
    }

    class SessionAttendanceListViewCell extends ListCell<SessionWithAttendance> {
        @Override
        protected void updateItem(SessionWithAttendance session, boolean empty) {
            super.updateItem(session, empty);

            if (empty || session == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (session.getSessionAttendance() == 1) {
                    setGraphic(new PresentSessionCard(session, getIndex() + 1).getRoot());
                } else {
                    setGraphic(new AbsentSessionCard(session, getIndex() + 1).getRoot());
                }
            }
        }
    }
}

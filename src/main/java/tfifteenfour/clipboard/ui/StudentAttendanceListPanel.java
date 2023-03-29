package tfifteenfour.clipboard.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;


public class StudentAttendanceListPanel extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";

    @FXML
    private ListView<SessionWithAttendance> listView;

    public StudentAttendanceListPanel (ObservableList<SessionWithAttendance> attendanceList) {
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
                if (session.getAttendance() == 1) {
                    setGraphic(new PresentSessionCard(session, getIndex() + 1).getRoot());
                } else {
                    setGraphic(new AbsentSessionCard(session, getIndex() + 1).getRoot());
                }
            }
        }
    }
}

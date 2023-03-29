package tfifteenfour.clipboard.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.model.course.Session;

import java.util.logging.Logger;

public class AttendanceViewPanel extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AttendanceViewPanel.class);

    @FXML
    private ListView<Session> listView;

    /**
     * Creates a {@code AttendanceViewPanel} with the given {@code ObservableList}.
     */
    public AttendanceViewPanel(ObservableList<Session> sessionList) {
        super(FXML);
        listView.setItems(sessionList);
        listView.setCellFactory(listView -> new AttendanceViewPanel.AttendanceListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Session} using a {@code SessionListCard}.
     */
    class AttendanceListViewCell extends ListCell<Session> {
        @Override
        protected void updateItem(Session session, boolean empty) {
            super.updateItem(session, empty);

            if (empty || session == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (session.getAttendanceStatus()) {
                    setGraphic(new AttendedSessionCard(session, getIndex() + 1).getRoot());
                } else {
                    setGraphic(new AbsentSessionCard(session, getIndex() + 1).getRoot());
                }
            }
        }
    }
}

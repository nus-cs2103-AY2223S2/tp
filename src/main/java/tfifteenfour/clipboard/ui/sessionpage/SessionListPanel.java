package tfifteenfour.clipboard.ui.sessionpage;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.ui.UiPart;

/**
 * Panel containing the list of sessions.
 */
public class SessionListPanel extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SessionListPanel.class);

    @FXML
    private ListView<Session> listView;

    /**
     * Creates a {@code SessionListPanel} with the given {@code ObservableList}.
     */
    public SessionListPanel(ObservableList<Session> sessionList) {
        super(FXML);
        listView.setItems(sessionList);
        listView.setCellFactory(listView -> new SessionListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Session} using a {@code SelectedSessionListCard}
     * or a {@code UnselectedSessionListCard}.
     */
    class SessionListViewCell extends ListCell<Session> {
        @Override
        protected void updateItem(Session session, boolean empty) {
            super.updateItem(session, empty);

            if (empty || session == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (session.getSelectionStatus()) {
                    setGraphic(new SelectedSessionListCard(session, getIndex() + 1).getRoot());
                } else {
                    setGraphic(new UnselectedSessionListCard(session, getIndex() + 1).getRoot());
                }
            }
        }
    }

    public void setSessionListView(ObservableList<Session> sessionList) {
        listView.setItems(sessionList);
    }

}

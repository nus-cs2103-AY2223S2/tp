package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.MeetingWithPerson;

/**
 * Panel containing the list of meetings.
 */
public class MeetingListPanel extends UiPart<Region> {
    private static final String FXML = "MeetingListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<MeetingWithPerson> meetingListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public MeetingListPanel(ObservableList<MeetingWithPerson> meetingList) {
        super(FXML);
        meetingListView.setItems(meetingList);
        meetingListView.setCellFactory(listView -> new MeetingListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Meeting} using a {@code MeetingCard}.
     */
    class MeetingListViewCell extends ListCell<MeetingWithPerson> {
        @Override
        protected void updateItem(MeetingWithPerson meeting, boolean empty) {
            super.updateItem(meeting, empty);

            if (empty || meeting == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MeetingCard(meeting, getIndex() + 1, meeting.getPersonToMeet()).getRoot());
            }
        }
    }

}

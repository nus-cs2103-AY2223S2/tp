package tfifteenfour.clipboard.ui.grouppage;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.ui.UiPart;
import tfifteenfour.clipboard.ui.coursepage.CourseListPanel;

/**
 * Panel containing the list of groups.
 */
public class GroupListPanel extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CourseListPanel.class);

    @FXML
    private ListView<Group> listView;

    /**
     * Creates a {@code GroupListPanel} with the given {@code ObservableList}.
     */
    public GroupListPanel(ObservableList<Group> groupList) {
        super(FXML);
        listView.setItems(groupList);
        listView.setCellFactory(listView -> new GroupListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Group} using a {@code GroupListCard}.
     */
    class GroupListViewCell extends ListCell<Group> {
        @Override
        protected void updateItem(Group group, boolean empty) {
            super.updateItem(group, empty);

            if (empty || group == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new GroupListCard(group, getIndex() + 1).getRoot());
            }
        }
    }

    public void setGroupListView(ObservableList<Group> groupList) {
        listView.setItems(groupList);
    }

}

package seedu.sudohr.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.sudohr.model.leave.Leave;

/**
 * Creates a  list of LeaveCards to show the current month
 */
public class LeaveListPanel extends UiPart<Region> {
    private static final String FXML = "LeaveListPanel.fxml";
    private final ObservableList<Leave> leaveList;

    @FXML
    private ListView<Leave> leaveListView;

    /**
     * Creates a {@code LeaveListPanel} with the given {@code ObservableList}.
     */
    public LeaveListPanel(ObservableList<Leave> leaveList) {
        super(FXML);
        this.leaveList = leaveList;
        leaveListView.setItems(leaveList);
        leaveListView.setCellFactory(listView -> new LeaveViewCell());
    }

    class LeaveViewCell extends ListCell<Leave> {
        @Override
        protected void updateItem(Leave leave, boolean empty) {
            super.updateItem(leave, empty);

            if (empty || leave == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LeaveCard(leave).getRoot());
            }
        }
    }

}

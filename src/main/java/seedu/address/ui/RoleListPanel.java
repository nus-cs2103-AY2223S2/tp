package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.job.Role;

/**
 * Panel containing the list of roles.
 */
public class RoleListPanel extends UiPart<Region> {
    private static final String FXML = "RoleListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RoleListPanel.class);

    @FXML
    private ListView<Role> roleListView;

    /**
     * Creates a {@code RoleListPanel} with the given {@code ObservableList}.
     */
    public RoleListPanel(ObservableList<Role> roleList) {
        super(FXML);
        roleListView.setItems(roleList);
        roleListView.setCellFactory(listView -> new RoleListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Role} using a {@code RoleCard}.
     */
    class RoleListViewCell extends ListCell<Role> {
        @Override
        protected void updateItem(Role role, boolean empty) {
            super.updateItem(role, empty);

            if (empty || role == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RoleCard(role, getIndex() + 1).getRoot());
            }
        }
    }

}

package seedu.address.ui.parent;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.parent.Parent;
import seedu.address.ui.UiPart;

/**
 * A Parent List Panel for UI
 */
public class ParentListPanel extends UiPart<Region> {
    private static final String FXML = "ParentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ParentListPanel.class);

    @javafx.fxml.FXML
    private ListView<Parent> parentListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ParentListPanel(ObservableList<Parent> parentList) {
        super(FXML);
        parentListView.setItems(parentList);
        parentListView.setCellFactory(listView -> new ParentListPanel.ParentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ParentListViewCell extends ListCell<Parent> {
        @Override
        protected void updateItem(Parent parent, boolean empty) {
            super.updateItem(parent, empty);

            if (empty || parent == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ParentCard(parent, getIndex() + 1).getRoot());
            }
        }
    }
}

package seedu.patientist.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.patientist.commons.core.LogsCenter;
import seedu.patientist.model.ward.Ward;

/**
 * Panel containing the list of wards.
 */
public class WardListPanel extends UiPart<Node> {
    private static final String FXML = "WardListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(WardListPanel.class);

    @javafx.fxml.FXML
    private ListView<Ward> wardListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public WardListPanel(ObservableList<Ward> wardList) {
        super(FXML);
        wardListView.setItems(wardList);
        wardListView.setCellFactory(listView -> new WardListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class WardListViewCell extends ListCell<Ward> {
        @Override
        protected void updateItem(Ward ward, boolean empty) {
            super.updateItem(ward, empty);

            if (empty || ward == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new WardCard(ward, getIndex() + 1).getRoot());
            }
        }
    }
}

package seedu.medinfo.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.medinfo.model.ward.Ward;

/**
 * Panel containing the list of wards.
 */
public class WardListPanel extends UiPart<Region> {
    private static final String FXML = "WardListPanel.fxml";

    @FXML
    private ListView<Ward> wardListView;

    /**
     * Creates a {@code WardListPanel} with the given {@code ObservableList}.
     */
    public WardListPanel(ObservableList<Ward> wardList) {
        super(FXML);
        wardListView.setItems(wardList);
        wardListView.setCellFactory(listView -> new WardListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Ward} using
     * a {@code WardCard}.
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

package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.fish.Fish;

/**
 * Panel containing the list of Fishes.
 */
public class FishListPanel extends UiPart<Region> {
    private static final String FXML = "FishListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FishListPanel.class);

    @FXML
    private ListView<Fish> fishListView;

    /**
     * Creates a {@code FishListPanel} with the given {@code ObservableList}.
     */
    public FishListPanel(ObservableList<Fish> fishList) {
        super(FXML);
        fishListView.setItems(fishList);
        fishListView.setCellFactory(listView -> new FishListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Fish} using a {@code FishCard}.
     */
    class FishListViewCell extends ListCell<Fish> {
        @Override
        protected void updateItem(Fish fish, boolean empty) {
            super.updateItem(fish, empty);

            if (empty || fish == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FishCard(fish, getIndex() + 1).getRoot());
            }
        }
    }

}

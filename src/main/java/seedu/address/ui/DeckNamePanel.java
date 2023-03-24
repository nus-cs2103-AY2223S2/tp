package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the deck name of the current review.
 */
public class DeckNamePanel extends UiPart<Region> {
    private static final String FXML = "DeckNamePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ReviewStatsPanel.class);

    @FXML
    private ListView<String> deckNameView;

    /**
     * Creates a {@code ReviewStatsPanel} with the given {@code ObservableList}.
     */
    public DeckNamePanel(ObservableList<String> deckNameList) {
        super(FXML);
        deckNameView.setItems(deckNameList);
        deckNameView.setCellFactory(listView -> new DeckNameViewCell());
    }

    static class DeckNameViewCell extends ListCell<String> {
        @Override
        protected void updateItem(String deckName, boolean empty) {
            super.updateItem(deckName, empty);
            if (empty || deckName == null) {
                setGraphic(null);
                setText(null);

            } else {
                setGraphic(new DeckName(deckName).getRoot());
                setStyle("-fx-background-color: transparent");
            }
        }
    }
}

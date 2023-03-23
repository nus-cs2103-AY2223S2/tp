package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the statistics of the current review.
 */
public class DeckNamePanel extends UiPart<Region> {
    private static final String FXML = "DeckNamePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ReviewStatsPanel.class);

    @FXML
    private ListView<Pair<String, String> > deckNameView;

    /**
     * Creates a {@code ReviewStatsPanel} with the given {@code ObservableList}.
     */
    public DeckNamePanel(ObservableList<Pair<String, String> > deckNameList) {
        super(FXML);
        deckNameView.setItems(deckNameList);
        deckNameView.setCellFactory(listView -> new DeckNamePanel.DeckNameViewCell());
        deckNameView.setStyle("-fx-background-color: transparent");
    }

    class DeckNameViewCell extends ListCell<Pair<String, String>> {
        @Override
        protected void updateItem(Pair<String, String> pair, boolean empty) {
            super.updateItem(pair, empty);
            if (empty || pair == null) {
                setGraphic(null);
                setText(null);

            } else {
                setGraphic(new DeckName(pair).getRoot());
                setStyle("fx-background-color:transparent");
            }
        }
    }
}

package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the deck name of the current review.
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
    }

}

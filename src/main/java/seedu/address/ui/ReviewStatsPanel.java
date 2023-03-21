package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.deck.Deck;

/**
 * Panel containing the list of persons.
 */
public class ReviewStatsPanel extends UiPart<Region> {
    private static final String FXML = "ReviewStatsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ReviewStatsPanel.class);

    @FXML
    private ListView<String> reviewStatsView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ReviewStatsPanel(ObservableList<String> reviewStatsList) {
        super(FXML);
        reviewStatsView.setItems(reviewStatsList);
        reviewStatsView.setStyle("-fx-background-color: #FFFFFF;"
                + "-fx-background-radius: 30; -fx-border-radius: 30; -fx-border-width:5;");
    }

}

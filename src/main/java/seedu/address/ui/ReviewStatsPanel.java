package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the statistics of the current review.
 */
public class ReviewStatsPanel extends UiPart<Region> {
    private static final String FXML = "ReviewStatsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ReviewStatsPanel.class);

    @FXML
    private ListView<String> reviewStatsView;

    /**
     * Creates a {@code ReviewStatsPanel} with the given {@code ObservableList}.
     */
    public ReviewStatsPanel(ObservableList<String> reviewStatsList) {
        super(FXML);
        reviewStatsView.setItems(reviewStatsList);
        reviewStatsView.setStyle("-fx-background-color: #FFFFFF;"
                + "-fx-background-radius: 30; -fx-border-radius: 30; -fx-border-width:5;");
    }

}

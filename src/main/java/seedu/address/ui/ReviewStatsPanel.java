package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.card.Card;

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
        reviewStatsView.setCellFactory(listView -> new ReviewStatsPanel.ReviewStatsViewCell());
        reviewStatsView.setStyle("-fx-background-color: #FFFFFF;"
                + "-fx-background-radius: 30; -fx-border-radius: 30; -fx-border-width:5;");
    }

    class ReviewStatsViewCell extends ListCell<String> {
        @Override
        protected void updateItem(String string, boolean empty) {
            super.updateItem(string, empty);
            if (empty || string == null) {
                setGraphic(null);
                setText(null);

            } else {
                setGraphic(new ReviewStat(string).getRoot());
                setStyle("-fx-border-insets: 10px; -fx-background-insets: 10px; -fx-padding: 10 80 10 80; "
                        + "-fx-background-color:#ededed ");
            }
        }
    }

}

package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.recommendation.Recommendation;

/**
 * Panel containing the list of recommendations.
 */
public class MeetListPanel extends UiPart<Region> {
    private static final String FXML = "MeetListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MeetListPanel.class);

    @FXML
    private ListView<Recommendation> meetListView;

    /**
     * Creates a {@code MeetListPanel} with the given {@code ObservableList}.
     */
    public MeetListPanel(ObservableList<Recommendation> recommendationList) {
        super(FXML);
        meetListView.setItems(recommendationList);
        meetListView.setCellFactory(listView -> new MeetListPanel.MeetListViewCell());
    }

    class MeetListViewCell extends ListCell<Recommendation> {
        @Override
        protected void updateItem(Recommendation recommendation, boolean empty) {
            super.updateItem(recommendation, empty);

            if (empty || recommendation == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MeetCard(recommendation).getRoot());
            }
        }
    }
}

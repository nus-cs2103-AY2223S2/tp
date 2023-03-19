package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.pair.Pair;

/**
 * Panel containing the list of pairs.
 */
public class PairListPanel extends UiPart<Region> {
    private static final String FXML = "PairListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PairListPanel.class);

    @FXML
    private ListView<Pair> pairListView;

    /**
     * Creates a {@code PairListPanel} with the given {@code ObservableList}.
     */
    public PairListPanel(ObservableList<Pair> pairList) {
        super(FXML);
        pairListView.setItems(pairList);
        pairListView.setCellFactory(listView -> new PairListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Pair} using a {@code PairCard}.
     */
    static class PairListViewCell extends ListCell<Pair> {
        @Override
        protected void updateItem(Pair pair, boolean empty) {
            super.updateItem(pair, empty);

            if (empty || pair == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PairCard(pair, getIndex() + 1).getRoot());
            }
        }
    }

}

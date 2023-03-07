package mycelium.mycelium.ui.common;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import mycelium.mycelium.commons.core.LogsCenter;

/**
 * Panel containing the list of {@code T}.
 */
public class ListPanel<T> extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ListPanel.class);
    private final Cardifier<T> cardifier;

    @FXML
    private ListView<T> listView;

    /**
     * Creates a {@code ListPanel} with the given {@code ObservableList}.
     */
    public ListPanel(ObservableList<T> list, Cardifier<T> cardifier) {
        super(FXML);
        this.cardifier = cardifier;
        if (list == null) {
            return;
        }
        listView.setItems(list);
        listView.setCellFactory(listview -> new ListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code T}.
     */
    class ListViewCell extends ListCell<T> {
        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(cardifier.cardify(item, getIndex() + 1).getRoot());
            }
        }
    }

}

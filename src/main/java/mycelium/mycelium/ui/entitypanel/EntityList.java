package mycelium.mycelium.ui.entitypanel;

import static java.util.Objects.requireNonNull;

import java.util.function.BiFunction;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.ui.UiPart;

/**
 * Panel containing the list of {@code T}.
 */
public class EntityList<T> extends UiPart<Region> {
    private static final String FXML = "EntityList.fxml";
    private Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private ListView<T> listView;

    /**
     * Creates a {@code ListPanel} with the given {@code ObservableList}.
     */
    public EntityList(ObservableList<T> list) {
        super(FXML);
        requireNonNull(list);
        listView.setItems(list);
        logger.fine("Initialized list panel with " + list.size() + " items");
    }

    /**
     * Creates a {@code ListPanel} with the given {@code list} after transforming with {@code biMap}
     *
     * @param list  ObservableList of items
     * @param biMap BiFunction that transforms the list to the appropriate {@code UiPart}
     */
    public EntityList(
        ObservableList<T> list,
        BiFunction<
            ? super T,
            ? super Integer,
            ? extends UiPart<? extends Node>> biMap
    ) {
        this(list);
        listView.setCellFactory(listView -> new ListViewCell(biMap));
    }

    /**
     * Replaces the contents of the list panel with the given list.
     *
     * @param list The new list to display.
     */
    public void setItems(ObservableList<T> list) {
        requireNonNull(list);
        listView.setItems(list);
        logger.fine("Reset list panel with " + list.size() + " items");
    }

    /**
     * Selects and scrolls to the next item in the list.
     */
    public void nextItem() {
        int size = listView.getItems().size();
        if (size <= 0) {
            return;
        }
        assert size > 0;
        int nextIndex = (listView.getSelectionModel().getSelectedIndex() + 1) % size;
        listView.getSelectionModel().select(nextIndex);
        listView.scrollTo(listView.getSelectionModel().getSelectedItem());
    }

    /**
     * Selects and scrolls to the previous item in the list.
     */
    public void prevItem() {
        int size = listView.getItems().size();
        if (size <= 0) {
            return;
        }
        assert size > 0;
        int prevIndex = (listView.getSelectionModel().getSelectedIndex() - 1 + size) % size;
        listView.getSelectionModel().select(prevIndex);
        listView.scrollTo(listView.getSelectionModel().getSelectedItem());
    }

    /**
     * Returns the selected item in the list.
     *
     * @return The selected item in the list.
     */
    public T getSelectedItem() {
        return listView.getSelectionModel().getSelectedItem();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code T}.
     */
    class ListViewCell extends ListCell<T> {
        private BiFunction<? super T, ? super Integer, ? extends UiPart<? extends Node>> biMap;

        ListViewCell(BiFunction<? super T, ? super Integer, ? extends UiPart<? extends Node>> biMap) {
            super();
            this.biMap = biMap;
        }

        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(biMap.apply(item, getIndex() + 1).getRoot());
            }
        }
    }

}

package mycelium.mycelium.ui.common;

import static java.util.Objects.requireNonNull;

import java.util.function.BiFunction;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of {@code T}.
 */
public class ListPanel<T> extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";

    @FXML
    private ListView<T> listView;

    /**
     * Creates a {@code ListPanel} with the given {@code ObservableList}.
     */
    public ListPanel(ObservableList<T> list) {
        super(FXML);
        requireNonNull(list);
        listView.setItems(list);
    }

    /**
     * Creates a {@code ListPanel} with the given {@cpde list} after transforming with {@code biMap}
     *
     * @param list  ObservableList of items
     * @param biMap BiFunction that transform the list to the approproate {@code UiPart}
     */
    public ListPanel(
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

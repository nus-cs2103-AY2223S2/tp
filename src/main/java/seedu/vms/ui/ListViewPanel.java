package seedu.vms.ui;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Function;

import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;


/** An extension of {@link ListView} to display the values of a map. */
public class ListViewPanel<T extends Comparable<T>> extends ListView<T> {
    private final Function<T, Node> displayFunction;


    /**
     * Constructs a {@code ListViewPanel}.
     *
     * @param dataMap - the map of data to display within this list view.
     * @param displayFunction - factory function to generate the graphical
     *      representation of the data.
     * @param <T> - the type of data being displayed.
     */
    public ListViewPanel(ObservableMap<?, T> dataMap, Function<T, Node> displayFunction) {
        this.displayFunction = displayFunction;
        setCellFactory(listView -> new DisplayCell());
        dataMap.addListener(this::handleChange);
        updateList(dataMap.values());
    }


    private void handleChange(MapChangeListener.Change<?, ? extends T> change) {
        updateList(change.getMap().values());
    }


    private void updateList(Collection<? extends T> updatedDatas) {
        getItems().setAll(updatedDatas);
        getItems().sort(Comparator.naturalOrder());
    }





    private class DisplayCell extends ListCell<T> {
        @Override
        protected void updateItem(T data, boolean empty) {
            super.updateItem(data, empty);
            if (empty || data == null) {
                setText(null);
                setGraphic(null);
            } else {
                setGraphic(displayFunction.apply(data));
            }
        }
    }
}

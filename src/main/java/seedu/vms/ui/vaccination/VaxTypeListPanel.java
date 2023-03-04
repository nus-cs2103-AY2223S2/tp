package seedu.vms.ui.vaccination;

import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.vms.model.vaccination.VaxType;


/**
 * Graphical representation of the filtered list view of available vaccination
 * types.
 */
public class VaxTypeListPanel extends ListView<VaxType> {
    /**
     * Constructs a {@code VaxTypeListPanel}.
     *
     * @param vaxTypeMap - the observable map of vaccination maps to display.
     */
    public VaxTypeListPanel(ObservableMap<String, VaxType> vaxTypeMap) {
        vaxTypeMap.addListener(this::handleChange);
        getItems().setAll(vaxTypeMap.values());
        setCellFactory(listView -> new VaxTypeListViewCell());
    }


    private void handleChange(MapChangeListener.Change<? extends String, ? extends VaxType> change) {
        getItems().setAll(change.getMap().values());
    }





    private class VaxTypeListViewCell extends ListCell<VaxType> {
        @Override
        protected void updateItem(VaxType vaxType, boolean empty) {
            super.updateItem(vaxType, empty);
            if (empty || vaxType == null) {
                setText(null);
                setGraphic(null);
            } else {
                setGraphic(new VaxTypeCard(vaxType).getRoot());
            }
        }
    }
}

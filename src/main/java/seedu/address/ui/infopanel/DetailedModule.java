package seedu.address.ui.infopanel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.ui.PersonCard;
import seedu.address.ui.PersonListPanel;

/**
 * DetailedModule controller for showing module details at DetailedInfoPanel.
 */
public class DetailedModule extends DetailedInfo {

    private static final String FXML = "DetailedModule.fxml";

    @FXML
    private ListView<Module> moduleListView;

    public DetailedModule(Person protagonist) {
        super(FXML);
        ObservableList<Module> list = FXCollections.observableArrayList(protagonist.getModules());
        moduleListView.setItems(list);
        moduleListView.setCellFactory(listView -> new PersonListPanel.ModuleListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Module} using a {@code ModuleCard}.
     */
    class ModuleListViewCell extends ListCell<Module> {
        @Override
        protected void updateItem(Module module, boolean empty) {
            super.updateItem(module, empty);

            if (empty || module == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ModuleCard(module, getIndex() + 1).getRoot());
            }
        }
    }

}

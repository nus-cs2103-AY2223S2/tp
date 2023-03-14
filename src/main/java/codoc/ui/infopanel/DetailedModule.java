package codoc.ui.infopanel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import codoc.model.module.Module;
import codoc.model.person.Person;
import codoc.ui.infopanel.ModuleCard;

import java.util.Comparator;

/**
 * DetailedModule controller for showing module details at DetailedInfoPanel.
 */
public class DetailedModule extends DetailedInfo {

    private static final String FXML = "DetailedModule.fxml";

    @FXML
    private ListView<Module> moduleListView;

    public DetailedModule(Person protagonist) {
        super(FXML);
        ObservableList<Module> modules = FXCollections.observableArrayList();
        protagonist.getModules().stream()
                .sorted(Comparator.comparing((Module module) -> module.moduleName).reversed())
                .forEach(module -> modules.add(module));
        moduleListView.setItems(modules);
        moduleListView.setCellFactory(listView -> new ModuleListViewCell());
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

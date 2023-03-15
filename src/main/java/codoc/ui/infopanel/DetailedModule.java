package codoc.ui.infopanel;

import java.util.Comparator;

import codoc.model.module.Module;
import codoc.model.person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * DetailedModule controller for showing module details at DetailedInfoPanel.
 */
public class DetailedModule extends DetailedInfo {

    private static final String FXML = "DetailedModule.fxml";

    @FXML
    private ListView<Module> moduleListView;

    /**
     * Creates a {@code DetailedModule} tab with the given {@code protagonist}.
     */
    public DetailedModule(Person protagonist) {
        super(FXML);
        ObservableList<Module> modules = FXCollections.observableArrayList();
        protagonist.getModules().stream()
                .sorted(Comparator.comparing((Module module) -> module.moduleName).reversed())
                .forEach(module -> modules.add(module));
        moduleListView.setItems(modules);
        moduleListView.setCellFactory(listView -> new ModuleListViewCell());
        // Took forever to get this to work but now ListView sets max height based on number of items
        moduleListView.setPrefHeight((52 * modules.size()) + 2);
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

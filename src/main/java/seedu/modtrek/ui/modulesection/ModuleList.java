package seedu.modtrek.ui.modulesection;

import java.util.Map;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.ui.UiPart;

/**
 * Represents a module list comprising module groups.
 */
public class ModuleList extends UiPart<Region> {
    private static final String FXML = "modulesection/ModuleList.fxml";

    @FXML
    private ScrollPane moduleListScrollContainer;

    @FXML
    private VBox moduleList;

    /**
     * Instantiates a new ModuleList.
     *
     * @param modules        the entire list of modules
     */
    public ModuleList(ObservableList<Module> modules) {
        super(FXML);

        updateFilteredModules(modules);
    }

    /**
     * Instantiates a new Module list.
     *
     * @param moduleGroups the module groups
     */
    public ModuleList(TreeMap<Object, ObservableList<Module>> moduleGroups) {
        super(FXML);

        updateSortedModules(moduleGroups);
    }
    /**
     * Displays all module groups within a module list.
     * @param modules the list of modules
     */
    public void updateFilteredModules(ObservableList<Module> modules) {
        moduleList.getChildren().clear();

        moduleListScrollContainer.setVvalue(0);

        if (modules.size() == 0) {
            displayPlaceholderText("No modules found that match your search query.");
        }

        ModuleGroup moduleGroup = new ModuleGroup(modules, "");
        moduleList.getChildren().add(moduleGroup.getRoot());
    }

    /**
     * Updates the sorted module groups.
     * @param moduleGroups The sorted module groups.
     */
    public void updateSortedModules(TreeMap<Object, ObservableList<Module>> moduleGroups) {
        moduleList.getChildren().clear();

        moduleListScrollContainer.setVvalue(0);

        if (moduleGroups.size() == 0) {
            displayPlaceholderText("No modules found in the module list.");
        }

        for (Map.Entry<Object, ObservableList<Module>> entry : moduleGroups.entrySet()) {
            ModuleGroup moduleGroup = new ModuleGroup(entry.getValue(), entry.getKey().toString());
            moduleList.getChildren().add(moduleGroup.getRoot());
        }
    }

    /**
     * Displays the placeholder text message when no modules are present in the module list.
     */
    private void displayPlaceholderText(String text) {
        Label placeholder = new Label(text);
        placeholder.getStyleClass().add("h3");
        moduleList.getChildren().add(placeholder);
    }
}

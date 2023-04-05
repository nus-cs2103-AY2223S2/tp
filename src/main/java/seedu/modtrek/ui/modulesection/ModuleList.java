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
 * A UI component comprising of ModuleGroups.
 */
public class ModuleList extends UiPart<Region> {
    private static final String FXML = "modulesection/ModuleList.fxml";

    @FXML
    private ScrollPane moduleListScrollContainer;

    @FXML
    private VBox moduleList;

    /**
     * Instantiates a new ModuleList component.
     * @param modules the list of modules to be displayed on the ModuleList.
     */
    public ModuleList(ObservableList<Module> modules) {
        super(FXML);

        updateFilteredModules(modules);
    }

    /**
     * Instantiates a new ModuleList component.
     * @param moduleGroups the list of modules (categorised in groups) to be displayed on the ModuleList.
     */
    public ModuleList(TreeMap<Object, ObservableList<Module>> moduleGroups) {
        super(FXML);

        updateSortedModules(moduleGroups);
    }
    /**
     * Updates the list of filtered modules in the ModuleList.
     * @param modules the list of filtered modules.
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
     * Updates the list of modules (categorised in groups by a certain sorting criteria) in the ModuleList.
     * @param moduleGroups the list of modules categorised in groups.
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
     * Displays the placeholder text message when no modules are present in the ModuleList.
     */
    private void displayPlaceholderText(String text) {
        Label placeholder = new Label(text);
        placeholder.getStyleClass().add("h3");
        moduleList.getChildren().add(placeholder);
    }
}

package seedu.modtrek.ui.modulesection;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.ui.UiPart;

/**
 * Represents a module list comprising module groups.
 */
public class ModuleList extends UiPart<Region> {
    private static final String FXML = "modulesection/ModuleList.fxml";

    @FXML
    private VBox moduleList;

    /**
     * Instantiates a new ModuleList.
     *
     * @param modules the entire list of modules
     * @param isFilteredList indicates if the list of modules is filtered by a search query
     */
    public ModuleList(ObservableList<Module> modules, boolean isFilteredList) {
        super(FXML);

        if (modules.size() == 0) {
            displayPlaceholderText(isFilteredList);
        }
        displayModuleGroup(modules, isFilteredList);
    }

    public ModuleList(TreeMap<? extends Object, ObservableList<Module>> moduleGroups) {
        super(FXML);

        displaySortedModuleGroup(moduleGroups);
    }
    /**
     * Displays all module groups within a module list.
     * @param modules the list of modules
     * @param isFilteredList indicates if the list of modules is filtered by a search query
     */
    private void displayModuleGroup(ObservableList<Module> modules, boolean isFilteredList) {
        if (isFilteredList) {
            ModuleGroup moduleGroup = new ModuleGroup(modules, "");
            moduleList.getChildren().add(moduleGroup.getRoot());
            return;
        }

        HashMap<SemYear, ObservableList<Module>> modsPerSemYear = new HashMap<>();
        for (Module module : modules) {
            SemYear semYear = module.getSemYear();
            if (!modsPerSemYear.containsKey(semYear)) {
                ObservableList<Module> mods = FXCollections.observableArrayList();
                mods.add(module);
                modsPerSemYear.put(semYear, mods);
            } else {
                modsPerSemYear.get(semYear).add(module);
            }
        }

        for (Map.Entry<SemYear, ObservableList<Module>> entry : modsPerSemYear.entrySet()) {
            ModuleGroup moduleGroup = new ModuleGroup(entry.getValue(), entry.getKey().toString());
            moduleList.getChildren().add(moduleGroup.getRoot());
        }
    }

    private void displaySortedModuleGroup(TreeMap<? extends Object, ObservableList<Module>> moduleGroups) {
        for (Map.Entry<? extends Object, ObservableList<Module>> entry : moduleGroups.entrySet()) {
            ModuleGroup moduleGroup = new ModuleGroup(entry.getValue(), entry.getKey().toString());
            moduleList.getChildren().add(moduleGroup.getRoot());
        }
    }

    /**
     * Displays the placeholder text message when no modules are present in the module list.
     *
     * @param isFilteredList indicates if the list of modules is filtered by a search query
     */
    private void displayPlaceholderText(boolean isFilteredList) {
        String text;

        text = isFilteredList ? "There are no modules that match your search query."
                : "No modules found in the module list.";

        Label placeholder = new Label(text);
        placeholder.getStyleClass().add("h3");
        moduleList.getChildren().add(placeholder);
    }
}

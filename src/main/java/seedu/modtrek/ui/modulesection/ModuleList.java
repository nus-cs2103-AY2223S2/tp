package seedu.modtrek.ui.modulesection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        displayModuleGroup(modules);
    }

    /**
     * Displays all module groups within a module list.
     * @param modules the list of modules
     */
    private void displayModuleGroup(ObservableList<Module> modules) {
        HashMap<SemYear, ArrayList<Module>> modsPerSemYear = new HashMap<>();
        for (Module module : modules) {
            SemYear semYear = module.getSemYear();
            if (!modsPerSemYear.containsKey(semYear)) {
                ArrayList<Module> mods = new ArrayList<>();
                mods.add(module);
                modsPerSemYear.put(semYear, mods);
            } else {
                modsPerSemYear.get(semYear).add(module);
            }
        }

        for (Map.Entry<SemYear, ArrayList<Module>> entry : modsPerSemYear.entrySet()) {
            ModuleGroup moduleGroup = new ModuleGroup(entry.getKey(), entry.getValue());
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

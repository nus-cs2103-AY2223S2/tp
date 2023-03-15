package seedu.modtrek.ui.modulelist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.ui.UiPart;

/**
 * Represents a module list comprising module groups.
 */
public class ModuleList extends UiPart<Region> {
    private static final String FXML = "modulelist/ModuleList.fxml";

    @FXML
    private VBox moduleList;

    /**
     * Instantiates a new ModuleList.
     *
     * @param modules the entire list of modules
     */
    public ModuleList(ObservableList<Module> modules) {
        super(FXML);
        displayModuleGroup(modules);
    }

    /**
     * Displays all module groups within a module list.
     * @param modules the list of modules
     */
    public void displayModuleGroup(ObservableList<Module> modules) {
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
}

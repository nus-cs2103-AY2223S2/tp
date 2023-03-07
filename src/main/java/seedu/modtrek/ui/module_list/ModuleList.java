package seedu.modtrek.ui.module_list;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.ui.UiPart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ModuleList extends UiPart<Region> {
    private static final String FXML = "module_list/ModuleList.fxml";

    @FXML
    private VBox moduleList;

    public ModuleList(ObservableList<Module> modules) {
        super(FXML);
        displayModuleGroup(modules);
    }

    public void displayModuleGroup(ObservableList<Module> modules) {
        // TODO: render ModuleGroup dynamically
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

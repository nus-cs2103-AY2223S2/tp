package seedu.modtrek.ui.modulesection;

import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.ui.UiPart;

public class ModuleSearchSection extends ModuleSection {
    public ModuleSearchSection(ObservableList<Module> modules) {
        super(modules);

        ModuleSectionFindNav nav = new ModuleSectionFindNav();
        moduleSectionNav.getChildren().add(nav.getRoot());
    }

    private class ModuleSectionFindNav extends UiPart<Region> {
        private static final String FXML = "modulesection/ModuleSectionFindNav.fxml";

        public ModuleSectionFindNav() {
            super(FXML);
        }
    }
}

package seedu.modtrek.ui.modulesection;

import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.ui.UiPart;

public class ModuleListSection extends ModuleSection {
    public ModuleListSection(ObservableList<Module> modules) {
        super(modules);

        ModuleSectionSortNav nav = new ModuleSectionSortNav();
        moduleSectionNav.getChildren().add(nav.getRoot());
    }

    private class ModuleSectionSortNav extends UiPart<Region> {
        private static final String FXML = "modulesection/ModuleSectionSortNav.fxml";

        public ModuleSectionSortNav() {
            super(FXML);
        }
    }
}

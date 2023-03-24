package seedu.modtrek.ui.modulesection;

import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.ui.UiPart;

/**
 * A subsection within the ResultsSection (left panel) that displays modules queried by
 * the user.
 */
public class ModuleSearchSection extends ModuleSection {

    /**
     * Instantiates a ModuleSearchSection.
     * @param modules The modules to display in the section.
     */
    public ModuleSearchSection(ObservableList<Module> modules) {
        super();

        ModuleList moduleList = new ModuleList(modules, true);
        moduleListPlaceholder.getChildren().add(moduleList.getRoot());

        ModuleSectionFindNav nav = new ModuleSectionFindNav();
        moduleSectionNav.getChildren().add(nav.getRoot());
    }

    /**
     * The navigation bar of ModuleSearchSection that lists the filters requested by the user to apply to
     * the list of modules.
     */
    private class ModuleSectionFindNav extends UiPart<Region> {
        private static final String FXML = "modulesection/ModuleSectionFindNav.fxml";

        public ModuleSectionFindNav() {
            super(FXML);
        }
    }
}

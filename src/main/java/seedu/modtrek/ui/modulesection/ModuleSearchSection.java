package seedu.modtrek.ui.modulesection;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.ui.UiPart;

/**
 * A subsection within the ResultsSection (left panel) that displays modules queried by
 * the user.
 */
public class ModuleSearchSection extends ModuleSection {
    private ModuleSectionFindNav findNav;

    /**
     * Instantiates a ModuleSearchSection.
     * @param filteredModules The modules to display in the section.
     */
    public ModuleSearchSection(ObservableList<Module> filteredModules, List<String> filters) {
        super();

        this.findNav = new ModuleSectionFindNav(filters);
        this.moduleList = new ModuleList(filteredModules);

        moduleSectionNav.getChildren().add(findNav.getRoot());
        moduleListPlaceholder.getChildren().add(moduleList.getRoot());
    }

    /**
     * Updates the filtered modules in the ModuleSearchSection.
     * @param filteredModules The filtered modules.
     * @param filters The list of filters applied.
     */
    public void update(ObservableList<Module> filteredModules, List<String> filters) {
        moduleList.updateFilteredModules(filteredModules);
        findNav.updateFilters(filters);
    }

    /**
     * The navigation bar of ModuleSearchSection that lists the filters requested by the user to apply to
     * the list of modules.
     */
    private class ModuleSectionFindNav extends UiPart<Region> {
        private static final String FXML = "modulesection/ModuleSectionFindNav.fxml";

        @FXML
        private FlowPane findNav;

        /**
         * Instantiates a ModuleSectionFindNav component.
         * @param filters The list of filters to display on the navigation bar.
         */
        public ModuleSectionFindNav(List<String> filters) {
            super(FXML);

            updateFilters(filters);
        }

        /**
         * Displays list of filters applied on the module list.
         * @param filters The list of filters.
         */
        public void updateFilters(List<String> filters) {
            /* Remove previous filters */
            int numPrevFilters = findNav.getChildren().size() - 1;
            if (numPrevFilters > 0) {
                findNav.getChildren().remove(1, numPrevFilters + 1);
            }

            if (filters.size() == 0) {
                Label placeholder = new Label("None");
                placeholder.getStyleClass().addAll("module-section-find-nav-label-placeholder", "h3");
                findNav.getChildren().add(placeholder);
                return;
            }

            for (String filter : filters) {
                Label filterLabel = new Label(filter);
                filterLabel.getStyleClass().add("module-section-find-nav-filter-label");
                findNav.getChildren().add(filterLabel);
            }
        }
    }
}

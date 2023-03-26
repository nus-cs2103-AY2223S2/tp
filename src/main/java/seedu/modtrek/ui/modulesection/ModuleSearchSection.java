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

    /**
     * Instantiates a ModuleSearchSection.
     * @param modules The modules to display in the section.
     */
    public ModuleSearchSection(ObservableList<Module> modules, List<String> filters) {
        super();

        ModuleList moduleList = new ModuleList(modules, true);
        moduleListPlaceholder.getChildren().add(moduleList.getRoot());

        ModuleSectionFindNav nav = new ModuleSectionFindNav(filters);
        moduleSectionNav.getChildren().add(nav.getRoot());
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
         * Instantiates a ModuleSectionFindNav.
         * @param filters The list of filters to display on the navigation bar.
         */
        public ModuleSectionFindNav(List<String> filters) {
            super(FXML);

            displayFilters(filters);
        }

        /**
         * Displays list of filters applied on the module list.
         * @param filters The list of filters.
         */
        private void displayFilters(List<String> filters) {
            for (String filter : filters) {
                Label filterLabel = new Label(filter);
                filterLabel.getStyleClass().add("module-section-find-nav-filter-label");
                findNav.getChildren().add(filterLabel);
            }
        }
    }
}

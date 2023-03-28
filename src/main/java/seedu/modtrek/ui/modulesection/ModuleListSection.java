package seedu.modtrek.ui.modulesection;

import java.util.List;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Region;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.ui.UiPart;

/**
 * A subsection within the ResultsSection (left panel) that displays all the modules in the user's
 * module list.
 */
public class ModuleListSection extends ModuleSection {
    private ModuleSectionSortNav sortNav;

    /**
     * Instantiates a new Module list section.
     *
     * @param sortedModules the sorted lists
     * @param sort the sort criteria
     * @param sorters the list of sorting handlers for the buttons in the dropdown box in sortNav.
     */
    public ModuleListSection(TreeMap<? extends Object, ObservableList<Module>> sortedModules,
                             String sort, List<Runnable> sorters) {
        super();

        this.sortNav = new ModuleSectionSortNav(sort, sorters);
        this.moduleList = new ModuleList(sortedModules);

        moduleSectionNav.getChildren().add(sortNav.getRoot());
        moduleListPlaceholder.getChildren().add(moduleList.getRoot());
    }

    /**
     * Updates the sorted modules.
     * @param sortedModules The sorted modules.
     * @param sort The sort criteria.
     */
    public void update(TreeMap<? extends Object, ObservableList<Module>> sortedModules, String sort) {
        moduleList.updateSortedModules(sortedModules);
        sortNav.updateSortLabel(sort);
    }

    /**
     * The navigation bar of ModuleListSection enabling user to select the category to sort
     * the modules by.
     */
    private class ModuleSectionSortNav extends UiPart<Region> {
        private static final String FXML = "modulesection/ModuleSectionSortNav.fxml";

        private final String[] selection = new String[] {"Year", "Code", "Credits", "Grade", "Tag"};

        @FXML
        private ComboBox sortDropdown;

        /**
         * Instantiates a new Module section sort nav.
         */
        public ModuleSectionSortNav(String sort, List<Runnable> sorters) {
            super(FXML);

            updateSortLabel(sort);
            setListeners(sorters);
        }

        private void setListeners(List<Runnable> sorters) {
            sortDropdown.setOnAction((event) -> {
                int selectedIndex = sortDropdown.getSelectionModel().getSelectedIndex();
                sorters.get(selectedIndex).run();
            });
        }

        public void updateSortLabel(String sort) {
            sort = sort.substring(0, 1) + sort.toLowerCase().substring(1);
            sortDropdown.setValue(sort);
        }
    }
}

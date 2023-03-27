package seedu.modtrek.ui.modulesection;

import java.util.List;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Region;
import seedu.modtrek.logic.commands.SortCommand;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.ui.UiPart;
import seedu.modtrek.ui.resultssection.ResultsSection;

/**
 * A subsection within the ResultsSection (left panel) that displays all the modules in the user's
 * module list.
 */
public class ModuleListSection extends ModuleSection {
    /**
     * Instantiates a new Module list section.
     *
     * @param sortedLists the sorted lists
     */
    public ModuleListSection(TreeMap<? extends Object, ObservableList<Module>> sortedLists, String sort,
                             List<Supplier<TreeMap<? extends Object, ObservableList<Module>>>> sorters) {
        super();

        ModuleList moduleList = new ModuleList(sortedLists);
        moduleListPlaceholder.getChildren().add(moduleList.getRoot());

        ModuleSectionSortNav nav = new ModuleSectionSortNav(sort, sorters);
        moduleSectionNav.getChildren().add(nav.getRoot());
    }

    public void refreshList(TreeMap<? extends Object, ObservableList<Module>> newList) {
        moduleListPlaceholder.getChildren().clear();

        ModuleList moduleList = new ModuleList(newList);
        moduleListPlaceholder.getChildren().add(moduleList.getRoot());
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
        public ModuleSectionSortNav(String sort,
                                    List<Supplier<TreeMap<? extends Object, ObservableList<Module>>>> sorters) {
            super(FXML);

            sort = sort.substring(0, 1) + sort.toLowerCase().substring(1);
            sortDropdown.setValue(sort);

            setListeners(sorters);
        }

        private void setListeners(List<Supplier<TreeMap<? extends Object, ObservableList<Module>>>> sorters) {
            sortDropdown.setOnAction((event) -> {
                int selectedIndex = sortDropdown.getSelectionModel().getSelectedIndex();
                ModuleListSection.this.refreshList(sorters.get(selectedIndex).get());
            });
        }
    }
}

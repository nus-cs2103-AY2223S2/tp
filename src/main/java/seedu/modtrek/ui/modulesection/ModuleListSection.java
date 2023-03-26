package seedu.modtrek.ui.modulesection;

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
    /**
     * Instantiates a ModuleListSection.
     * @param modules The modules to display in the section.
     */
    public ModuleListSection(ObservableList<Module> modules) {
        super();

        ModuleList moduleList = new ModuleList(modules, false);
        moduleListPlaceholder.getChildren().add(moduleList.getRoot());

        ModuleSectionSortNav nav = new ModuleSectionSortNav();
        moduleSectionNav.getChildren().add(nav.getRoot());
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

        public ModuleSectionSortNav(/* Logic logic */) {
            super(FXML);

            setListeners(/* Logic logic */);
        }

        private void setListeners(/* Logic logic */) {
            sortDropdown.setOnAction((event) -> {
                int selectedIndex = sortDropdown.getSelectionModel().getSelectedIndex();
                String selectedItem = selection[selectedIndex];

                switch (selectedItem) {
                case "Year":
                    // sort by year
                    break;
                case "Code":
                    // sort by code
                    break;
                case "Credits":
                    // sort by credits
                    break;
                case "Grade":
                    // sort by grade
                    break;
                case "Tag":
                    // sort by tag
                    break;
                default:
                    // should not reach here
                }
            });
        }
    }
}

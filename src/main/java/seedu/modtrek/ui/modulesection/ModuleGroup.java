package seedu.modtrek.ui.modulesection;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.ui.UiPart;

/**
 * A UI component comprising a group of ModuleCards.
 */
public class ModuleGroup extends UiPart<Region> {
    private static final String FXML = "modulesection/ModuleGroup.fxml";

    @FXML
    private VBox moduleGroup;

    @FXML
    private GridPane moduleCardGroup;

    /**
     * Instantiates a new ModuleGroup component.
     *
     * @param modules the list of modules for the semYear
     * @param title title of the ModuleGroup
     */
    public ModuleGroup(ObservableList<Module> modules, String title) {
        super(FXML);
        displayModuleCards(modules, title);
    }

    /**
     * Displays all ModuleCards within the ModuleGroup.
     * @param modules the list of modules to be displayed as ModuleCards in the ModuleGroup.
     */
    public void displayModuleCards(ObservableList<Module> modules, String title) {
        if (title.length() > 0) {
            Label groupLabel = new Label(title);
            groupLabel.getStyleClass().addAll("module-group-title", (
                    title.length() > 1 ? "module-group-title-long"
                            : "module-group-title-short"), "h4");
            moduleGroup.getChildren().add(0, groupLabel);
        }

        /*
        * Position variables to keep track of the current free position (col, row) to add a ModuleCard on the GridPane.
        *  ModuleCards are displayed on a `r by 3` grid, where r is the number of rows and 3 is the number of columns.
        *  E.g. If there are 5 ModuleCards, they will be displayed on a `2 by 3` grid.
        */
        int maxCols = 3;
        int col = 0;
        int row = 0;

        for (Module module : modules) {
            ModuleCard moduleCard = new ModuleCard(module);
            GridPane.setConstraints(moduleCard.getRoot(), col, row);
            moduleCardGroup.getChildren().add(moduleCard.getRoot());

            /* Add placeholder cards to set ModuleCard min width */
            if (col == 0) {
                ModuleCard placeholderCard1 = new ModuleCard();
                placeholderCard1.getRoot().getStyleClass().add("module-card-placeholder");
                GridPane.setConstraints(placeholderCard1.getRoot(), 1, row);

                ModuleCard placeholderCard2 = new ModuleCard();
                placeholderCard2.getRoot().getStyleClass().add("module-card-placeholder");
                GridPane.setConstraints(placeholderCard2.getRoot(), 2, row);

                moduleCardGroup.getChildren().addAll(placeholderCard1.getRoot(), placeholderCard2.getRoot());
            }

            /* Update the position variables */
            col += 1;
            if (col == maxCols) {
                col = 0;
                row += 1;
            }
        }
    }
}

package seedu.modtrek.ui.modulelist;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.ui.UiPart;

/**
 * Represents a module group comprising module cards.
 */
public class ModuleGroup extends UiPart<Region> {
    private static final String FXML = "modulelist/ModuleGroup.fxml";

    @FXML
    private Label moduleGroupTitle;

    @FXML
    private GridPane moduleCardGroup;

    /**
     * Instantiates a new ModuleGroup.
     *
     * @param sy the semYear
     * @param modules the list of modules for the semYear
     */
    public ModuleGroup(SemYear sy, ArrayList<Module> modules) {
        super(FXML);
        moduleGroupTitle.setText(sy.toString());
        displayModuleCards(modules);
    }

    /**
     * Displays all module cards within a module group.
     * @param modules the list of modules for the group
     */
    public void displayModuleCards(ArrayList<Module> modules) {

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

package seedu.modtrek.ui.module_list;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.ui.UiPart;

import java.util.ArrayList;
import java.util.HashSet;

public class ModuleGroup extends UiPart<Region> {
    private static final String FXML = "module_list/ModuleGroup.fxml";

    @FXML
    private Label moduleGroupTitle;

    @FXML
    private GridPane moduleCardGroup;

    public ModuleGroup(SemYear sy, ArrayList<Module> modules /* TODO: pass in module data instead, consisting of title and modules */) {
        super(FXML);
        moduleGroupTitle.setText(sy.toString());
        displayModuleCards(modules);
    }

    public void displayModuleCards(ArrayList<Module> modules) {
        int NUM_MODULES = modules.size();

        /*
        * Position variables to keep track of the current free position (col, row) to add a ModuleCard on the GridPane.
        *  ModuleCards are displayed on a `r by 3` grid, where r is the number of rows and 3 is the number of columns.
        *  E.g. If there are 5 ModuleCards, they will be displayed on a `2 by 3` grid.
        */
        int MAX_COLS = 3;
        int col = 0;
        int row = 0;

        for (int i = 0; i < NUM_MODULES; i++) {
            ModuleCard moduleCard = new ModuleCard(modules.get(i) /* new String[]{"FDN"} */);
            GridPane.setConstraints(moduleCard.getRoot(), col, row);

            /* Update the position variables */
            col += 1;
            if (col == MAX_COLS) {
                col = 0;
                row += 1;
            }

            moduleCardGroup.getChildren().add(moduleCard.getRoot());
        }
    }
}

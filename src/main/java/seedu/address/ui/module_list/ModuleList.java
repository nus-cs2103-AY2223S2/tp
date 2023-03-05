package seedu.address.ui.module_list;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.ui.UiPart;

public class ModuleList extends UiPart<Region> {
    private static final String FXML = "module_list/ModuleList.fxml";

    @FXML
    private VBox moduleList;

    public ModuleList() {
        super(FXML);
        displayModuleGroup();
    }

    public void displayModuleGroup() {
        // TODO: render ModuleGroup dynamically
        ModuleGroup moduleGroup1 = new ModuleGroup("Y1S1");
        moduleList.getChildren().add(moduleGroup1.getRoot());

        ModuleGroup moduleGroup2 = new ModuleGroup("Y1S2");
        moduleList.getChildren().add(moduleGroup2.getRoot());

        ModuleGroup moduleGroup3 = new ModuleGroup("Y2S1");
        moduleList.getChildren().add(moduleGroup3.getRoot());
    }
}

package seedu.modtrek.ui.modulesection;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.ui.UiPart;

public class ModuleSection extends UiPart<Region> {
    private static final String FXML = "modulesection/ModuleSection.fxml";

    @FXML
    private StackPane moduleListPlaceholder;

    public ModuleSection(ObservableList<Module> modules) {
        super(FXML);

        ModuleList moduleList = new ModuleList(modules);
        moduleListPlaceholder.getChildren().add(moduleList.getRoot());
    }
}

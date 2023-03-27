package seedu.modtrek.ui.modulesection;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.modtrek.ui.UiPart;

/**
 * A subsection within the ResultsSection (left panel) that displays modules
 */
public class ModuleSection extends UiPart<Region> {
    private static final String FXML = "modulesection/ModuleSection.fxml";

    protected ModuleList moduleList;

    @FXML
    protected HBox moduleSectionNav;

    @FXML
    protected StackPane moduleListPlaceholder;

    /**
     * Instantiates a ModuleSection.
     */
    public ModuleSection() {
        super(FXML);
    }
}

package seedu.modtrek.ui.graphicssection;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.ui.UiPart;
import seedu.modtrek.ui.modulelist.ModuleList;

/**
 * A UI component that displays the portion of a Graphical User Interface to showcase modules.
 */
public class GraphicsSection extends UiPart<Region> {
    private static final String FXML = "graphics_section/GraphicsSection.fxml";

    @FXML
    private Label sectionHeaderTitle;

    @FXML
    private Label sectionHeaderSubtitle;

    @FXML
    private VBox sectionBody;

    @FXML
    private GridPane moduleList;

    /**
     * Creates a {@code CliSection} with the given {@code ObservableList<Module>}.
     */
    public GraphicsSection(ObservableList<Module> modules) {
        super(FXML);
        displayModuleList(modules);
    }

    // TODO: next iteration
    public void displayProgress() {
    }

    /**
     * Displays with the updated list of modules.
     *
     * @param modules the list of modules
     */
    public void displayModuleList(ObservableList<Module> modules) {
        sectionHeaderTitle.setText("Your Modules");
        sectionHeaderSubtitle.setText("in total");

        ModuleList moduleList = new ModuleList(modules);
        sectionBody.getChildren().clear();
        sectionBody.getChildren().add(moduleList.getRoot());
    }
}

package seedu.address.ui.graphics_section;

import javafx.fxml.FXML;

import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import seedu.address.ui.UiPart;
import seedu.address.ui.module_list.ModuleList;

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

    public GraphicsSection() {
        super(FXML);
        displayModuleList();
    }

    // TODO: next iteration
    public void displayProgress() {
    }

    public void displayModuleList(/* SomeKindOfList<Modules> modules */) {
        sectionHeaderTitle.setText("Your Modules");
        sectionHeaderSubtitle.setText("in total");

        // TODO: render ModuleList dynamically
        ModuleList moduleList = new ModuleList();
        sectionBody.getChildren().add(moduleList.getRoot());
    }
}

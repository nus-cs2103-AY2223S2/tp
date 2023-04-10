package seedu.vms.ui.vaccination;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.vms.ui.TagFlowView;
import seedu.vms.ui.UiPart;


/**
 * Graphical representation of a requirement and its associated index.
 */
public class RequirementBox extends UiPart<HBox> {
    private static final String FXML_FILE = "RequirementBox.fxml";

    @FXML private Label indexLabel;
    @FXML private VBox tagBox;


    /**
     * Constructs a {@code RequirementBox}.
     */
    public RequirementBox(int index, TagFlowView tagView) {
        super(FXML_FILE);
        indexLabel.setText(String.format("%d.", index + 1));
        tagBox.getChildren().add(tagView);
    }
}

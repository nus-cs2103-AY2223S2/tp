package seedu.vms.ui.vaccination;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.vms.model.vaccination.Requirement;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.ui.TagFlowView;
import seedu.vms.ui.UiPart;


/**
 * Graphical representation of a vaccination type.
 */
public class VaxTypeCard extends UiPart<Region> {
    private static final String FXML_FILE = "VaxTypeCard.fxml";
    private static final String STYLE_CLASS_ALL_TAG = "tag-color-all";
    private static final String STYLE_CLASS_ANY_TAG = "tag-color-any";
    private static final String STYLE_CLASS_NONE_TAG = "tag-color-none";

    @FXML private Label titleLabel;
    @FXML private Label ageRangeLabel;
    @FXML private Label spacingLabel;
    @FXML private VBox groupBox;
    @FXML private VBox allergyBox;
    @FXML private VBox historyBox;


    /**
     * Constructs a {@code VaxTypeCard}.
     *
     * @param vaxType - the vaccination type to display.
     */
    public VaxTypeCard(VaxType vaxType) {
        super(FXML_FILE);
        titleLabel.setText(vaxType.getName());
        ageRangeLabel.setText(String.format("%d ~ %d",
                vaxType.getMinAge(),
                vaxType.getMaxAge()));
        spacingLabel.setText(String.valueOf(vaxType.getMinSpacing()));
        groupBox.getChildren().add(new TagFlowView(vaxType.getGroups()));
        addAllReq(allergyBox, vaxType.getAllergyReqs());
        addAllReq(historyBox, vaxType.getHistoryReqs());
    }


    private void addAllReq(VBox box, List<Requirement> reqs) {
        for (Requirement req : reqs) {
            List<String> styleClasses = List.of();
            switch (req.getReqType()) {
            case ALL:
                styleClasses = List.of(STYLE_CLASS_ALL_TAG);
                break;
            case ANY:
                styleClasses = List.of(STYLE_CLASS_ANY_TAG);
                break;
            case NONE:
                styleClasses = List.of(STYLE_CLASS_NONE_TAG);
                break;
            default:
                throw new AssertionError(String.format("Unrecognized requirement type"));
            }
            box.getChildren().add(new TagFlowView(req.getReqSet(), styleClasses));
        }
    }
}

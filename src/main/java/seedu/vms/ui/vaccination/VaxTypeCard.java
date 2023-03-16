package seedu.vms.ui.vaccination;

import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.vms.model.GroupName;
import seedu.vms.model.vaccination.Requirement;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.ui.TagFlowView;
import seedu.vms.ui.UiPart;


/**
 * Graphical representation of a vaccination type.
 */
public class VaxTypeCard extends UiPart<Region> {
    private static final String FXML_FILE = "VaxTypeCard.fxml";

    @FXML private Label titleLabel;
    @FXML private Label ageRangeLabel;
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
        ageRangeLabel.setText(String.format("%s ~ %s",
                vaxType.getMinAge(),
                vaxType.getMaxAge()));
        groupBox.getChildren().add(new TagFlowView(vaxType.getGroups()
                .stream()
                .map(GroupName::getName)
                .collect(Collectors.toList())));
        allergyBox.getChildren().add(new TagFlowView(
                vaxType.getIngredients()
                    .stream()
                    .map(GroupName::toString)
                    .collect(Collectors.toList()),
                TagFlowView.STYLE_CLASS_TAG_RED));
        addAllReq(historyBox, vaxType.getHistoryReqs());
    }


    private void addAllReq(VBox box, List<Requirement> reqs) {
        for (Requirement req : reqs) {
            List<String> styleClasses = List.of();
            switch (req.getReqType()) {
            case ALL:
                styleClasses = List.of(TagFlowView.STYLE_CLASS_TAG_BLUE);
                break;
            case ANY:
                styleClasses = List.of(TagFlowView.STYLE_CLASS_TAG_GREEN);
                break;
            case NONE:
                styleClasses = List.of(TagFlowView.STYLE_CLASS_TAG_RED);
                break;
            default:
                throw new AssertionError(String.format("Unrecognized requirement type"));
            }
            box.getChildren().add(new TagFlowView(
                    req.getReqSet()
                            .stream()
                            .map(GroupName::getName)
                            .collect(Collectors.toList()),
                    styleClasses));
        }
    }
}

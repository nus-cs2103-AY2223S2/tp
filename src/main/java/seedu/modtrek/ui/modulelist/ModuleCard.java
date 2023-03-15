package seedu.modtrek.ui.modulelist;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.tag.Tag;
import seedu.modtrek.model.tag.ValidTag;
import seedu.modtrek.ui.UiPart;

/**
 * Represents a module card storing module details for a semYear.
 */
public class ModuleCard extends UiPart<Region> {
    private static final String FXML = "modulelist/ModuleCard.fxml";

    @FXML
    private Label moduleCardCode;

    @FXML
    private Label moduleCardCredits;

    @FXML
    private Label moduleCardGrade;

    @FXML
    private FlowPane moduleCardTagGroup;

    /**
     * Instantiates a new ModuleCard.
     *
     * @param module the module
     */
    public ModuleCard(Module module) {
        super(FXML);

        moduleCardCode.setText(module.getCode().toString());
        moduleCardCredits.setText(module.getCredit() + "MC");

        String grade = module.getGrade().toString();
        moduleCardGrade.setText(!grade.isEmpty() ? grade : "–");

        for (Tag tag : module.getTags()) {
            switch (ValidTag.valueOf(tag.tagName)) {
            // TODO: See if you can make use of the new ValidTag enum :)
            case UNIVERSITY_LEVEL_REQUIREMENTS:
                addTag("ULR", tag.tagName.toString(), "red");
                break;
            case COMPUTER_SCIENCE_FOUNDATION:
                addTag("CSF", tag.tagName.toString(), "blue");
                break;
            case COMPUTER_SCIENCE_BREADTH_AND_DEPTH:
                addTag("CSBD", tag.tagName.toString(), "green");
                break;
            case IT_PROFESSIONALISM:
                addTag("ITP", tag.tagName.toString(), "yellow");
                break;
            case MATHEMATICS_AND_SCIENCES:
                addTag("MS", tag.tagName.toString(), "purple");
                break;
            case UNRESTRICTED_ELECTIVES:
                addTag("UE", tag.tagName.toString(), "orange");
                break;
            default:
            }
        }
    }

    /**
     * Instantiates a new placeholder ModuleCard. Purpose of this placeholder is to set the
     * max width of the actual ModuleCards in ModuleGroup.
     */
    public ModuleCard() {
        super(FXML);
    }

    private void addTag(String tagName, String tagFull, String tagColor) {
        Label tag = new Label(tagName);
        tag.getStyleClass().addAll("module-card-tag", "module-card-tag-" + tagColor, "p2");
        moduleCardTagGroup.getChildren().add(tag);

        /* Add a helper text to moduleCardTag to show full description of tag, on hover. */
        Tooltip tagToolTip = new Tooltip(tagFull);
        Tooltip.install(tag, tagToolTip);
        tagToolTip.setShowDelay(Duration.seconds(0.05));
    }
}

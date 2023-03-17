package seedu.modtrek.ui.modulesection;

import static seedu.modtrek.model.tag.Tag.ValidTag.COMPUTER_SCIENCE_BREADTH_AND_DEPTH;
import static seedu.modtrek.model.tag.Tag.ValidTag.COMPUTER_SCIENCE_FOUNDATION;
import static seedu.modtrek.model.tag.Tag.ValidTag.IT_PROFESSIONALISM;
import static seedu.modtrek.model.tag.Tag.ValidTag.MATHEMATICS_AND_SCIENCES;
import static seedu.modtrek.model.tag.Tag.ValidTag.UNIVERSITY_LEVEL_REQUIREMENTS;
import static seedu.modtrek.model.tag.Tag.ValidTag.UNRESTRICTED_ELECTIVES;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.tag.Tag;
import seedu.modtrek.model.tag.Tag.ValidTag;
import seedu.modtrek.ui.UiPart;

/**
 * Represents a module card storing module details for a semYear.
 */
public class ModuleCard extends UiPart<Region> {
    private static final String FXML = "modulesection/ModuleCard.fxml";

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

        // TODO: call from Tag enum
        for (Tag tag : module.getTags()) {
            switch (ValidTag.valueOf(tag.tagName)) {
            case UNIVERSITY_LEVEL_REQUIREMENTS:
                addTag("ULR", UNIVERSITY_LEVEL_REQUIREMENTS.toString(), "red");
                break;
            case COMPUTER_SCIENCE_FOUNDATION:
                addTag("CSF", COMPUTER_SCIENCE_FOUNDATION.toString(), "blue");
                break;
            case COMPUTER_SCIENCE_BREADTH_AND_DEPTH:
                addTag("CSBD", COMPUTER_SCIENCE_BREADTH_AND_DEPTH.toString(), "green");
                break;
            case IT_PROFESSIONALISM:
                addTag("ITP", IT_PROFESSIONALISM.toString(), "yellow");
                break;
            case MATHEMATICS_AND_SCIENCES:
                addTag("MS", MATHEMATICS_AND_SCIENCES.toString(), "purple");
                break;
            case UNRESTRICTED_ELECTIVES:
                addTag("UE", UNRESTRICTED_ELECTIVES.toString(), "orange");
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

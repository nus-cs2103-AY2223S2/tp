package seedu.modtrek.ui.module_list;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.tag.Tag;
import seedu.modtrek.ui.UiPart;

public class ModuleCard extends UiPart<Region> {
    private static final String FXML = "module_list/ModuleCard.fxml";
    private enum ValidTag {
        UNIVERSITY_LEVEL_REQUIREMENTS,
        COMPUTER_SCIENCE_FOUNDATION,
        COMPUTER_SCIENCE_BREADTH_AND_DEPTH,
        IT_PROFESSIONALISM,
        MATHEMATICS_AND_SCIENCES,
        UNRESTRICTED_ELECTIVES
    }

    @FXML
    private Label moduleCardCode;

    @FXML
    private Label moduleCardCredits;

    @FXML
    private Label moduleCardGrade;

    @FXML
    private FlowPane moduleCardTagGroup;

    public ModuleCard(Module module /* TODO: replace with Module object */) {
        super(FXML);

        moduleCardCode.setText(module.getCode().toString());
        moduleCardCredits.setText(module.getCredit() + "MC");
        moduleCardGrade.setText(module.getGrade().toString());

        for (Tag tag : module.getTags()) {
            String tagName = "";
            switch (ValidTag.valueOf(tag.tagName)) {
            case UNIVERSITY_LEVEL_REQUIREMENTS:
                tagName = "ULR";
            case COMPUTER_SCIENCE_FOUNDATION:
                tagName = "CSF";
            case COMPUTER_SCIENCE_BREADTH_AND_DEPTH:
                tagName = "CSBD";
            case IT_PROFESSIONALISM:
                tagName = "ITP";
            case MATHEMATICS_AND_SCIENCES:
                tagName = "MS";
            case UNRESTRICTED_ELECTIVES:
                tagName = "UE";
            default:
            }
            addTag(tagName, "red");
        }
    }

    private void addTag(String tagName, String tagColor /* TODO: change to enums */) {
        Label tag = new Label(tagName);
        tag.getStyleClass().addAll("module-card-tag", "module-card-tag-" + tagColor, "p2");
        moduleCardTagGroup.getChildren().add(tag);

        /* Add a helper text to moduleCardTag to show full description of tag, on hover. */
        Tooltip tagToolTip = new Tooltip("Computer Science Foundation");
        Tooltip.install(tag, tagToolTip);
        tagToolTip.setShowDelay(Duration.seconds(0.05));
    }
}

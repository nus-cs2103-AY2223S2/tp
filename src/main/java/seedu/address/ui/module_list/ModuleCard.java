package seedu.address.ui.module_list;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import seedu.address.ui.UiPart;

public class ModuleCard extends UiPart<Region> {
    private static final String FXML = "module_list/ModuleCard.fxml";

    @FXML
    private Label moduleCardCode;

    @FXML
    private Label moduleCardCredits;

    @FXML
    private Label moduleCardGrade;

    @FXML
    private FlowPane moduleCardTagGroup;

    public ModuleCard(String code, String credits, String grade, String[] tags /* TODO: replace with Module object */) {
        super(FXML);

        moduleCardCode.setText(code);
        moduleCardCredits.setText(credits + "MC");
        moduleCardGrade.setText(grade);

        for (int i = 0; i < tags.length; i++) {
            String tagName = tags[i];
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

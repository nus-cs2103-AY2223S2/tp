package seedu.modtrek.ui.modulesection;

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
 * A UI component storing details of a particular module.
 */
public class ModuleCard extends UiPart<Region> {
    private static final String FXML = "modulesection/ModuleCard.fxml";

    @FXML
    private Label moduleCardCode;

    @FXML
    private Label moduleCardYearSem;

    @FXML
    private Label moduleCardCredits;

    @FXML
    private Label moduleCardGrade;

    @FXML
    private FlowPane moduleCardTagGroup;

    /**
     * Instantiates a new ModuleCard component.
     * @param module the module object that encapsulates module information to be
     *               displayed as a ModuleCard component.
     */
    public ModuleCard(Module module) {
        super(FXML);

        if (!module.isComplete()) {
            addIncompleteStyles();
        }

        fillCard(module);
    }

    /**
     * Instantiates a new placeholder ModuleCard component. Purpose of this placeholder is to set the
     * max width of the actual ModuleCards in ModuleGroup.
     */
    public ModuleCard() {
        super(FXML);
    }

    /**
     * Populates the ModuleCard with its information (module code, credits, semyear, grade, tags)
     * @param module The module object that encapsulates the module information.
     */
    private void fillCard(Module module) {
        moduleCardCode.setText(module.getCode().toString());

        moduleCardYearSem.setText(module.getSemYear().toString());

        moduleCardCredits.setText(module.getCredit() + "MC");

        String grade = module.getGrade().toString();
        moduleCardGrade.setText(!grade.equals("NO GRADE") ? grade : "–");

        for (Tag tag : module.getTags()) {
            String tagNameShort = ValidTag.getShortForm(tag.tagName).toString();
            String tagNameLong = tag.toString();
            String color = ValidTag.getColor(tag.tagName);
            addTag(tagNameShort, tagNameLong, color);
        }
    }

    /**
     * Adds a label to the ModuleCard that corresponds to a given tag.
     * @param tagNameShort The short form of the tag name.
     * @param tagNameLong The full tag name in long form.
     * @param tagColor The color of the tag.
     */
    private void addTag(String tagNameShort, String tagNameLong, String tagColor) {
        Label tag = new Label(tagNameShort);
        tag.getStyleClass().addAll("module-card-tag", "module-card-tag-" + tagColor, "p2");
        moduleCardTagGroup.getChildren().add(tag);

        /* Add a helper text to moduleCardTag to show full description of tag, on hover. */
        Tooltip tagToolTip = new Tooltip(tagNameLong);
        Tooltip.install(tag, tagToolTip);
        tagToolTip.setShowDelay(Duration.seconds(0.05));
    }

    /**
     * Adds CSS styles to ModuleCard for the case where module is incomplete.
     */
    private void addIncompleteStyles() {
        this.getRoot().getStyleClass().add("module-card-incomplete");

        Tooltip incompleteWarning = new Tooltip("This module is incomplete!");
        Tooltip.install(this.getRoot(), incompleteWarning);
        incompleteWarning.setShowDelay(Duration.seconds(0.05));
    }
}

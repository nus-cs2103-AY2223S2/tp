package codoc.ui.infopanel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import codoc.model.skill.Skill;
import codoc.ui.UiPart;

/**
 * An UI component that displays information of a {@code Skill}.
 */
public class SkillCard extends UiPart<Region> {

    private static final String FXML = "SkillListCard.fxml";

    public final Skill skill;

    @FXML
    private HBox skillCard;
    @FXML
    private Label name;
    @FXML
    private Label id;

    /**
     * Creates a {@code ModuleCard} with the given {@code Module} and index to display.
     */
    public SkillCard(Skill skill, int displayedIndex) {
        super(FXML);
        this.skill = skill;
        id.setText(displayedIndex + ". ");
        name.setText(skill.skillName);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCard)) {
            return false;
        }

        // state check
        SkillCard card = (SkillCard) other;
        return id.getText().equals(card.id.getText())
                && skill.equals(card.skill);
    }
}

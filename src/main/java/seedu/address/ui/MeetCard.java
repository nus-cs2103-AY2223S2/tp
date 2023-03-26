package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

public class MeetCard extends UiPart<Region> {
    private static final String FXML = "MeetListCard.fxml";
    private static final String GROUP_TAG_STYLE = "-fx-text-fill: black; -fx-background-color: rgb(227, 211, 238); "
            + "-fx-padding: 2 5 2 5; -fx-background-radius: 5;";
    private static final String MODULE_TAG_STYLE = "-fx-text-fill: #FFFFFF; -fx-background-color: rgb(150, 146, 223); "
            + "-fx-padding: 2 5 2 5; -fx-background-radius: 5;";

    public final Person person;

    @javafx.fxml.FXML
    private HBox meetCardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;

    @FXML
    private FlowPane allTags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public MeetCard(Person person) {
        super(FXML);
        this.person = person;
        id.setText(person.getContactIndex() + ". ");
        name.setText(person.getName().getValue());
        person.getImmutableGroupTags().stream()
                .sorted(GroupTag::compareTo)
                .forEach(groupTag -> {
                    Button temp = new Button(groupTag.tagName);
                    temp.setStyle(GROUP_TAG_STYLE);
                    allTags.getChildren().add(temp);
                });
        person.getImmutableCommonModuleTags().stream()
                .sorted(ModuleTag::compareTo)
                .forEach(moduleTag -> {
                    Button temp = new Button(moduleTag.tagName);
                    temp.setStyle(MODULE_TAG_STYLE);
                    allTags.getChildren().add(temp);
                });
        allTags.setHgap(4.0);
        allTags.setVgap(4.0);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetCard)) {
            return false;
        }

        // state check
        MeetCard card = (MeetCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}

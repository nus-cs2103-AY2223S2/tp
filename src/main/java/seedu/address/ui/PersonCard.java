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

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final String GROUP_TAG_STYLE = "-fx-text-fill: black; -fx-background-color: rgb(227, 211, 238); "
            + "-fx-padding: 2 5 2 5; -fx-background-radius: 5;";
    private static final String MODULE_TAG_STYLE = "-fx-text-fill: #FFFFFF; -fx-background-color: rgb(150, 146, 223); "
            + "-fx-padding: 2 5 2 5; -fx-background-radius: 5;";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on EduMate level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;

    @FXML
    private FlowPane allTags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person) {
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
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}

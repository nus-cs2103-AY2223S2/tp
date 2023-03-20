package arb.ui.tag;

import arb.model.tag.Tag;
import arb.ui.UiPart;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * An UI component that displays information of a {@code Tag}.
 */
public class TagCard extends UiPart<Region> {

    private static final String FXML = "tag/TagListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Tag tag;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox contentsPane;;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label numberOfClientsTagged;
    @FXML
    private Label numberOfProjectsTagged;

    /**
     * Creates a {@code TagCard} with the given {@code Tag} and index to display.
     */
    public TagCard(Tag tag, int displayedIndex) {
        super(FXML);
        this.tag = tag;
        id.setText(displayedIndex + ". ");
        name.setText(tag.tagName);
        numberOfClientsTagged.setText("Number of clients tagged: " + tag.getNumberOfClientsTagged());
        numberOfProjectsTagged.setText("Number of projects tagged: " + tag.getNumberOfProjectsTagged());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCard)) {
            return false;
        }

        // state check
        TagCard card = (TagCard) other;
        return id.getText().equals(card.id.getText())
                && tag.equals(card.tag);
    }
}

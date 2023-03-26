package arb.ui.tag;

import arb.model.tag.TagMapping;
import arb.ui.UiPart;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * An UI component that displays information of a {@code TagMapping}.
 */
public class TagMappingCard extends UiPart<Region> {

    private static final String FXML = "tag/TagMappingListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final TagMapping tagMapping;

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
     * Creates a {@code TagMappingCard} with the given {@code TagMapping} and index to display.
     */
    public TagMappingCard(TagMapping tagMapping, int displayedIndex) {
        super(FXML);
        this.tagMapping = tagMapping;
        id.setText(displayedIndex + ". ");
        name.setText(tagMapping.getTag().tagName);
        numberOfClientsTagged.setText("Number of clients tagged: " + tagMapping.getNumberOfClientsTagged());
        numberOfProjectsTagged.setText("Number of projects tagged: " + tagMapping.getNumberOfProjectsTagged());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagMappingCard)) {
            return false;
        }

        // state check
        TagMappingCard card = (TagMappingCard) other;
        return id.getText().equals(card.id.getText())
                && tagMapping.equals(card.tagMapping);
    }
}

package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.tag.Tag;

/**
 * A UI component that displays all information about a Doctor or Patient.
 */
abstract class EnlargedInfoCard extends UiPart<Region> {

    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code EnlargedInfoCard}
     */
    public EnlargedInfoCard(String fxmlFilePath) {
        super(fxmlFilePath);
    }

    /**
     * Adds a tag to the flow pane containing all tags.
     *
     * @param tag a tag.
     */
    protected void addTagToFlowPane(Tag tag) {
        TagLabel tagLabel = new TagLabel(tag);
        tags.getChildren().add(tagLabel);
    }

    /**
     * Clears all tags in the flow pane.
     */
    protected void clearTags() {
        tags.getChildren().clear();
    }

}

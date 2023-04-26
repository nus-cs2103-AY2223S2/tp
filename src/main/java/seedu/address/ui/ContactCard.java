package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.tag.Tag;

/**
 * A UI component that displays key information about a Doctor or Patient
 * within a list view.
 */
abstract class ContactCard extends UiPart<Region> {

    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ContactCard}
     */
    public ContactCard(String fxmlFilePath) {
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

}

package seedu.address.ui;


import javafx.scene.control.Label;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays the tag of a doctor or patient.
 */
public class TagLabel extends Label {

    private static final String FXML = "TagLabel.fxml";

    /**
     * Creates a {@code TagLabel}.
     */
    public TagLabel(Tag tag) {
        super(FXML);
        this.setText(tag.getTagName());
    }
}

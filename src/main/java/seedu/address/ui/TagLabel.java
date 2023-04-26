package seedu.address.ui;


import javafx.scene.control.Label;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays the tag of a doctor or patient.
 */
public class TagLabel extends Label {

    /**
     * Creates a {@code TagLabel}.
     */
    public TagLabel(Tag tag) {
        this.setText(tag.getTagName());
        double tagMaxWidth = 150.0;
        this.setMaxWidth(tagMaxWidth);
        boolean tagWrapText = true;
        this.setWrapText(tagWrapText);
    }
}

package seedu.address.ui;


import javafx.scene.control.Label;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays the tag of a doctor or patient.
 */
public class TagLabel extends Label {

    private double tagMaxWidth = 150.0;
    private boolean tagWrapText = true;

    /**
     * Creates a {@code TagLabel}.
     */
    public TagLabel(Tag tag) {
        this.setText(tag.getTagName());
        this.setMaxWidth(tagMaxWidth);
        this.setWrapText(tagWrapText);
    }
}

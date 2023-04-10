package seedu.address.ui.detail;

import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * An abstract class that represents the detailed content of a student.
 */
public abstract class DetailedContent extends UiPart<Region> {
    protected static final int MAX_LINE_LENGTH = 35;

    public DetailedContent(String fxml) {
        super(fxml);
    }
}

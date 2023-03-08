package seedu.address.ui.infoPanel;

import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * Represents DetailedInfoPanel which is the lower part of the InfoPanel.
 */
public abstract class DetailedInfo extends UiPart<Region> {

    /**
     * Creates a {@code DetailedInfoPanel} with the given {@code fxmlFileUrl}.
     */
    public DetailedInfo(String fxmlFileUrl) {
        super(fxmlFileUrl);
    }

}

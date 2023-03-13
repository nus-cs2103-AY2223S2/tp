package codoc.ui.infopanel;

import javafx.scene.layout.Region;
import codoc.ui.UiPart;

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

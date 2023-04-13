package mycelium.mycelium.ui.statusbarfooter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.ui.UiPart;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {
    private static final String FXML = "StatusBarFooter.fxml";

    private Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private Label saveLocationStatus;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarFooter(Path saveLocation) {
        super(FXML);
        saveLocationStatus.setText(Paths.get(".").resolve(saveLocation).toString());
        logger.fine("Initialized status bar with save location: " + saveLocation.toString());
    }

}

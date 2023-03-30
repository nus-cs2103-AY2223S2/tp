package wingman.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class MenuBar extends UiPart<Region> {

    private static final String FXML = "MenuBar.fxml";

    @FXML
    private Label modeStatus;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public MenuBar() {
        super(FXML);
        modeStatus.setText("Flight                                 Crew                                   Plane"
                + "       " + "                          Pilot                                   "
                + "Location                                 ");
    }

}

package seedu.medinfo.ui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";

    @FXML
    private Label saveLocationStatus;
    @FXML
    private Label stats;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarFooter(Path saveLocation, List<String> statsInfo) {
        super(FXML);
        statsInfo.get(0);
        statsInfo.get(1);
        this.saveLocationStatus.setText(Paths.get(".").resolve(saveLocation).toString());
        for (String stat:statsInfo) {
            stats.setText(stats.getText() + "       " + stat + "       ");
        }
    }

}

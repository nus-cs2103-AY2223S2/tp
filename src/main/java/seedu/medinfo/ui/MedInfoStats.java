package seedu.medinfo.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

import static java.util.Objects.requireNonNull;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class MedInfoStats extends UiPart<Region> {

    private static final String FXML = "MedInfoStats.fxml";

    @FXML
    private Label currentOccupancy;
    @FXML
    private Label numberOfCritical;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public MedInfoStats(String totalOccupancy, String numberOfCritical) {
        super(FXML);
        this.currentOccupancy.setText(totalOccupancy);
        this.numberOfCritical.setText(numberOfCritical);
    }

    public void updateOccupancy(String newOccupancy) {
        requireNonNull(newOccupancy);
        currentOccupancy.setText(newOccupancy);
    }
    public void updateNumberOfCritical(String newNumberOfCritical) {
        requireNonNull(newNumberOfCritical);
        numberOfCritical.setText(newNumberOfCritical);
    }


}

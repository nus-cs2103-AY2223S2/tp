package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import seedu.address.model.pilot.Pilot;

/**
 * A generic view for a flight.
 */
public class PilotCard extends UiPart<VBox> {
    private static final String FXML = "FlightCard.fxml";
    private final Pilot pilot;
    private final int displayedIndex;

    @FXML
    private VBox cardPane;

    @FXML
    private Label id;

    /**
     * Creates a view for the given pilot. The pilot is an identifiable object
     * that can be displayed in a list.
     *
     * @param pilot The pilot to be displayed.
     */
    public PilotCard(Pilot pilot, int displayedIndex) {
        super(FXML);
        this.pilot = pilot;
        this.displayedIndex = displayedIndex - 1;
        for (String line : pilot.getDisplayList()) {
            Label label = new Label(line);
            cardPane.getChildren().add(label);

        id.setText(displayedIndex + ". ");
        }
    }

    /**
     * Returns the pilot that is being displayed.
     *
     * @return The pilot that is being displayed.
     */
    public Pilot getFlight() {
        return pilot;
    }
}

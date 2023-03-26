package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import seedu.address.model.location.Location;

/**
 * A generic view for a location.
 */
public class LocationCard extends UiPart<VBox> {
    private static final String FXML = "LocationCard.fxml";
    private final Location location;

    @FXML
    private VBox cardPane;

    /**
     * Creates a view for the given location. The location is an identifiable object
     * that can be displayed in a list.
     *
     * @param location The location to be displayed.
     */
    public LocationCard(Location location) {
        super(FXML);
        this.location = location;
        for (String line : location.getDisplayList()) {
            Label label = new Label(line);
            cardPane.getChildren().add(label);
        }
    }

    /**
     * Returns the location that is being displayed.
     *
     * @return The location that is being displayed.
     */
    public Location getLocation() {
        return location;
    }
}

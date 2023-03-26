package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import seedu.address.model.plane.Plane;

/**
 * A generic view for a plane.
 */
public class PlaneCard extends UiPart<VBox> {
    private static final String FXML = "PlaneCard.fxml";
    private final Plane plane;

    @FXML
    private VBox cardPane;

    /**
     * Creates a view for the given plane. The plane is an identifiable object
     * that can be displayed in a list.
     *
     * @param plane The plane to be displayed.
     */
    public PlaneCard(Plane plane) {
        super(FXML);
        this.plane = plane;
        for (String line : plane.getDisplayList()) {
            Label label = new Label(line);
            cardPane.getChildren().add(label);
        }
    }

    /**
     * Returns the plane that is being displayed.
     *
     * @return The plane that is being displayed.
     */
    public Plane getPlane() {
        return plane;
    }
}

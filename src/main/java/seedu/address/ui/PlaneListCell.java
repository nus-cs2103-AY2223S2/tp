package seedu.address.ui;

import javafx.scene.control.ListCell;
import seedu.address.model.item.Item;

/**
 * The cell that displays the planes in the list.
 *
 * @param <Plane> The type of Item to display.
 */
public class PlaneListCell<Plane extends Item> extends ListCell<Plane> {

    private static final String FXML = "PlaneListCell.fxml";

    @Override
    protected void updateItem(Plane plane, boolean empty) {
        super.updateItem(plane, empty);
        if (empty || plane == null) {
            setGraphic(null);
            setText(null);
        } else {
            setGraphic(new PlaneCard((seedu.address.model.plane.Plane) plane).getRoot());
        }
    }
}

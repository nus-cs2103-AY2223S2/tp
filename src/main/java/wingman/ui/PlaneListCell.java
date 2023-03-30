package wingman.ui;

import javafx.scene.control.ListCell;
import wingman.model.item.Item;

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
            setGraphic(new PlaneCard((wingman.model.plane.Plane) plane, getIndex() + 1).getRoot());
        }
    }
}

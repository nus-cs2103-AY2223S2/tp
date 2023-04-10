package wingman.ui;

import javafx.scene.control.ListCell;
import wingman.model.item.Item;

/**
 * The cell that displays the pilots in the list.
 *
 * @param <Pilot> The type of Item to display.
 */
public class PilotListCell<Pilot extends Item> extends ListCell<Pilot> {

    private static final String FXML = "PilotListCell.fxml";

    @Override
    protected void updateItem(Pilot pilot, boolean empty) {
        super.updateItem(pilot, empty);
        if (empty || pilot == null) {
            setGraphic(null);
            setText(null);
        } else {
            setGraphic(new PilotCard((wingman.model.pilot.Pilot) pilot, getIndex() + 1).getRoot());
        }
    }
}

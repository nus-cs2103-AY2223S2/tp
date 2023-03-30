package wingman.ui;

import javafx.scene.control.ListCell;
import wingman.model.item.Item;

/**
 * The cell that displays the flights in the list.
 *
 * @param <Flight> The type of Item to display.
 */
public class FlightListCell<Flight extends Item> extends ListCell<Flight> {

    private static final String FXML = "FlightListCell.fxml";

    @Override
    protected void updateItem(Flight flight, boolean empty) {
        super.updateItem(flight, empty);
        if (empty || flight == null) {
            setGraphic(null);
            setText(null);
        } else {
            setGraphic(new FlightCard((wingman.model.flight.Flight) flight, getIndex() + 1).getRoot());
        }
    }
}

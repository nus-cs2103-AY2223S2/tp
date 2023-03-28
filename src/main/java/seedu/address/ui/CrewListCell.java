package seedu.address.ui;

import javafx.scene.control.ListCell;
import seedu.address.model.item.Item;

/**
 * The cell that displays the crew in the list.
 *
 * @param <Crew> The type of Item to display.
 */
public class CrewListCell<Crew extends Item> extends ListCell<Crew> {

    private static final String FXML = "CrewListCell.fxml";

    @Override
    protected void updateItem(Crew crew, boolean empty) {
        super.updateItem(crew, empty);
        if (empty || crew == null) {
            setGraphic(null);
            setText(null);
        } else {
            setGraphic(new CrewCard(
                    (seedu.address.model.crew.Crew) crew, getIndex() + 1)
                    .getRoot());
        }
    }
}

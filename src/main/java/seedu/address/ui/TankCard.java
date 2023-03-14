package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.tank.Tank;

/**
 * An UI component that displays information of a {@code Tank}.
 */
public class TankCard extends UiPart<Region> {

    private static final String FXML = "TankListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Tank tank;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;

    /**
     * Creates a {@code TankCard} with the given {@code Tank} and index to display.
     */
    public TankCard(Tank tank, int displayedIndex) {
        super(FXML);
        this.tank = tank;
        id.setText(displayedIndex + ". ");
        name.setText(tank.getTankName().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TankCard)) {
            return false;
        }

        // state check
        TankCard card = (TankCard) other;
        return id.getText().equals(card.id.getText())
                && tank.equals(card.tank);
    }
}

package seedu.address.ui.core;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import seedu.address.model.item.Item;
import seedu.address.ui.UiPart;

/**
 * A generic view for an item.
 */
public class ItemCard extends UiPart<VBox> {
    private static final String FXML = "ItemCard.fxml";
    private final Item item;

    @FXML
    private VBox cardPane;

    /**
     * Creates a view for the given item. The item is an identifiable object
     * that can be displayed in a list.
     *
     * @param item The item to be displayed.
     */
    public ItemCard(Item item) {
        super(FXML);
        this.item = item;
        for (String line : item.getDisplayList()) {
            Label label = new Label(line);
            cardPane.getChildren().add(label);
        }
    }

    /**
     * Returns the item that is being displayed.
     *
     * @return The item that is being displayed.
     */
    public Item getItem() {
        return item;
    }
}

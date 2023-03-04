package seedu.address.ui.core;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import seedu.address.model.item.Identifiable;

/**
 * A generic view for an item.
 */
public class ItemView extends VBox {
    private final Identifiable item;

    /**
     * Creates a view for the given item. The item is an identifiable object
     * that can be displayed in a list.
     *
     * @param item The item to be displayed.
     */
    public ItemView(Identifiable item) {
        super();
        this.item = item;
        for (String line : item.getDisplayList()) {
            getChildren().add(new Label(line));
        }
    }

    /**
     * Returns the item that is being displayed.
     *
     * @return The item that is being displayed.
     */
    public Identifiable getItem() {
        return item;
    }
}

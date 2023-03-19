package seedu.address.ui.core;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.item.Item;
import seedu.address.ui.UiPart;

/**
 * A generic panel for displaying a list of items.
 */
public class ItemListPanel extends UiPart<Region> {
    private static final String FXML = "ItemListPanel.fxml";

    private final Logger logger;

    @FXML
    private ListView<Item> itemList;

    /**
     * Creates a {@code ItemListPanel} with the given {@code ObservableList}.
     *
     * @param itemList The list of items to be displayed.
     */
    public ItemListPanel(ObservableList<Item> itemList) {
        this(LogsCenter.getLogger(ItemListPanel.class), itemList);
    }

    /**
     * Creates a {@code ItemListPanel} with the given {@code ObservableList}.
     *
     * @param logger   The logger to be used.
     * @param itemList The list of items to be displayed.
     */
    public ItemListPanel(Logger logger,
        ObservableList<Item> itemList) {
        super(FXML);
        this.logger = logger;
        this.itemList.setItems(itemList);
        this.itemList.setCellFactory(listView -> new ItemListCell<>());
    }
}

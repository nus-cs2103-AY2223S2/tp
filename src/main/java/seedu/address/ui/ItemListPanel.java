package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.entity.Item;

/**
 * Panel containing the list of persons.
 */
public class ItemListPanel extends UiPart<Region> {

    private static final String FXML = "ItemListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ItemListPanel.class);

    @FXML
    private ListView<Item> itemListView;

    @FXML
    private Label inventoryValueLabel;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ItemListPanel(ObservableList<Item> itemList) {
        super(FXML);
        itemListView.setItems(itemList);
        itemListView.setCellFactory(listView -> new ItemListViewCell());
    }

    /**
     * Updates item panel with new inventory items
     */
    public void updateItems(ObservableList<Item> newItemList) {
        itemListView.setItems(newItemList);
        updateInventoryValue(newItemList);
    }

    /**
     * Calculates total item value in inventoryList
     */
    public void updateInventoryValue(ObservableList<Item> itemList) {

        int totalCost = 0;
        for (Item item : itemList) {
            totalCost += item.getCost();
        }
        inventoryValueLabel.setText("Total Value: " + totalCost + "g");
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ItemListViewCell extends ListCell<Item> {

        @Override
        protected void updateItem(Item item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ItemCard(item, getIndex() + 1).getRoot());
            }
        }
    }

}

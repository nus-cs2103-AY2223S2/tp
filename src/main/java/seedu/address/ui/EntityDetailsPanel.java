package seedu.address.ui;

//import java.util.ArrayList;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;

/**
 * Panel containing the detailed view of the entity.
 */
public class EntityDetailsPanel extends UiPart<Region> {

    private static final String FXML = "EntityDetailsPanel.fxml";

    private ItemListPanel itemListPanel;

    @javafx.fxml.FXML
    private Label entityDetailsLabel;

    @javafx.fxml.FXML
    private Label entityNameLabel;

    @javafx.fxml.FXML
    private Label entityClassificationLabel;

    @javafx.fxml.FXML
    private StackPane inventoryPlaceholder;

    /**
     * Creates an entity details panel linked to items list.
     */
    public EntityDetailsPanel(ObservableList<Entity> itemList) {
        super(FXML);
        itemListPanel = new ItemListPanel(FXCollections.observableArrayList());
    }

    /**
     * Updates the detail panel with the specified entity.
     */
    public void updateEntityDetails(Entity entity) {
        entityNameLabel.setText(entity.getName().fullName);
        entityClassificationLabel.setText("[" + entity.getClass().getSimpleName() + "]");
        entityDetailsLabel.setText(entityFieldsToString(entity.getFields()));

        if (entity instanceof Item) { // Hide inventory panel
            if (inventoryPlaceholder.getChildren().size() > 0) {
                inventoryPlaceholder.getChildren().remove(0);
            }
            //itemListPanel
        } else if (entity instanceof Character) { // Show inventory panel
            ObservableList<Item> inventoryItems = FXCollections.observableList((
                    (Character) entity).getInventory().getItems());
            itemListPanel.updateItems(inventoryItems);
            if (inventoryPlaceholder.getChildren().size() == 0) {
                inventoryPlaceholder.getChildren().add(itemListPanel.getRoot());
            }
        } else if (entity instanceof Mob) {
            ObservableList<Item> inventoryItems = FXCollections.observableList((
                    (Mob) entity).getInventory().getItems());
            itemListPanel.updateItems(inventoryItems);
            if (inventoryPlaceholder.getChildren().size() == 0) {
                inventoryPlaceholder.getChildren().add(itemListPanel.getRoot());
            }
        }
    }

    private String entityFieldsToString(List<Pair<String, String>> fields) {
        final StringBuilder builder = new StringBuilder();
        for (Pair<String, String> p : fields) {
            String field = String.format("%s: %s\n", p.getKey(), p.getValue());
            builder.append(field);
        }
        return builder.toString();
    }
}

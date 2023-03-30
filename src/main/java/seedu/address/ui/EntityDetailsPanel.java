package seedu.address.ui;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
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
    private FlowPane inventoryPlaceholder;

    /**
     * Creates an entity details panel linked to items list.
     */
    public EntityDetailsPanel(ObservableList<Entity> itemList) {
        super(FXML);
        itemListPanel = new ItemListPanel(FXCollections.observableList(new ArrayList<Item>()));
    }

    /**
     * Updates the detail panel with the specified entity.
     */
    public void updateEntityDetails(Entity entity) {
        System.out.println("setting name :" + entity.getName());
        entityNameLabel.setText(entity.getName().fullName);
        entityClassificationLabel.setText("[" + entity.getClass().getSimpleName() + "]");
        entityDetailsLabel.setText(entity.toString());

        if (entity instanceof Item) { // Hide inventory panel
            if (inventoryPlaceholder.getChildren().size() > 0) {
                inventoryPlaceholder.getChildren().remove(0);
            }
            itemListPanel.getRoot().setManaged(false);
            itemListPanel.getRoot().setVisible(false);
            //itemListPanel
        } else if (entity instanceof Character) { // Show inventory panel
            ObservableList<Item> inventoryItems = FXCollections.observableList((
                    (Character) entity).getInventory().getItems());
            itemListPanel = new ItemListPanel(inventoryItems);
            inventoryPlaceholder.getChildren().add(itemListPanel.getRoot());
            itemListPanel.getRoot().setManaged(true);
            itemListPanel.getRoot().setVisible(true);
        } else if (entity instanceof Mob) {
            ObservableList<Item> inventoryItems = FXCollections.observableList((
                    (Mob) entity).getInventory().getItems());
            itemListPanel = new ItemListPanel(inventoryItems);
            inventoryPlaceholder.getChildren().add(itemListPanel.getRoot());
            itemListPanel.getRoot().setManaged(true);
            itemListPanel.getRoot().setVisible(true);
        }
    }
}

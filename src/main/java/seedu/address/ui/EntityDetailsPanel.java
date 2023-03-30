package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Item;

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
        itemListPanel = new ItemListPanel(itemList);
    }

    /**
     * Updates the detail panel with the specified entity.
     */
    public void updateEntityDetails(Entity entity) {
        entityNameLabel.setText(entity.getName().fullName);
        entityClassificationLabel.setText("[" + entity.getClass().getSimpleName() + "]");
        entityDetailsLabel.setText(entity.toString());

        if (entity instanceof Item) { // Hide inventory panel
            inventoryPlaceholder.getChildren().remove(0);
        } else { // Show inventory panel
            inventoryPlaceholder.getChildren().add(itemListPanel.getRoot());
        }
    }
}

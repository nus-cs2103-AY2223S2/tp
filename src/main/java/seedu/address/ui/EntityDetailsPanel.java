package seedu.address.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.entity.Entity;

/**
 * Panel containing the detailed view of the entity.
 */
public class EntityDetailsPanel extends UiPart<Region> {

    private static final String FXML = "EntityDetailsPanel.fxml";

    @javafx.fxml.FXML
    private Label entityDetailsLabel;

    @javafx.fxml.FXML
    private Label entityNameLabel;

    @javafx.fxml.FXML
    private Label entityClassificationLabel;

    public EntityDetailsPanel() {
        super(FXML);
    }

    /**
     * Updates the detail panel with the specified entity.
     */
    public void updateEntityDetails(Entity entity) {
        entityNameLabel.setText(entity.getName().fullName);
        entityClassificationLabel.setText(entity.getClass().getSimpleName());
        String detailsText = "";
        System.out.println(entity.toString());
        String[] entityDetails = entity.toString().split("\\|");
        for (int i = 1; i < entityDetails.length; i++) {
            System.out.println(entityDetails[i]);
            detailsText += entityDetails[i] + "\n";
        }
        entityDetailsLabel.setText(detailsText);
    }
}

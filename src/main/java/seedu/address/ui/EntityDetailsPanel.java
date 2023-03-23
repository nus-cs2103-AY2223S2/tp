package seedu.address.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.model.entity.Entity;

import java.util.List;

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
        entityClassificationLabel.setText("[" + entity.getClass().getSimpleName() + "]");
        final StringBuilder detailsText = new StringBuilder();
        List<Pair<String, String>> fields = entity.getFields();
        for (Pair<String, String> p : fields) {
            String text = String.format("%s: %s\n", p.getKey(), p.getValue());
            detailsText.append(text);
        }
        entityDetailsLabel.setText(detailsText.toString());
    }
}

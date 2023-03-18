package seedu.address.ui;

import java.util.Comparator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.entity.Entity;

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

    public void UpdateEntityDetails(Entity entity) {
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

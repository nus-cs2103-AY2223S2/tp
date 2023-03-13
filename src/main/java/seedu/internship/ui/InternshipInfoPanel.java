package seedu.internship.ui;

import static java.util.Objects.isNull;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.model.internship.Internship;

/**
 * A panel to display detailed information about an internship.
 */
public class InternshipInfoPanel extends UiPart<Region> {

    private static final String FXML = "InternshipInfoPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InternshipInfoPanel.class);

    private Internship internship;

    @FXML
    private GridPane infoPanel;
    @FXML
    private Label position;
    @FXML
    private Label company;
    @FXML
    private Label id;
    @FXML
    private Label status;
    @FXML
    private Label description;
    @FXML
    private FlowPane tags;
    @FXML
    private Separator separator;

    /**
     * Creates a {@code InternshipInfoPanel} displaying all information of an Internship.
     */
    public InternshipInfoPanel() {
        super(FXML);
        position.setText("Run 'view' command to view some internships.");
        position.setStyle("-fx-font-size: 14pt");
        company.setText("");
        status.setText("");
        description.setText("");
        separator.setManaged(false);

    }

    /**
     * Updates the {@code InternshipInfoPanel} displaying all information of an Internship.
     */
    public void updateInfoPanel(Internship internship) {
        if (!isNull(internship)) {
            position.setText(internship.getPosition().positionName);
            company.setText(internship.getCompany().companyName);
            status.setText("Status: " + internship.getStatus().toString());
            description.setText("Description: " + internship.getDescription().descriptionMessage);
            separator.setManaged(true);
            internship.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        }
    }


}

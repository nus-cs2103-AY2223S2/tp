package seedu.ultron.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.ultron.commons.core.LogsCenter;
import seedu.ultron.model.opening.Opening;

/**
 * Panel containing the list of openings.
 */
public class OpeningDetailsPanel extends UiPart<Region> {
    private static final String FXML = "OpeningDetailsPanel.fxml";
    public final Opening opening;

    @FXML
    private Label company;
    @FXML
    private Label position;
    @FXML
    private Label status;
    @FXML
    private Label email;
    @FXML
    private Label remark;
    @FXML
    private FlowPane dates;

    private final Logger logger = LogsCenter.getLogger(OpeningDetailsPanel.class);

    /**
     * Creates a {@code OpeningDetailsPanel} with the given {@code ObservableList}.
     */
    public OpeningDetailsPanel(Opening opening) {
        super(FXML);
        this.opening = opening;
        position.setText(opening.getPosition().fullPosition);
        company.setText("Company: " + opening.getCompany().fullCompany);
        status.setText("Status: " + opening.getStatus().fullStatus);
        email.setText("Email: " + opening.getEmail().value);
        if (opening.getRemark().value != "") {
            remark.setText(String.format("Remark: %s", opening.getRemark().value));
        } else {
            remark.setText("");
        }
        dates.setHgap(20);
        opening.getKeydates().stream()
                .forEach(date -> {
                    KeydateCard keydateCard = new KeydateCard(date);
                    keydateCard.setWrapText(true);
                    dates.getChildren().add(keydateCard);
                });
        position.setWrapText(true);
        company.setWrapText(true);
        status.setWrapText(true);
        email.setWrapText(true);
        remark.setWrapText(true);
    }
}

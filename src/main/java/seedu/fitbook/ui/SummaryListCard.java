package seedu.fitbook.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.fitbook.AppParameters;
import seedu.fitbook.commons.core.LogsCenter;
import seedu.fitbook.model.client.Client;

/**
 * A UI component that displays information of a {@code Client}.
 */
public class SummaryListCard extends UiPart<Region> {
    private static final String FXML = "SummaryListCard.fxml";
    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on FitBook level 4</a>
     */

    public final Client client;

    @javafx.fxml.FXML
    private HBox cardPane;
    @javafx.fxml.FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane appointments;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ClientCode} with the given {@code Client} and index to display.
     */
    public SummaryListCard(Client client, int displayedIndex) {
        super(FXML);
        this.client = client;

        id.setText(displayedIndex + ". ");
        name.setText(client.getName().fullName);
        client.getAppointments().stream()
                .sorted(Comparator.comparing(appointment -> appointment.appointmentTime))
                .forEach(appointment -> appointments.getChildren().add(new Label(appointment.appointmentTime)));
        client.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientCard)) {
            return false;
        }

        // state check
        SummaryListCard card = (SummaryListCard) other;
        return id.getText().equals(card.id.getText())
                && client.equals(card.client)
                && cardPane.equals(card.client)
                && tags.equals(card.tags)
                && name.equals(card.name);
    }
}

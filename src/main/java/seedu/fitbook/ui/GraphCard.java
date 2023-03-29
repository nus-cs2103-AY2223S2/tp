package seedu.fitbook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.fitbook.model.client.Client;

/**
 * A UI component that displays information of a {@code Weight}.
 */
public class GraphCard extends UiPart<Region> {
    private static final String FXML = "GraphCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As
     * a consequence, UI elements' variable names cannot be set to such keywords or an exception
     * will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on
     *      AddressBook level 4</a>
     */

    public final Client client;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label weightValue;
    @FXML
    private Label date;

    /**
     * Creates a {@code PersonCode} with the given {@code Task} and index to display.
     */
    public GraphCard(Client client, int displayedIndex) {
        super(FXML);
        this.client = client;
        id.setText(displayedIndex + ". ");
        name.setText(client.getName().fullName + " ");
        weightValue.setText(client.getWeight().value + " ");
        date.setText(client.getWeightHistory().getDateValue(0).toString());
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
        GraphCard card = (GraphCard) other;
        return id.getText().equals(card.id.getText())
                && client.equals(card.client);
    }
}

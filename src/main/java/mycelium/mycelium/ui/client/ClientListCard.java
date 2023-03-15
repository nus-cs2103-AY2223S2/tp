package mycelium.mycelium.ui.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.person.Phone;
import mycelium.mycelium.ui.common.UiPart;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class ClientListCard extends UiPart<Region> {

    private static final String FXML = "ClientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Client client;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label yearOfBirth;
    @FXML
    private Label source;
    @FXML
    private Label mobileNumber;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ClientListCard(Client client, int displayedIndex) {
        super(FXML);
        this.client = client;
        id.setText(displayedIndex + ". ");
        name.setText(client.getName().toString());
        email.setText(client.getEmail().toString());
        yearOfBirth.setText(client.getYearOfBirth().map(YearOfBirth::toString).orElse("No year of birth").toString());
        source.setText(client.getSource().orElse("No source"));
        mobileNumber.setText(client.getMobileNumber().map(Phone::toString).orElse("No mobile number").toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientListCard)) {
            return false;
        }

        // state check
        ClientListCard card = (ClientListCard) other;
        return id.getText().equals(card.id.getText())
            && client.equals(card.client);
    }
}

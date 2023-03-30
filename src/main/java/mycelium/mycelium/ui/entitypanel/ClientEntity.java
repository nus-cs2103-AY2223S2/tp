package mycelium.mycelium.ui.entitypanel;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.Phone;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.util.NonEmptyString;
import mycelium.mycelium.ui.UiPart;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class ClientEntity extends UiPart<Region> {
    private static final String FXML = "ClientEntity.fxml";
    public final Client client;

    private Logger logger = LogsCenter.getLogger(getClass());
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

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
    public ClientEntity(Client client, int displayedIndex) {
        super(FXML);
        this.client = client;
        id.setText(displayedIndex + ". ");
        name.setText(client.getName().toString());
        email.setText(client.getEmail().toString());
        yearOfBirth.setText(client.getYearOfBirth().map(YearOfBirth::toString).orElse("No year of birth").toString());
        source.setText(client.getSource().map(NonEmptyString::toString).orElse("No source"));
        mobileNumber.setText(client.getMobileNumber().map(Phone::toString).orElse("No mobile number").toString());
        logger.fine("Initialized ClientEntity with client: " + client.getName());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientEntity)) {
            return false;
        }

        // state check
        ClientEntity card = (ClientEntity) other;
        return id.getText().equals(card.id.getText())
            && client.equals(card.client);
    }
}

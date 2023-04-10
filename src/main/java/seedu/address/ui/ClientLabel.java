package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.client.Client;

/**
 * A UI component that displays information of a {@code Client}.
 */
public class ClientLabel extends UiPart<Region> {

    private static final String FXML = "ClientLabel.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Client client;

    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;

    /**
     * Creates a {@code ClientCode} with the given {@code Client} and index to display.
     */
    public ClientLabel(Client client, int displayedIndex) {
        super(FXML);
        this.client = client;
        id.setText("#" + String.format("%04d", displayedIndex));
        name.setText(client.getName().fullName);
        phone.setText(client.getPhone().value);
        address.setText(client.getAddress().value);
        email.setText(client.getEmail().value);
    }

    /**
     * Creates an empty ClientLabel
     */
    public ClientLabel() {
        super(FXML);
        this.client = null;
        id.setText("");
        name.setText("Add a Client");
        phone.setText("");
        address.setText("");
        address.setText("");
        email.setText("");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientLabel)) {
            return false;
        }

        // state check
        ClientLabel card = (ClientLabel) other;
        return id.getText().equals(card.id.getText())
            && client.equals(card.client);
    }
}

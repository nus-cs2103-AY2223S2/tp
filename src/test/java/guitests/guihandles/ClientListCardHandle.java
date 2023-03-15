package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import mycelium.mycelium.model.client.Client;

/**
 * Provides a handle to a person card in the person list panel.
 */
public class ClientListCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String NAME_FIELD_ID = "#name";
    private static final String EMAIL_FIELD_ID = "#email";
    private static final String SOURCE_FIELD_ID = "#source";
    private static final String YEAR_OF_BIRTH_FIELD_ID = "#yearOfBirth";
    private static final String MOBILE_NUMBER_FIELD_ID = "#mobileNumber";


    private final Label idLabel;
    private final Label nameLabel;
    private final Label emailLabel;
    private final Label sourceLabel;
    private final Label yearOfBirthLabel;
    private final Label mobileNumberLabel;


    /**
     * Creates a {@code ProjectListCardHandle} with the default details.
     */
    public ClientListCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        nameLabel = getChildNode(NAME_FIELD_ID);
        emailLabel = getChildNode(EMAIL_FIELD_ID);
        sourceLabel = getChildNode(SOURCE_FIELD_ID);
        yearOfBirthLabel = getChildNode(YEAR_OF_BIRTH_FIELD_ID);
        mobileNumberLabel = getChildNode(MOBILE_NUMBER_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getName() {
        return nameLabel.getText();
    }

    public String getEmail() {
        return emailLabel.getText();
    }

    public String getSource() {
        return sourceLabel.getText();
    }
    public String getYearOfBirth() {
        return yearOfBirthLabel.getText();
    }

    public String getMobileNumber() {
        return mobileNumberLabel.getText();
    }

    /**
     * Returns true if this handle contains {@code person}.
     */
    public boolean equals(Client client) {
        return getName().equals(client.getName().toString())
                && getEmail().equals(client.getEmail())
                && getSource().equals(client.getSource())
                && getYearOfBirth().equals(client.getYearOfBirth())
                && getMobileNumber().equals(client.getMobileNumber());
    }
}

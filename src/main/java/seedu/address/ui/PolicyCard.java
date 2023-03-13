package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.client.Client;
import seedu.address.model.client.policy.Policy;

public class PolicyCard extends UiPart<Region> {

    private static final String FXML = "PolicyListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Policy policy;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label startdate;
    @FXML
    private Label premium;
    @FXML
    private Label frequency;

    /**
     * Creates a {@code PolicyCode} with the given {@code Policy} and index to display.
     */
    public PolicyCard(Policy policy, int displayedIndex) {
        super(FXML);
        this.policy = policy;
        id.setText(displayedIndex + ". ");
        name.setText(policy.getPolicyName().policyName);
        startdate.setText(policy.getStartDate().toString());
        premium.setText(policy.getPremium().value);
        frequency.setText(policy.getFrequency().frequency);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PolicyCard)) {
            return false;
        }

        // state check
        PolicyCard card = (PolicyCard) other;
        return id.getText().equals(card.id.getText())
                && policy.equals(card.policy);
    }
}

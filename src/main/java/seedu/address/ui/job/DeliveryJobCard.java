package seedu.address.ui.job;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class DeliveryJobCard extends UiPart<Region> {

    private static final String FXML = "DeliveryJobListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final DeliveryJob job;

    @FXML
    private HBox cardPane;
    @FXML
    private Label label;
    @FXML
    private Label id;
    @FXML
    private Label receipient;
    @FXML
    private Label address;
    @FXML
    private Label deliveryTimeSlot;
    @FXML
    private Label earning;
    @FXML
    private Label completedStatus;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public DeliveryJobCard(DeliveryJob job, int displayedIndex) {
        super(FXML);
        this.job = job;
        id.setText(displayedIndex + ". ");
        label.setText(job.getJobId());
        receipient.setText(job.getRecepient().getName().fullName);
        address.setText(job.getRecepient().getAddress().value);
        deliveryTimeSlot.setText(job.getDeliverSlot());
        earning.setText(job.getEarning().value);
        completedStatus.setText(String.valueOf(job.getDeliveredStatus()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeliveryJobCard)) {
            return false;
        }

        // state check
        DeliveryJobCard card = (DeliveryJobCard) other;
        return id.getText().equals(card.id.getText())
                && job.equals(card.job);
    }
}

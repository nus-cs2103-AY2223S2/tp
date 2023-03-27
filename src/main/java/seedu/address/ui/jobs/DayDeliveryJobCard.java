package seedu.address.ui.jobs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class DayDeliveryJobCard extends UiPart<Region> {

    private static final String FXML = "DayDeliveryJobListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final DeliveryJob job;

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
   /* @FXML
    private Label deliveryTimeDate;
    @FXML
    private Label deliveryTimeSlot;*/
    @FXML
    private Label earning;
   // @FXML
    //private Label completedStatus;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public DayDeliveryJobCard(DeliveryJob job, int displayedIndex) {
        super(FXML);
        this.job = job;
        id.setText(displayedIndex + ". ");
        label.setText(job.getJobId());
        receipient.setText(job.getRecipientId());
        address.setText("Refine later");

       /* job.getDeliveryDate().ifPresentOrElse(val -> {
            deliveryTimeDate.setText(val.date);
        }, () -> {
            deliveryTimeDate.setText("N.A");
        });

        job.getDeliverySlot().ifPresentOrElse(val -> {
            deliveryTimeSlot.setText(val.value);
        }, () -> {
            deliveryTimeSlot.setText("N.A");
        });*/

        job.getEarning().ifPresentOrElse(val -> {
            earning.setText(val.value);
        }, () -> {
            earning.setText("N.A");
        });

       // completedStatus.setText(String.valueOf(job.getDeliveredStatus()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DayDeliveryJobCard)) {
            return false;
        }

        // state check
        DayDeliveryJobCard card = (DayDeliveryJobCard) other;
        return id.getText().equals(card.id.getText())
                && job.equals(card.job);
    }
}

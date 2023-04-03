package seedu.address.ui.jobs;

import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import seedu.address.model.jobs.DeliveryDate;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code DeliveryJobCard}.
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
    private final DeliveryJob job;
    private Consumer<DeliveryJob> onCheckHandler;

    @FXML
    private BorderPane cardPane;
    @FXML
    private Label label;
    @FXML
    private Label id;
    @FXML
    private Label receipient;
    @FXML
    private Label address;
    @FXML
    private Label deliveryTimeDate;
    @FXML
    private Label deliveryTimeSlot;
    @FXML
    private Label earning;
    @FXML
    private Label earningDollar;
    @FXML
    private Label earningCent;
    @FXML
    private ImageView checkmarkIcon;

    /**
     * Creates a {@code DeliveryJobCard} with the given {@code DeliveryJob}, index to display
     * and {@code Consumer<DeliveryJob>}.
     */
    public DeliveryJobCard(DeliveryJob job, int displayedIndex, Consumer<DeliveryJob> onCheckHandler) {
        super(FXML);
        this.job = job;
        id.setText(displayedIndex + ". ");
        label.setText(job.getJobId());
        receipient.setText(job.getRecipientId());
        address.setText("Refine later");

        job.getDeliveryDate().ifPresentOrElse(val -> {
            if (val.date.equals(DeliveryDate.placeholder().date)) {
                deliveryTimeDate.setText("Not scheduled");
            } else {
                deliveryTimeDate.setText(val.date);
            }
        }, () -> {
            deliveryTimeDate.setText("Not scheduled");
        });

        job.getDeliverySlot().ifPresentOrElse(val -> {
            deliveryTimeSlot.setText(val.getDescription());
        }, () -> {
            deliveryTimeSlot.setText("");
        });

        job.getEarning().ifPresentOrElse(val -> {
            earningDollar.setText(val.dollar);
            earningCent.setText(val.cent);
        }, () -> {
            earningDollar.setText("0");
            earningCent.setText("00");
        });

        checkmarkIcon.setVisible(job.getDeliveredStatus());
        this.onCheckHandler = onCheckHandler;
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

    @FXML
    private void handleChecked() {
        onCheckHandler.accept(job);
    }
}

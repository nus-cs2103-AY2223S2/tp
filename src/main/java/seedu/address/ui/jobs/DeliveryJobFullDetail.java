package seedu.address.ui.jobs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.ui.UiPart;

/**
 * DeliveryJobFullDetail.
 */
public class DeliveryJobFullDetail extends UiPart<Region> {

    private static final String FXML = "FullDeliveryJobDetailPane.fxml";

    @FXML
    private Label label;
    @FXML
    private Label id;
    @FXML
    private Label deliveryTimeDate;
    @FXML
    private Label deliveryTimeSlot;
    @FXML
    private Label earning;
    @FXML
    private Label completedStatus;

    /**
     * DeliveryJobFullDetail
     *
     * @param job
     * @param displayedIndex
     */
    public DeliveryJobFullDetail(DeliveryJob job, int displayedIndex) {
        super(FXML);
        id.setText(displayedIndex + ". ");
        label.setText(job.getJobId());

        job.getDeliveryDate().ifPresentOrElse(val -> {
            deliveryTimeDate.setText(val.date);
        }, () -> {
            deliveryTimeDate.setText("N.A");
        });

        job.getDeliverySlot().ifPresentOrElse(val -> {
            deliveryTimeSlot.setText(val.value);
        }, () -> {
            deliveryTimeSlot.setText("N.A");
        });

        earning.setText(job.getEarning().value);
        completedStatus.setText(String.valueOf(job.getDeliveredStatus()));
    }
}

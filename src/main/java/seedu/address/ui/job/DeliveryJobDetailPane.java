package seedu.address.ui.job;

import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.ui.UiPart;
import seedu.address.ui.person.PersonCard;

/**
 * DeliveryJobDetailPane.
 */
public class DeliveryJobDetailPane extends UiPart<Region> {

    private static final String FXML = "DeliveryJobDetailPane.fxml";

    private DeliveryJobFullDetail deliveryJobFullDetail;
    private PersonCard senderInfo;
    private PersonCard recepientInfo;

    private final DeliveryJob job;
    private final int displayedIndex;

    @FXML
    private StackPane fullDeliveryJobDetailPlaceholder;

    @FXML
    private StackPane senderContactInfoPlaceholder;

    @FXML
    private StackPane recepientContactInfoPlaceholder;

    @FXML
    private TitledPane contactInfoTitledPane;

    /**
     * DeliveryJobDetailPanel
     *
     * @param job
     * @param displayedIndex
     */
    public DeliveryJobDetailPane(DeliveryJob job, int displayedIndex) {
        super(FXML);
        this.job = job;
        this.displayedIndex = displayedIndex;

        fillInnerParts();
    }

    void fillInnerParts() {
        deliveryJobFullDetail = new DeliveryJobFullDetail(job, displayedIndex);
        fullDeliveryJobDetailPlaceholder.getChildren().add(deliveryJobFullDetail.getRoot());

        senderInfo = new PersonCard(job.getSender(), "Sender");
        recepientInfo = new PersonCard(job.getRecepient(), "Recepient");

        senderContactInfoPlaceholder.getChildren().add(senderInfo.getRoot());
        recepientContactInfoPlaceholder.getChildren().add(recepientInfo.getRoot());
    }
}

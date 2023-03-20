package seedu.address.ui.jobs;

import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.ui.UiPart;
import seedu.address.ui.person.PersonCard;

/**
 * DeliveryJobDetailPane.
 */
public class DeliveryJobDetailPane extends UiPart<Region> {

    private static final String FXML = "DeliveryJobDetailPane.fxml";

    private DeliveryJobFullDetail deliveryJobFullDetail;

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
    }

    /**
     * fillInnerParts.
     *
     * @param ab
     */
    public void fillInnerParts(ReadOnlyAddressBook ab) {
        deliveryJobFullDetail = new DeliveryJobFullDetail(job, displayedIndex);
        fullDeliveryJobDetailPlaceholder.getChildren().add(deliveryJobFullDetail.getRoot());

        ab.getPersonById(job.getSenderId()).ifPresent(per -> {
            PersonCard card = new PersonCard(per, "Sender");
            senderContactInfoPlaceholder.getChildren().add(card.getRoot());
        });

        ab.getPersonById(job.getRecepientId()).ifPresent(per -> {
            PersonCard card = new PersonCard(per, "Recepient");
            recepientContactInfoPlaceholder.getChildren().add(card.getRoot());
        });
    }
}

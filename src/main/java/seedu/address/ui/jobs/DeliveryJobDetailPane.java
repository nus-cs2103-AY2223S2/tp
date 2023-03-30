package seedu.address.ui.jobs;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.jobs.DeliveryDate;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.ui.UiPart;
import seedu.address.ui.person.PersonCard;

/**
 * DeliveryJobDetailPane.
 */
public class DeliveryJobDetailPane extends UiPart<Region> {

    private static final String FXML = "DeliveryJobDetailPane.fxml";
    private static final String BUTTON_LABEL_NOTCOMPLETE = "Mark Pending";
    private static final String BUTTON_LABEL_COMPLETE = "Mark Delivered";
    private static final String BUTTON_LABEL_SHOW = "Show";
    private static final String BUTTON_LABEL_HIDE = "Hide";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final DeliveryJob job;

    private Consumer<DeliveryJob> handleEdit;
    private Consumer<DeliveryJob> handleComplete;
    private Consumer<DeliveryJob> handleDelete;

    @FXML
    private StackPane senderContactInfoPlaceholder;
    @FXML
    private StackPane recipientContactInfoPlaceholder;
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
    private Label earningDollar;
    @FXML
    private Label earningCent;
    @FXML
    private TextArea description;
    @FXML
    private Button completeButton;
    @FXML
    private Label deliveryAddress;
    @FXML
    private GridPane contactDetailPane;
    @FXML
    private Button toggleContactButton;

    /**
     * DeliveryJobDetailPanel
     *
     * @param job
     */
    public DeliveryJobDetailPane(DeliveryJob job) {
        super(FXML);
        this.job = job;
    }

    /**
     * fillInnerParts.
     *
     * @param ab
     */
    public void fillInnerParts(ReadOnlyAddressBook ab) {
        label.setText(job.getJobId());

        job.getDeliveryDate().ifPresentOrElse(val -> {
            if (val.date.equals(DeliveryDate.placeholder().toString())) {
                deliveryTimeDate.setText("N.A");
            } else {
                deliveryTimeDate.setText(val.date);
            }
        }, () -> {
            deliveryTimeDate.setText("N.A");
        });

        job.getDeliverySlot().ifPresentOrElse(val -> {
            deliveryTimeSlot.setText(val.getDescription());
        }, () -> {
            deliveryTimeSlot.setText("N.A");
        });

        job.getEarning().ifPresentOrElse(val -> {
            earningDollar.setText(val.dollar);
            earningCent.setText(val.cent);
        }, () -> {
            earningDollar.setText("0");
            earningCent.setText("00");
        });

        ab.getPersonById(job.getSenderId()).ifPresent(per -> {
            PersonCard card = new PersonCard(per, "Sender");
            card.getRoot().getStyleClass().add("job_person_card");
            senderContactInfoPlaceholder.getChildren().add(card.getRoot());
        });

        ab.getPersonById(job.getRecipientId()).ifPresent(per -> {
            PersonCard card = new PersonCard(per, "Recipient");
            card.getRoot().getStyleClass().add("job_person_card");
            deliveryAddress.setText(per.getAddress().toString());
            recipientContactInfoPlaceholder.getChildren().add(card.getRoot());
        });

        description.setText(job.getDescription());
        handleEdit = (job) -> {};
        handleComplete = (job) -> {};
        handleDelete = (job) -> {};
        updateCompleteButton();
    }

    /**
     * Sets the edit handler.
     *
     * @param handler
     */
    public void setEditHandler(Consumer<DeliveryJob> handler) {
        handleEdit = handler;
    }

    /**
     * Sets the complete handler.
     *
     * @param handler
     */
    public void setCompleteHandler(Consumer<DeliveryJob> handler) {
        handleComplete = handler;
    }

    /**
     * Sets the delete handler.
     *
     * @param handler
     */
    public void setDeleteHandler(Consumer<DeliveryJob> handler) {
        handleDelete = handler;
    }

    @FXML
    void handleEditAction() {
        logger.info("[Event] handleEditAction");
        handleEdit.accept(job);
    }

    @FXML
    void handleCompleteAction() {
        logger.info("[Event] handleCompleteAction");
        updateCompleteButton();
        handleComplete.accept(job);
    }

    @FXML
    void handleDeleteAction() {
        logger.info("[Event] handleDeleteAction");
        handleDelete.accept(job);
    }

    @FXML
    void toggleVisibility() {
        contactDetailPane.setVisible(!contactDetailPane.isVisible());
        if (!contactDetailPane.isVisible()) {
            toggleContactButton.setText(BUTTON_LABEL_SHOW);
        } else {
            toggleContactButton.setText(BUTTON_LABEL_HIDE);
        }
    }

    private void updateCompleteButton() {
        if (job.getDeliveredStatus()) {
            completeButton.setText(BUTTON_LABEL_NOTCOMPLETE);
        } else {
            completeButton.setText(BUTTON_LABEL_COMPLETE);
        }
    }
}

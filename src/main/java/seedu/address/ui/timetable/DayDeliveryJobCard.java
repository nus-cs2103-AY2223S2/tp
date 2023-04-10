package seedu.address.ui.timetable;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code DayDeliveryJobCard}.
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
    private final Logic logic;

    @FXML
    private HBox cardPane;
    @FXML
    private Label jobID;
    @FXML
    private Label id;
    @FXML
    private Label receipient;
    @FXML
    private Label earn;
    @FXML
    private Label address;

    /**
     * Creates a {@code DayDeliveryJobCard} with the given {@code Logic},
     * {@code DeliveryJob} and index to display.
     */
    public DayDeliveryJobCard(Logic logic, DeliveryJob job, int displayedIndex) {
        super(FXML);
        this.job = job;
        this.logic = logic;
        ReadOnlyAddressBook addressBook = logic.getAddressBook();

        id.setText(displayedIndex + ". ");
        jobID.setText(job.getJobId());

        if (job.getRecipientId() != null) {
            receipient.setText("To: " + job.getRecipientId());
        } else {
            receipient.setText("To: N.A.");
        }

        addressBook.getPersonById(job.getRecipientId()).ifPresentOrElse(per -> {
            address.setText("@" + per.getAddress().toString());
        }, () -> {
            address.setText("Dest: N.A.");
        });

        if (job.getEarning().isPresent()) {
            earn.setText("Earn: +$" + job.getEarning().get());
        } else {
            earn.setText("Earn: +$0.0");
        }
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

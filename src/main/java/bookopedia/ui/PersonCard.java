package bookopedia.ui;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import bookopedia.model.person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label deliveryStatus;
    @FXML
    private Label noOfDeliveryAttempts;
    @FXML
    private FlowPane parcels;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        deliveryStatus.setText(person.getDeliveryStatus().toString());
        deliveryStatus.getStyleClass().add(person.getDeliveryStatus().name());
        AtomicInteger parcelIndex = new AtomicInteger();
        person.getParcels().stream()
                .sorted(Comparator.comparing(parcel -> parcel.parcelName))
                .forEach(parcel -> {
                    Label label = new Label(parcelIndex.incrementAndGet() + ". " + parcel.parcelName);
                    if (parcel.isFragile() && parcel.isBulky()) {
                        label.getStyleClass().add("isFragileAndBulky");
                    } else if (parcel.isFragile()) {
                        label.getStyleClass().add("isFragile");
                    } else if (parcel.isBulky()) {
                        label.getStyleClass().add("isBulky");
                    }
                    parcels.getChildren().add(label);
                });
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}

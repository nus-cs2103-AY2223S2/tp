package bookopedia.ui;

import bookopedia.model.person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.util.Comparator;

/**
 * An UI component that displays more information of a {@code Person}.
 */
public class ViewDisplay extends UiPart<Region>{
    private static final String FXML = "ViewDisplay.fxml";

    public final Person person;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label deliveryStatusToView;
    @FXML
    private Label noOfDeliveryAttemptsToView;
    @FXML
    private FlowPane parcelsToView;
    @FXML
    private Label parcelLabel;

    /**
     * Creates a {@code ViewDisplay} with the given {@code Person} and index to display.
     */
    public ViewDisplay(Person person, int index) {
        super(FXML);
        this.person = person;
        name.setText("Name: " + person.getName().fullName);
        phone.setText("Phone: " + person.getPhone().value);
        address.setText("Address: " + person.getAddress().value);
        email.setText("Email: " + person.getEmail().value);
        parcelLabel.setText("Parcels: ");
        person.getParcels().stream()
                .sorted(Comparator.comparing(parcel -> parcel.parcelName))
                .forEach(parcel -> parcelsToView.getChildren().add(new Label(parcel.parcelName)));
        deliveryStatusToView.setText(person.getDeliveryStatus().toString());
        deliveryStatusToView.getStyleClass().add(person.getDeliveryStatus().name());
        noOfDeliveryAttemptsToView.setText(String.format("Number of Attempts: %d", person.getNoOfDeliveryAttempts()));
    }
}

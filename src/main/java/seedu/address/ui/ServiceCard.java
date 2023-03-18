package seedu.address.ui;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.service.Service;
import seedu.address.model.service.Vehicle;

/**
 * An UI component that displays information of a {@code Service}.
 */
public class ServiceCard extends UiPart<Region> {

    private static final String FXML = "ServiceListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on AddressBook level 4</a>
     */

    public final Service service;
    public final Vehicle serviceVehicle;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label vehiclePlateNumber;
    @FXML
    private Label entryDate;
    @FXML
    private Label estimatedFinish;
    @FXML
    private Label numberOfParts;
    @FXML
    private Label numberOfTechnicians;

    /**
     * Creates a {@code ServiceCode} with the given {@code Service} and service vehicle
     */
    public ServiceCard(Service service, Vehicle serviceVehicle) {
        super(FXML);
        this.service = service;
        this.serviceVehicle = serviceVehicle;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        id.setText(service.getId() + ". ");
        description.setText(service.getDescription());
        vehiclePlateNumber.setText(serviceVehicle.getPlateNumber());
        entryDate.setText("Entry Date: " + service.getEntryDate().format(dtf));
        estimatedFinish.setText("Estimated Finish Date: " + service.getEstimatedFinishDate().format(dtf));
        numberOfParts.setText("Parts required: " + service.getRequiredParts().getSize());
        numberOfTechnicians.setText("Technicians assigned: " + service.getAssignedToIds().size());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ServiceCard)) {
            return false;
        }

        // state check
        ServiceCard card = (ServiceCard) other;
        return id.getText().equals(card.id.getText())
                && service.equals(card.service);
    }
}

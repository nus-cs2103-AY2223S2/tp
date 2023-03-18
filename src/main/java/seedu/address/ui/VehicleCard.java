package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.service.Vehicle;

/**
 * An UI component that displays information of a {@code Customer}.
 */
public class VehicleCard extends UiPart<Region> {

    private static final String FXML = "VehicleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on AddressBook level 4</a>
     */

    public final Vehicle vehicle;
    public final Customer vehicleOwner;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label plateNumber;
    @FXML
    private Label brand;
    @FXML
    private Label type;
    @FXML
    private Label owner;
    @FXML
    private Label numberOfServices;

    /**
     * Creates a {@code VehicleCode} with the given {@code Vehicle} and vehicle
     * owner
     */
    public VehicleCard(Vehicle vehicle, Customer vehicleOwner) {
        super(FXML);
        this.vehicle = vehicle;
        this.vehicleOwner = vehicleOwner;
        id.setText(vehicle.getId() + ". ");
        plateNumber.setText(vehicle.getPlateNumber());
        brand.setText(vehicle.getBrand() + ",");
        type.setText(vehicle.getType().getValue());
        owner.setText(vehicleOwner.getName().fullName);
        numberOfServices.setText("Number of services: " + vehicle.getServiceIds().size());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VehicleCard)) {
            return false;
        }

        // state check
        VehicleCard card = (VehicleCard) other;
        return id.getText().equals(card.id.getText())
                && vehicleOwner.equals(card.vehicleOwner);
    }
}

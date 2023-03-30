package seedu.address.ui;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import seedu.address.model.service.Service;
import seedu.address.model.service.ServiceStatus;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.VehicleType;

/**
 * A UI component that displays information of a {@code Service}.
 */
public class ServiceCard extends UiPart<Region> {

    private static final String FXML = "ServiceListCard.fxml";
    private static final Image CarIcon = new Image("/images/car_white_icon.png");
    private static final Image MotorbikeIcon = new Image("/images/motorbike_white_icon.png");
    private static final Color toRepair = Color.rgb(61, 167, 201);
    private static final Color inProgress = Color.rgb(252, 159, 39);
    private static final Color complete = Color.rgb(89, 201, 61);
    private static final Color cancelled = Color.rgb(236, 46, 46);
    private static final Color onHold = Color.rgb(255, 230, 0);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     * issue on AddressBook level 4</a>
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
    private Label vehicleId;
    @FXML
    private ImageView vehicleTypeImg;
    @FXML
    private Label vehicleInfo;
    @FXML
    private Label estimatedFinishDate;
    @FXML
    private Label status;
    @FXML
    private Circle statusCircle;

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
        vehicleId.setText("Vehicle ID: " + serviceVehicle.getId());
        if (serviceVehicle.getType() == VehicleType.CAR) {
            vehicleTypeImg.setImage(CarIcon);
        } else {
            vehicleTypeImg.setImage(MotorbikeIcon);
        }
        vehicleInfo.setText(serviceVehicle.getBrand() + ", " + serviceVehicle.getPlateNumber());
        estimatedFinishDate.setText("Estimated Finish Date: " + service.getEstimatedFinishDate().format(dtf));
        status.setText("Status: " + service.getStatus().getValue());
        setStatusCircle(service.getStatus());
    }

    private void setStatusCircle(ServiceStatus status) {
        switch (status) {
        case TO_REPAIR:
            statusCircle.setFill(toRepair);
            break;
        case IN_PROGRESS:
            statusCircle.setFill(inProgress);
            break;
        case COMPLETE:
            statusCircle.setFill(complete);
            break;
        case CANCELLED:
            statusCircle.setFill(cancelled);
            break;
        default:
            statusCircle.setFill(onHold);
        }
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

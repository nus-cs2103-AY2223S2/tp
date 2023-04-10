package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.mapping.VehicleDataMap;
import seedu.address.model.service.Vehicle;

/**
 * Panel containing the list of vehicles.
 */
public class VehicleListPanel extends UiPart<Region> {
    private static final String FXML = "VehicleListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(VehicleListPanel.class);

    @FXML
    private ListView<Vehicle> vehicleListView;
    private VehicleDataMap dataMap;

    /**
     * Creates a {@code VehicleListPanel} with the given {@code ObservableList}.
     */
    public VehicleListPanel(ObservableList<Vehicle> vehicleList, VehicleDataMap dataMap) {
        super(FXML);
        vehicleListView.setItems(vehicleList);
        vehicleListView.setCellFactory(listView -> new VehicleListViewCell());
        this.dataMap = dataMap;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Vehicle}
     * using a {@code VehicleCard}.
     */
    class VehicleListViewCell extends ListCell<Vehicle> {
        @Override
        protected void updateItem(Vehicle vehicle, boolean empty) {
            super.updateItem(vehicle, empty);

            if (empty || vehicle == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new VehicleCard(vehicle, dataMap.getVehicleCustomer(vehicle)).getRoot());
            }
        }
    }

}

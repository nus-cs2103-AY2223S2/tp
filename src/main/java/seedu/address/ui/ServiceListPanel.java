package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.mapping.ServiceDataMap;
import seedu.address.model.service.Service;

/**
 * Panel containing the list of services.
 */
public class ServiceListPanel extends UiPart<Region> {
    private static final String FXML = "ServiceListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(VehicleListPanel.class);

    @FXML
    private ListView<Service> serviceListView;
    private ServiceDataMap dataMap;

    /**
     * Creates a {@code VehicleListPanel} with the given {@code ObservableList}.
     */
    public ServiceListPanel(ObservableList<Service> serviceList, ServiceDataMap dataMap) {
        super(FXML);
        serviceListView.setItems(serviceList);
        serviceListView.setCellFactory(listView -> new ServiceListViewCell());
        this.dataMap = dataMap;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Service}
     * using a {@code VehicleCard}.
     */
    class ServiceListViewCell extends ListCell<Service> {
        @Override
        protected void updateItem(Service service, boolean empty) {
            super.updateItem(service, empty);

            if (empty || service == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ServiceCard(service, dataMap.getServiceVehicle(service)).getRoot());
            }
        }
    }

}

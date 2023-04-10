package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.mapping.CustomerDataMap;

/**
 * Panel containing the list of customers.
 */
public class CustomerListPanel extends UiPart<Region> {
    private static final String FXML = "CustomerListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CustomerListPanel.class);

    @FXML
    private ListView<Customer> customerListView;
    private CustomerDataMap vehicleLookup;

    /**
     * Creates a {@code CustomerListPanel} with the given {@code ObservableList}.
     */
    public CustomerListPanel(ObservableList<Customer> customerList, CustomerDataMap vehicleLookup) {
        super(FXML);
        customerListView.setItems(customerList);
        customerListView.setCellFactory(listView -> new CustomerListViewCell());
        this.vehicleLookup = vehicleLookup;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Customer}
     * using a {@code CustomerCard}.
     */
    class CustomerListViewCell extends ListCell<Customer> {
        @Override
        protected void updateItem(Customer customer, boolean empty) {
            super.updateItem(customer, empty);

            if (empty || customer == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CustomerCard(customer,
                        vehicleLookup.getCustomerCarsCount(customer),
                        vehicleLookup.getCustomerMotorbikesCount(customer)).getRoot());
            }
        }
    }

}

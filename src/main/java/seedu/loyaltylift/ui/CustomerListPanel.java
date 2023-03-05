package seedu.loyaltylift.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.loyaltylift.commons.core.LogsCenter;
import seedu.loyaltylift.model.customer.Customer;

/**
 * Panel containing the list of customers.
 */
public class CustomerListPanel extends UiPart<Region> {
    private static final String FXML = "CustomerListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CustomerListPanel.class);

    private final CustomerInfoOnClickHandler handler;

    @FXML
    private ListView<Customer> customerListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public CustomerListPanel(ObservableList<Customer> personList, CustomerInfoOnClickHandler handler) {
        super(FXML);
        this.handler = handler;

        customerListView.setItems(personList);
        customerListView.setCellFactory(listView -> new PersonListViewCell());

        customerListView.setOnMouseClicked(e -> {
            Customer customer = customerListView.getSelectionModel().getSelectedItem();
            handler.customerInfoOnClick(customer);

//            customerListView.getItems().
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Customer> {
        @Override
        protected void updateItem(Customer customer, boolean empty) {
            super.updateItem(customer, empty);

            if (empty || customer == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CustomerCard(customer, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Represents a function that handles the event of when a customer card is clicked in this list.
     */
    @FunctionalInterface
    public interface CustomerInfoOnClickHandler {
        /**
         * Handles the displaying of customer information on main window.
         */
        void customerInfoOnClick(Customer customer);
    }
}

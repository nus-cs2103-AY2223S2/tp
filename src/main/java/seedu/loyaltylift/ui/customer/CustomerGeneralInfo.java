package seedu.loyaltylift.ui.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.ui.UiPart;

public class CustomerGeneralInfo extends UiPart<VBox> {

    private static final String FXML = "Customer/CustomerGeneralInfo.fxml";

    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;

    public CustomerGeneralInfo(Customer customer) {
        super(FXML);

        phone.setText(customer.getPhone().value);
        address.setText(customer.getAddress().value);
        email.setText(customer.getEmail().value);
    }
}

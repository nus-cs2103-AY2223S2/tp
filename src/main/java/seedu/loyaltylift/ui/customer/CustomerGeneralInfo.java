package seedu.loyaltylift.ui.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import seedu.loyaltylift.ui.UiPart;

import java.net.URL;

public class CustomerGeneralInfo extends UiPart<VBox> {

    private static final String FXML = "Customer/CustomerGeneralInfo.fxml";

    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;

    public CustomerGeneralInfo() {
        super(FXML);

        phone.setText("91031028");
        address.setText("10 Summer Drive, Singapore 309881");
        email.setText("tracyloh@example.com");
    }
}

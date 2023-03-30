package seedu.loyaltylift.ui.order;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.ui.UiPart;

/**
 * A panel that displays the general information of a Customer.
 */
public class OrderGeneralInfo extends UiPart<VBox> {

    private static final String FXML = "Order/OrderGeneralInfo.fxml";

    @FXML
    private Label customer;

    @FXML
    private Label quantity;
    @FXML
    private Label address;
    @FXML
    private Label createdDate;

    /**
     * Creates a {@code CustomerGeneralInfo} with the given {@code Order}.
     * @param order The order whose general information is to be displayed.
     */
    public OrderGeneralInfo(Order order) {
        super(FXML);

        customer.setText(order.getCustomer().getName().fullName);
        quantity.setText(order.getQuantity().toString());
        address.setText(order.getAddress().toString());
        createdDate.setText(order.getCreatedDate().toString());
    }
}

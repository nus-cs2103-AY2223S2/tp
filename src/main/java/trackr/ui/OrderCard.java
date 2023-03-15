package trackr.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import trackr.model.order.Order;

/**
 * An UI component that displays information of a {@code Order}.
 */
public class OrderCard extends UiPart<Region> {

    private static final String FXML = "OrderListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Order order;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label orderName;
    @FXML
    private Label orderQuantity;
    @FXML
    private Label orderDeadline;
    @FXML
    private Label orderStatus;
    @FXML
    private Label customerName;
    @FXML
    private Label customerPhone;
    @FXML
    private Label customerAddress;

    /**
     * Creates a {@code orderCode} with the given {@code Order} and index to display.
     */
    public OrderCard(Order order, int displayedIndex) {
        super(FXML);
        this.order = order;
        id.setText(displayedIndex + ". ");
        orderName.setText(order.getOrderName().value);
        orderQuantity.setText(order.getOrderQuantity().value);
        orderDeadline.setText(order.getOrderDeadline().toString());
        orderStatus.setText(order.getOrderStatus().toString());
        customerName.setText(order.getCustomer().getCustomerName().fullName);
        customerAddress.setText(order.getCustomer().getCustomerAddress().value);
        customerPhone.setText(order.getCustomer().getCustomerPhone().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderCard)) {
            return false;
        }

        // state check
        OrderCard card = (OrderCard) other;
        return id.getText().equals(card.id.getText())
                && order.equals(card.order);
    }
}

package trackr.ui.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import trackr.model.order.Order;
import trackr.ui.UiPart;

//@@author arkarsg-reused
/**
 * A UI component that displays summarised information of {@code Order}.
 */
public class SummarisedOrderCard extends UiPart<Region> {
    private static final String FXML = "SummarisedOrderCard.fxml";

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

    /**
     * Creates a {@code summarisedContactCard} with the given {@code Order} and index
     */
    public SummarisedOrderCard(Order order, int displayedIndex) {
        super(FXML);
        this.order = order;
        id.setText(displayedIndex + ". ");
        orderName.setText(order.getOrderName().getName());
        orderQuantity.setText(order.getOrderQuantity().toString());
        orderDeadline.setText(order.getOrderDeadline().toString());
        orderStatus.setText(order.getOrderStatus().toString());
        customerName.setText(order.getCustomer().getCustomerName().getName());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SummarisedOrderCard)) {
            return false;
        }

        // state check
        SummarisedOrderCard card = (SummarisedOrderCard) other;
        return id.getText().equals(card.id.getText())
                    && order.equals(card.order);
    }
}

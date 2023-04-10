package seedu.loyaltylift.ui.customer;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.ui.Badge;
import seedu.loyaltylift.ui.UiPart;

/**
 * A UI component that displays information of a {@code Order} that belongs to a {@code Customer}.
 */
public class CustomerOrderCard extends UiPart<HBox> {

    private static final String FXML = "Customer/CustomerOrderCard.fxml";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label date;
    @FXML
    private Label address;
    @FXML
    private HBox statusPlaceholder;

    private final Order order;
    private final Integer index;


    /**
     * Creates a {@code CustomerOrderCard} with the given {@code Order} and index to display.
     */
    public CustomerOrderCard(Order order, Integer index) {
        super(FXML);
        this.order = order;
        this.index = index;

        id.setText(index.toString());
        name.setText(order.getName().fullName);
        date.setText(DATE_FORMATTER.format(order.getCreatedDate().value));
        address.setText(order.getAddress().value);

        Badge statusBadge = Badge.createOrderStatusBadge(order.getStatus());
        statusPlaceholder.getChildren().add(statusBadge.getRoot());
    }
}

package seedu.loyaltylift.ui.order;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.ui.Badge;
import seedu.loyaltylift.ui.UiPart;

/**
 * ScrollPane that displays information of a order.
 */
public class OrderInfo extends UiPart<ScrollPane> {

    private static final String FXML = "Order/OrderInfo.fxml";

    @FXML
    private VBox verticalContainer;

    @FXML
    private Label orderName;

    @FXML
    private AnchorPane orderStatusPlaceholder;

    /**
     * Creates a {@code OrderInfo} with the given {@code Order}.
     * @param order The order whose information is to be displayed.
     */
    public OrderInfo(Order order) {
        super(FXML);

        orderName.setText(order.getName().name.toUpperCase());

        Badge orderStatusBadge = Badge.createOrderStatusBadge(order.getStatus());
        orderStatusPlaceholder.getChildren().add(orderStatusBadge.getRoot());

        // General Info
        OrderGeneralInfo orderGeneralInfo = new OrderGeneralInfo(order);
        insertSection("General", orderGeneralInfo.getRoot());
    }

    private void insertSection(String sectionTitle, Node node) {
        VBox vbox = new VBox();

        Label label = new Label(sectionTitle);
        label.setStyle("-fx-font-size: 25; -fx-font-weight: bold; -fx-background-insets: 0 0 5 0px");

        vbox.getChildren().addAll(label, node);
        verticalContainer.getChildren().add(vbox);
    }
}

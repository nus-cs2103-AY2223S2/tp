package seedu.loyaltylift.ui.customer;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.ui.Badge;
import seedu.loyaltylift.ui.UiPart;

/**
 * ScrollPane that displays information of a customer.
 */
public class CustomerInfo extends UiPart<ScrollPane> {

    private static final String FXML = "Customer/CustomerInfo.fxml";

    @FXML
    private VBox verticalContainer;

    @FXML
    private Label customerName;

    @FXML
    private AnchorPane customerTypePlaceholder;

    /**
     * Creates a {@code CustomerInfo} with the given {@code Customer}.
     * @param customer The customer whose information is to be displayed.
     */
    public CustomerInfo(Customer customer) {
        super(FXML);

        customerName.setText(customer.getName().fullName.toUpperCase());

        Badge customerTypeBadge = Badge.createCustomerTypeBadge(customer.getCustomerType());
        customerTypePlaceholder.getChildren().add(customerTypeBadge.getRoot());

        // General Info
        CustomerGeneralInfo customerGeneralInfo = new CustomerGeneralInfo(customer);
        insertSection("General", customerGeneralInfo.getRoot());
    }

    private void insertSection(String sectionTitle, Node node) {
        VBox vbox = new VBox();

        Label label = new Label(sectionTitle);
        label.setStyle("-fx-font-size: 25; -fx-font-weight: bold; -fx-background-insets: 0 0 5 0px");

        vbox.getChildren().addAll(label, node);
        verticalContainer.getChildren().add(vbox);
    }
}

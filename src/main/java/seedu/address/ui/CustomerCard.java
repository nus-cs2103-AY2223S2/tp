package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.entity.person.Customer;

/**
 * A UI component that displays information of a {@code Customer}.
 */
public class CustomerCard extends UiPart<Region> {

    private static final String FXML = "CustomerListCard.fxml";

    public final Customer customer;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;

    @FXML
    private Label email;
    @FXML
    private Label numberOfCars;
    @FXML
    private Label numberOfMotorbikes;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code CustomerCode} with the given {@code Customer} and index to display.
     */
    public CustomerCard(Customer customer, int cars, int motorbikes) {
        super(FXML);
        this.customer = customer;
        id.setText(customer.getId() + ". ");
        name.setText(customer.getName().fullName + ", ");
        numberOfCars.setText(cars + "");
        numberOfMotorbikes.setText(motorbikes + "");
        phone.setText(customer.getPhone().value);
        email.setText(customer.getEmail().value);
        customer.getTags().stream()
                .forEach(tag -> tags.getChildren()
                        .add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CustomerCard)) {
            return false;
        }

        // state check
        CustomerCard card = (CustomerCard) other;
        return id.getText().equals(card.id.getText())
                && customer.equals(card.customer);
    }
}

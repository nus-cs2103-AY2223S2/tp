package seedu.loyaltylift.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import seedu.loyaltylift.model.customer.CustomerType;
import seedu.loyaltylift.model.order.Status;

/**
 * A small label that displays a text with a rounded background box.
 */
public class Badge extends UiPart<StackPane> {

    private static final String FXML = "Badge.fxml";

    @FXML
    private Label tag;

    /**
     * Creates a {@code Badge} with the given colors and text to be displayed.
     * @param backgroundColor Color of the background.
     * @param textColor Color of the text to be displayed.
     * @param text The text to be displayed.
     */
    private Badge(Color backgroundColor, Color textColor, String text) {
        super(FXML);

        this.getRoot().setStyle("-fx-background-color: " + colorToHex(backgroundColor));
        tag.setStyle("-fx-text-fill: " + colorToHex(textColor));
        tag.setText(text);
    }

    /**
     * Returns a string representing the hex colour code of the given {@code Color}.
     * @param color The Color to be converted.
     * @return A string of hex colour code.
     */
    public static String colorToHex(Color color) {
        return "#" + color.toString().substring(2, 8);
    }

    /**
     * Constructs a {@code Badge} based on the {@code CustomerType}.
     * @param customerType The CustomerType of the customer.
     * @return An instance of the Badge.
     * @throws IllegalArgumentException Thrown if CustomerType is not recognised or null.
     */
    public static Badge createCustomerTypeBadge(CustomerType customerType) {
        switch (customerType) {
        case INDIVIDUAL:
            return new Badge(Color.valueOf("#2F8F95"), Color.WHITE, "Individual");
        case ENTERPRISE:
            return new Badge(Color.valueOf("#95352F"), Color.WHITE, "Enterprise");
        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * Constructs a {@code Badge} based on the {@code Status} of an {@code Order}.
     * @param status The status of the Order.
     * @return An instance of the Badge.
     * @throws IllegalArgumentException Thrown if Status is not recognised or null.
     */
    public static Badge createOrderStatusBadge(Status status) {
        // TODO: update for different status type
        return new Badge(Color.valueOf("#4F46E5"), Color.WHITE, status.toString());
    }
}

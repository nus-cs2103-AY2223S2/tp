package seedu.loyaltylift.ui.order;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.loyaltylift.model.order.StatusUpdate;
import seedu.loyaltylift.ui.UiPart;

/**
 * A UI component that displays status history of an {@code Order} that belongs to a {@code Customer}.
 */
public class OrderHistoryCard extends UiPart<HBox> {
    private static final String FXML = "Order/OrderHistoryCard.fxml";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @javafx.fxml.FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label date;

    private final StatusUpdate statusUpdate;
    private final Integer index;


    /**
     * Creates a {@code CustomerOrderCard} with the given {@code Order} and index to display.
     */
    public OrderHistoryCard(StatusUpdate statusUpdate, Integer index) {
        super(FXML);
        this.statusUpdate = statusUpdate;
        this.index = index;

        id.setText(index.toString());
        name.setText(statusUpdate.getStatusValue().toString());
        date.setText(DATE_FORMATTER.format(statusUpdate.getDate()));
    }
}

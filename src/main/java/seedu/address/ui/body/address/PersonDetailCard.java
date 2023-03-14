package seedu.address.ui.body.address;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays a section of information.
 */
public class PersonDetailCard extends UiPart<Region> {
    private static final String FXML = "body/address/PersonDetailCard.fxml";
    private static final String DATA_BODY_PLACEHOLDER = "Unknown";
    private static final double OPACITY_PRESENT = 1;
    private static final double OPACITY_MISSING = 0.5;

    @FXML
    private Label title;
    @FXML
    private Label body;

    /**
     * Creates a {@code PersonDetailCard}.
     *
     * @param data Data to populate the card with.
     */
    public PersonDetailCard(DetailCardData data) {
        super(FXML);

        title.setText(data.title);

        if (data.hasBody()) {
            body.setText(data.body);
            this.getRoot().setOpacity(OPACITY_PRESENT);
        } else {
            body.setText(DATA_BODY_PLACEHOLDER);
            this.getRoot().setOpacity(OPACITY_MISSING);
        }
    }

    /**
     * Helper container class for data.
     */
    public static class DetailCardData {
        public final String title;
        public final String body;

        /**
         * Creates a {@code DetailCardData} with the given {@code title} and {@code body}.
         */
        public DetailCardData(String title, String body) {
            Objects.requireNonNull(title);
            this.title = title;
            this.body = body;
        }

        /**
         * Returns whether the card body has any content.
         */
        public boolean hasBody() {
            return body != null && !body.isBlank();
        }
    }
}

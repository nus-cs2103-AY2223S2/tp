package seedu.address.ui.body.address;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays a section of information.
 */
public class PersonDetailCard extends UiPart<Region> {
    private static final String FXML = "body/address/PersonDetailCard.fxml";

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
        body.setText(data.body);
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
            this.title = title;
            this.body = body;
        }
    }
}

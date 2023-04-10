package seedu.powercards.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.util.Pair;

/**
 * Individual element of the review panel
 */
public class ReviewStatsElement extends UiPart<Region> {
    private static final String FXML = "ReviewStatsElement.fxml";

    @FXML
    private Label title;

    @FXML
    private Label description;

    /**
     * Creates a {@code ReviewStatsElement} with the given {@code pair}.
     */
    public ReviewStatsElement(Pair<String, String> pair) {
        super(FXML);
        title.setText(pair.getKey());
        description.setText(pair.getValue());
    }

}

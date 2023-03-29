package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.util.Pair;

/**
 * Individual element of the review panel
 */
public class ReviewStatsPane extends UiPart<Region> {
    private static final String FXML = "ReviewStatsPane.fxml";

    @FXML
    private Label title;

    @FXML
    private Label description;

    /**
     * Creates a {@code ReviewStatsPane} with the given {@code pair}.
     */
    public ReviewStatsPane(Pair<String, String> pair) {
        super(FXML);
        title.setText(pair.getKey());
        description.setText(pair.getValue());
    }

}

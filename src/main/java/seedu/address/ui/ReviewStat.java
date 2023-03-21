package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.util.Pair;

/**
 * Individual element of the review panel
 */
public class ReviewStat extends UiPart<Region> {
    private static final String FXML = "ReviewStat.fxml";

    @FXML
    private Label title;

    @FXML
    private Label description;

    /**
     * Creates a {@code ReviewStat} with the given {@code pair}.
     */
    public ReviewStat(Pair<String, String> pair) {
        super(FXML);
        title.setText(pair.getKey());
        description.setText(pair.getValue());
    }

}

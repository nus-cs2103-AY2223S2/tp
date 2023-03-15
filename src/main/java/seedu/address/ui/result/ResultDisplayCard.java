package seedu.address.ui.result;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays a section of command results.
 */
public class ResultDisplayCard extends UiPart<Region> {
    private static final String FXML = "result/ResultDisplayCard.fxml";

    @FXML
    private Label title;
    @FXML
    private Label body;

    /**
     * Creates a {@code ResultDisplayCard} with the given {@code title} and {@code body}.
     */
    public ResultDisplayCard(String title, String body) {
        super(FXML);
        Objects.requireNonNull(title);
        Objects.requireNonNull(body);

        this.title.setText(title);
        this.body.setText(body);
    }
}

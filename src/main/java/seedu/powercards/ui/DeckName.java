package seedu.powercards.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * Individual element of the review panel
 */
public class DeckName extends UiPart<Region> {
    private static final String FXML = "DeckName.fxml";
    private static final String EMPTY_STRING = "";

    @FXML
    private Label title;
    @FXML
    private Label description;

    /**
     * Creates a {@code DeckName} with the given {@code name}.
     */
    public DeckName(String name) {
        super(FXML);
        title.setText(name.equals(EMPTY_STRING) ? EMPTY_STRING : "Selected deck:");
        description.setText(name);
    }

}

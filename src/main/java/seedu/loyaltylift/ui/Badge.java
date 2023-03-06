package seedu.loyaltylift.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * A small label that displays a text with a rounded background box.
 */
public class Badge extends UiPart<StackPane> {

    private static final String FXML = "Badge.fxml";

    @FXML
    private Label tag;

    /**
     * Creates a {@code Tag} with the given colors and text to be displayed.
     * @param backgroundColor Color of the background.
     * @param textColor Color of the text to be displayed.
     * @param text The text to be displayed.
     */
    public Badge(Color backgroundColor, Color textColor, String text) {
        super(FXML);

        this.getRoot().setStyle("-fx-background-color: " + colorToHex(backgroundColor));
        tag.setStyle("-fx-text-fill: " + colorToHex(textColor));
        tag.setText(text);
    }

    private String colorToHex(Color color) {
        return "#" + color.toString().substring(2, 8);
    }
}

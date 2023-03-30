package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * An ui component for the history display.
 */
public class HistoryDisplay extends UiPart<Region> {

    private static final String FXML = "HistoryDisplay.fxml";

    @FXML
    private TextArea historyDisplay;

    public HistoryDisplay() {
        super(FXML);
    }

    /**
     * Sets the history string argument to the {@code TextArea} object.
     *
     * @param history the history string of executed user's commands.
     */
    public void displayHistoryToUser(String history) {
        requireNonNull(history);
        historyDisplay.setText(history);
    }
}

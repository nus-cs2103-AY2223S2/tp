package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.model.history.InputHistory;

/**
 * An ui for the history display.
 */
public class HistoryDisplay extends UiPart<Region> {

    private static final String FXML = "HistoryDisplay.fxml";

    @FXML
    private TextFlow historyDisplay;

    public HistoryDisplay() {
        super(FXML);
    }

    /**
     * Sets the history string argument to the {@code TextArea} object.
     *
     * @param history the history string of executed user's commands.
     */
    public void displayHistoryToUser(InputHistory history) {
        requireNonNull(history);
        historyDisplay.getChildren().clear();
        for (String s : history.getPast()) {
            historyDisplay.getChildren().add(new Text(s + "\n"));
        }
        for (String s : history.getFuture()) {
            historyDisplay.getChildren().add(new Text(s + "\n"));
        }
    }
}

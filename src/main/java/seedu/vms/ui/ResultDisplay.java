package seedu.vms.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.vms.logic.commands.CommandResult;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML private ScrollPane scrollPane;
    @FXML private VBox displayArea;

    public ResultDisplay() {
        super(FXML);
        scrollPane.viewportBoundsProperty().addListener((ob, o, n) -> {
            double width = n.getWidth();
            double height = n.getHeight();

            // force width of display area to be view port width
            displayArea.setMinWidth(width);
            displayArea.setPrefWidth(width);
            displayArea.setMaxWidth(width);

            displayArea.setMinHeight(height);
        });
        displayArea.heightProperty().addListener((ob, o, n) -> {
            scrollPane.setVvalue(1D);
        });
    }

    public void setFeedbackToUser(CommandResult commandResult) {
        requireNonNull(commandResult);
        Label label = new Label(commandResult.getMessage());
        label.setWrapText(true);
        displayArea.getChildren().add(label);
    }
}

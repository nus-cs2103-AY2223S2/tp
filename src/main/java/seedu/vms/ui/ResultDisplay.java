package seedu.vms.ui;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.vms.logic.CommandMessage;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> implements Refreshable {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML private ScrollPane scrollPane;
    @FXML private VBox displayArea;

    private LinkedBlockingDeque<CommandMessage> messageQueue = new LinkedBlockingDeque<>();


    /**
     * Constructs a {@code ResultDisplay}.
     */
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

    public void setFeedbackToUser(List<CommandMessage> commandResult) {
        requireNonNull(commandResult);
        messageQueue.addAll(commandResult);
    }


    @Override
    public void refresh() {
        for (int i = 0; i < messageQueue.size(); i++) {
            displayMessage(messageQueue.poll());
        }
    }


    private void displayMessage(CommandMessage commandResult) {
        displayArea.getChildren().add(new ResultMessageBox(commandResult).getRoot());
    }
}

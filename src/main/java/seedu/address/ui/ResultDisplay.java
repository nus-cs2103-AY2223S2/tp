package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private VBox pane;
    @FXML
    private ScrollPane scrollPane;

    public ResultDisplay() {
        super(FXML);
    }

    public void clear() {
        pane.getChildren().clear();
    }

    public void place(Node item) {
        pane.getChildren().add(item);
        scrollPane.setContent(pane);
    }

}

package seedu.modtrek.ui.clisection;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.modtrek.ui.UiPart;

/**
 * A UI component that displays the response dialog after parsing or execution of commands.
 */
public class SystemDialog extends UiPart<Region> {
    private static final String FXML = "clisection/SystemDialog.fxml";

    @FXML
    private HBox systemDialogContainer;

    @FXML
    private Label systemDialogText;

    @FXML
    private HBox systemDialogResponseIndicator;

    /**
     * Creates a {@code SystemDialog}.
     *
     * @param text the response
     * @param isSuccess if the execution is successful
     */
    public SystemDialog(String text, boolean isSuccess) {
        super(FXML);

        systemDialogResponseIndicator.getStyleClass().add("system-dialog-response-indicator-"
                + (isSuccess ? "success" : "error"));

        systemDialogText.setText(text);

        /* Set systemDialogContainer's min height so that container does not shrink when scrollpane overflows. */
        systemDialogContainer.setMinHeight(systemDialogText.getHeight());
        systemDialogContainer.setMinHeight(Region.USE_PREF_SIZE);
    }
}

package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * The UI component that is responsible for receiving remarks inputs.
 */
public class RemarkWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "RemarkWindow.fxml";
    private final KeyCodeCombination ctrlS = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);

    @FXML
    private TextArea textArea;

    /**
     * Creates a new RemarkWindow.
     *
     * @param root Stage to use as the root of the RemarkWindow.
     */
    public RemarkWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new RemarkWindow.
     */
    public RemarkWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public String showAndGetText(String existingRemark) {
        final String[] content = new String[1];
        logger.fine("Editing remarks");
        textArea.setText(existingRemark);
        textArea.positionCaret(existingRemark.length());
        EventHandler<KeyEvent> ctrlSHandler = event -> {
            if (ctrlS.match(event)) {
                event.consume();
                getRoot().close();
            }
        };
        textArea.setOnKeyPressed(ctrlSHandler);
        getRoot().showAndWait();
        return textArea.getText();
    }


    /**
     * Returns true if the remark window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the remark window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the remark window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}

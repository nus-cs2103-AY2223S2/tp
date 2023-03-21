package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;


/**
 * Controller for a help page
 */
public class QuickstartWindow extends UiPart<Stage> {

    private static final String FXML = "QuickstartWindow.fxml";

    /**
     * Creates a new QuickstartWindow.
     */
    public QuickstartWindow() {
        this(new Stage());
    }

    /**
     * Creates a new QuickstartWindow.
     *
     * @param root Stage to use as the root of the QuickstartWindow.
     */
    public QuickstartWindow(Stage root) {
        super(FXML, root);
    }


    /**
     * Shows the QuickstartWindow.
     */
    public void show() {
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the QuickstartWindow is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the QuickstartWindow.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the QuickstartWindow.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
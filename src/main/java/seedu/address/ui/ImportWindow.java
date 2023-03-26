package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a Import window
 */
public class ImportWindow extends UiPart<Stage> {
    public static final String USERGUIDE_URL = "https://ay2223s2-cs2103-w17-1.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Referssss to the user guide:\n" + USERGUIDE_URL;


    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "ImportWindow.fxml";
    private static final String TITLE_TEXT = "Import";

    @FXML
    private Label helpHeader;

    @FXML
    private Button importButton;

    @FXML
    private ImageView importImage;

    @FXML
    private Button cancelButton;

    @FXML
    private Button acceptButton;

    @FXML
    private VBox importContainer;

    /**
     * Creates a new ImportWindow.
     *
     * @param root Stage to use as the root of the ImportWindow.
     */
    public ImportWindow(Stage root) {
        super(FXML, root);
        helpHeader.setText(TITLE_TEXT);

        importImage.setImage(new Image(this.getClass().getResourceAsStream("/images/import.png")));
        importButton.setGraphic(importImage);

        // root.setOnCloseRequest(new EventHandler<WindowEvent>() {
        //     public void handle(WindowEvent we) {
        //         copyButton.setText("Copy Link");
        //     }
        // });
        importContainer.setOnDragOver(e -> {
            if (e.getGestureSource() != importContainer && e.getDragboard().hasFiles()) {
                helpHeader.setText("DROPPED");
                System.out.println("DROPPED");
            }
        });

    }

    /**
     * Creates a new HelpWindow.
     */
    public ImportWindow() {
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
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
        //copyButton.setText("Link Copied!");
    }
}

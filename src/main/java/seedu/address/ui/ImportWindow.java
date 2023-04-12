package seedu.address.ui;

import static seedu.address.logic.commands.ImportDataCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;

import java.io.File;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a Import window
 */
public class ImportWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "ImportWindow.fxml";
    private static final String TITLE_TEXT = "Import";

    private static final String DEFAULT_FEEDBACK_STYLE = "-fx-text-fill: #9e9e9e";
    private static final String VALID_FEEDBACK_STYLE = "-fx-text-fill: #646666";
    private static final String SUCCESS_FEEDBACK_STYLE = "-fx-text-fill: #006400";
    private static final String ERROR_FEEDBACK_STYLE = "-fx-text-fill: #cc0000";

    private String filePath;

    @FXML
    private Label importHeader;

    @FXML
    private Label feedbackLabel;

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

    private Consumer<String> message;

    /**
     * Creates a new ImportWindow.
     *
     * @param root Stage to use as the root of the ImportWindow.
     */
    public ImportWindow(Stage root) {
        super(FXML, root);

        importHeader.setText(TITLE_TEXT);
        importImage.setImage(new Image(this.getClass().getResourceAsStream("/images/import.png")));
        importButton.setGraphic(importImage);

        importContainer.setOnDragOver(e -> {
            if (e.getGestureSource() != importContainer && e.getDragboard().hasFiles()) {
                handleDragOver(e.getDragboard());
            }
        });

    }

    /**
     * Creates a new HelpWindow.
     */
    public ImportWindow(Consumer<String> message) {
        this(new Stage());

        this.message = message;
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
        filePath = null;
        feedbackLabel.setText("no files imported");
        feedbackLabel.setStyle(DEFAULT_FEEDBACK_STYLE);
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Handles import button click event.
     */
    public void handleButton() {
        logger.info("Import Button Clicked");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Backup File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.json"));
        File backupFile = fileChooser.showOpenDialog(getRoot());

        if (backupFile == null) {
            logger.info("No file selected");
            return;
        }

        filePath = backupFile.getAbsolutePath();
        feedbackLabel.setText(filePath);
        feedbackLabel.setStyle(VALID_FEEDBACK_STYLE);
    }

    /**
     * Handles file drag over event.
     */
    private void handleDragOver(Dragboard dragboard) {
        if (dragboard.hasFiles()) {
            filePath = dragboard.getFiles().get(0).getAbsolutePath();
        }
        feedbackLabel.setText(filePath);
        feedbackLabel.setStyle(VALID_FEEDBACK_STYLE);
    }

    /**
     * Handles import button click event. Creates a command string and executes it.
     */
    public void handleImport() {
        String commandString = COMMAND_WORD + " " + PREFIX_FILEPATH + filePath;
        message.accept(commandString);
    }

    /**
     * Handles success import styling.
     */
    public void handleSuccess() {
        feedbackLabel.setText("Import Successful");
        feedbackLabel.setStyle(SUCCESS_FEEDBACK_STYLE);
    }

    /**
     * Handles error import styling.
     */
    public void handleError(String error) {
        feedbackLabel.setText(error);
        feedbackLabel.setStyle(ERROR_FEEDBACK_STYLE);
    }


}

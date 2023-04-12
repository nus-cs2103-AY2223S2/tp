package seedu.address.ui;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.ExportDataCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;

import java.io.File;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Controller for a Export window
 */
public class ExportWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(ExportWindow.class);
    private static final String FXML = "ExportWindow.fxml";
    private static final String TITLE_TEXT = "Export";

    private static final String VALID_FEEDBACK_STYLE = "-fx-text-fill: #9e9e9e";
    private static final String ERROR_FEEDBACK_STYLE = "-fx-text-fill: #cc0000";


    private Logic logic;
    private String filePath;

    @FXML
    private Label exportHeader;

    @FXML
    private Label feedbackLabel;

    @FXML
    private Button exportButton;

    @FXML
    private ImageView exportImage;

    @FXML
    private Button cancelButton;

    @FXML
    private Button acceptButton;

    @FXML
    private VBox exportContainer;



    /**
     * Creates a new ImportWindow.
     *
     * @param root Stage to use as the root of the ImportWindow.
     */
    public ExportWindow(Logic logic, Stage root) {
        super(FXML, root);
        this.logic = logic;

        exportHeader.setText(TITLE_TEXT);
        exportImage.setImage(new Image(this.getClass().getResourceAsStream("/images/export.png")));
        exportButton.setGraphic(exportImage);

    }

    /**
     * Creates a new HelpWindow.
     */
    public ExportWindow(Logic logic) {
        this(logic, new Stage());
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
        feedbackLabel.setText("no folder selected");
        feedbackLabel.setStyle(VALID_FEEDBACK_STYLE);
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
        logger.info("File Explorer Opened");
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory to Export to");
        File selectedFolder = directoryChooser.showDialog(getRoot());

        if (selectedFolder == null) {
            logger.info("No file selected");
            return;
        }

        filePath = selectedFolder.getAbsolutePath();
        feedbackLabel.setText(filePath + "\\data.json");
        feedbackLabel.setStyle(VALID_FEEDBACK_STYLE);
    }


    /**
     * Handles import button click event. Creates a command string and executes it.
     */
    public void handleExport() {
        try {
            requireNonNull(filePath);
            String commandString = COMMAND_WORD + " " + PREFIX_FILEPATH + filePath;
            this.logic.execute(commandString);
            hide();
        } catch (ParseException | CommandException e) {
            feedbackLabel.setText(e.getMessage());
            feedbackLabel.setStyle(ERROR_FEEDBACK_STYLE);
        } catch (NullPointerException e) {
            feedbackLabel.setText("No file selected");
            feedbackLabel.setStyle(ERROR_FEEDBACK_STYLE);
        }
    }
}

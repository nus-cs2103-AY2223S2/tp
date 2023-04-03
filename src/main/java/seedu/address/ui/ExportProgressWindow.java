package seedu.address.ui;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.student.Student;

/**
 * Controller for an export student's progress page
 */
public class ExportProgressWindow extends UiPart<Stage> {

    public static final String MESSAGE_SUCCESS = "%1$s's progress report exported in %2$s";
    private static final Logger logger = LogsCenter.getLogger(ExportProgressWindow.class);
    private static final String FXML = "ExportProgressWindow.fxml";

    private Student student;

    private Stage root;
    private Logic logic;

    private String defaultFileName;

    @FXML
    private Button saveAsButton;

    @FXML
    private TextArea resultDisplay;
    @FXML
    private ImageView exportImage;

    /**
     * Creates a new ExportProgressWindow.
     *
     * @param root Stage to use as the root of the ExportProgressWindow.
     */
    public ExportProgressWindow(Stage root, Student student, Logic logic) {
        super(FXML, root);
        this.root = root;
        this.student = student;
        this.logic = logic;
        this.exportImage.setImage(new Image(this.getClass().getResourceAsStream("/images/export.png")));
        if (this.student != null) {
            resultDisplay.setText("Export " + this.student.getName().fullName + "'s progress");
            saveAsButton.setDisable(false);
        }
    }

    /**
     * Creates a new ExportProgressWindow.
     */
    public ExportProgressWindow(Student student, Logic logic) {
        this(new Stage(), student, logic);
    }

    /**
     * Shows the export progress window.
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
        logger.info("Showing export progress report page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the export progress window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the export progress window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the export progress window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    public ExportProgressWindow setCheckedPerson(Student student) {
        this.student = student;
        resultDisplay.setText("Export " + student.getName().fullName + "'s progress");
        saveAsButton.setDisable(false);
        return this;
    }

    /**
     * Opens a directory chooser window.
     */
    @FXML
    private void saveAs() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setInitialFileName(this.student.getName().fullName + "'s Progress Report");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        File selectedFile = fileChooser.showSaveDialog(this.root);

        if (selectedFile != null) {
            saveAsButton.setDisable(true);
            try {
                logic.exportProgress(this.student, selectedFile.getPath());
                String commandResult = String.format(MESSAGE_SUCCESS, this.student.getName().fullName,
                        selectedFile.getPath());
                logger.info("Result: " + commandResult);
                resultDisplay.setText(commandResult);
                saveAsButton.setDisable(false);
            } catch (IOException e) {
                logger.info("Error: " + e.getMessage());
                resultDisplay.setText("Error!\n" + e.getMessage());
                saveAsButton.setDisable(false);
            }

        }
    }
}

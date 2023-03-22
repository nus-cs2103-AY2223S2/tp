package seedu.address.ui;

import java.io.File;
import java.util.logging.Logger;

import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.TextFragment;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

import javax.swing.*;

/**
 * Controller for an export student's progress page
 */
public class ExportProgressWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(ExportProgressWindow.class);
    private static final String FXML = "ExportProgressWindow.fxml";

    private Person person;

    private Stage root;

    private String defaultFileName;

    @FXML
    private Button saveAsButton;

    @FXML
    private Label exportMessage;

    /**
     * Creates a new ExportProgressWindow.
     *
     * @param root Stage to use as the root of the ExportProgressWindow.
     */
    public ExportProgressWindow(Stage root, Person person) {
        super(FXML, root);
        this.root = root;
        this.person = person;
        if (this.person != null) {
            exportMessage.setText("Export " + this.person.getName().fullName + "'s progress");
            saveAsButton.setDisable(false);
        }
//        root.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            public void handle(WindowEvent we) {
//                exportMessage.setText("");
//            }
//        });
    }

    /**
     * Creates a new ExportProgressWindow.
     */
    public ExportProgressWindow(Person person) {
        this(new Stage(), person);
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
        logger.fine("Showing export progress report page.");
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

    public void setCheckedPerson(Person person) {
        this.person = person;
        exportMessage.setText("Export " + person.getName().fullName + "'s progress");
        saveAsButton.setDisable(false);
    }

    /**
     * Opens a directory chooser window.
     */
    @FXML
    private void saveAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setInitialFileName(this.person.getName().fullName + "'s Progress Report");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(this.root);
        if (selectedFile != null) {
            exportMessage.setText("Exporting " + this.person.getName().fullName + "'s progress");
            Document document = new Document();
            Page page = document.getPages().add();
            page.getParagraphs().add(new TextFragment(this.person.getName().fullName));
            document.save(selectedFile.getPath());
            String EXPORT_DONE_MSG = this.person.getName().fullName + "'s progress exported!";
            exportMessage.setText(EXPORT_DONE_MSG);
        }
    }
}

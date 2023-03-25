package seedu.address.ui;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

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
        logger.log(Level.INFO, "opening progress report page");
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

    public ExportProgressWindow setCheckedPerson(Person person) {
        this.person = person;
        exportMessage.setText("Export " + person.getName().fullName + "'s progress");
        saveAsButton.setDisable(false);
        return this;
    }

    /**
     * Opens a directory chooser window.
     */
    @FXML
    private void saveAs() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setInitialFileName(this.person.getName().fullName + "'s Progress Report");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        File selectedFile = fileChooser.showSaveDialog(this.root);

        if (selectedFile != null) {
            saveAsButton.setDisable(true);
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDFont boldFont = PDType1Font.HELVETICA_BOLD;
            PDFont font = PDType1Font.HELVETICA;

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            String studentName = this.person.getName().fullName;
            String[] studentNameSplit = studentName.split(" ");
            int wrap = 43;
            int xInit = 90;
            int yInit = 700;
            int x = 90;
            int y = 700;
            int fontSize = 18;

            for(int i = 0; i < studentNameSplit.length; i++) {
                int charUsed = 0;
                x = xInit;
                while(charUsed < wrap && i < studentNameSplit.length) {
                    String curString = studentNameSplit[i];
                    contentStream.beginText();
                    contentStream.setFont(boldFont, fontSize);
                    contentStream.moveTextPositionByAmount(x, y);
                    logger.info(String.valueOf(x));
                    logger.info(String.valueOf(y));
                    if (curString.length() > wrap) {
                        contentStream.drawString(curString.substring(0, wrap));
                        studentNameSplit[i] = "-" + curString.substring(wrap, curString.length());
                        logger.info(curString);
                        logger.info(studentNameSplit[i]);
                        charUsed += curString.substring(0, wrap).length();
                        logger.info(String.valueOf(charUsed));
                        i--;
                    } else {
                        contentStream.drawString(curString + " ");
                        charUsed += curString.length() + 1;
                    }
                    contentStream.endText();
                    x += boldFont.getStringWidth(curString + " ") / 1000 * fontSize;
                    i++;
                }
                i--;
                y -= boldFont.getFontDescriptor().getCapHeight() / 1000 * fontSize;
            }

            contentStream.beginText();
            contentStream.setFont(font, 14);
            contentStream.moveTextPositionByAmount(90, 600);
            contentStream.showText("Task List");
            contentStream.endText();

            contentStream.close();

            try {
                document.save(selectedFile.getPath());
            } catch (IOException e) {
                logger.log(Level.INFO, "file is currently used by another process");
            }
            document.close();
            saveAsButton.setDisable(false);
        }
    }
}

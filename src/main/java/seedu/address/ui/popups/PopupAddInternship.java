package seedu.address.ui.popups;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.MainWindow;
import seedu.address.ui.UiPart;

/**
 * Pop up for add internship
 */

public class PopupAddInternship extends UiPart<Stage> {
    private static MainWindow mainWindow;
    private static final Logger logger = LogsCenter.getLogger(PopupAddInternship.class);
    private static final String FXML = "PopupAddInternship.fxml";

    private static final String ILLEGAL_REGEX = ".*[a-z]/.*";

    @FXML
    private TextField companyName;
    @FXML
    private TextField jobTitle;
    @FXML
    private TextField place;
    @FXML
    private TextField salary;
    @FXML
    private TextField rating;
    @FXML
    private VBox qualificationVBox;
    @FXML
    private TextField qualification;
    @FXML
    private VBox programmingLanguageVBox;
    @FXML
    private TextField programmingLanguage;
    @FXML
    private VBox reviewVBox;
    @FXML
    private TextField review;
    @FXML
    private VBox noteVBox;
    @FXML
    private TextField note;
    @FXML
    private VBox reflectionVBox;
    @FXML
    private TextField reflection;

    /**
     * Creates a new popup add internship.
     */
    public PopupAddInternship(Stage root) {
        super(FXML, root);
    }
    /**
     * Creates a new popup add internship.
     */
    public PopupAddInternship(MainWindow mainWindow) {
        this(new Stage());
        this.mainWindow = mainWindow;
        mainWindow.setPopupAddInternship(this);
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
        logger.fine("Showing add internship popup");
        cleanNode();
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Clean the text field and child of javaFX node.
     */
    private void cleanNode() {
        companyName.setText("");
        jobTitle.setText("");
        place.setText("");
        salary.setText("");
        rating.setText("");

        qualification.setText("");
        int qVBoxSize = qualificationVBox.getChildren().size();
        if (qVBoxSize > 2) {
            qualificationVBox.getChildren().remove(2, qVBoxSize);
        }

        programmingLanguage.setText("");
        int plVBoxSize = programmingLanguageVBox.getChildren().size();
        if (plVBoxSize > 2) {
            programmingLanguageVBox.getChildren().remove(2, plVBoxSize);
        }

        review.setText("");
        int rVBoxSize = reviewVBox.getChildren().size();
        if (rVBoxSize > 2) {
            reviewVBox.getChildren().remove(2, rVBoxSize);
        }

        note.setText("");
        int nVBoxSize = noteVBox.getChildren().size();
        if (nVBoxSize > 2) {
            noteVBox.getChildren().remove(2, nVBoxSize);
        }

        reflection.setText("");
        int rfVBoxSize = reflectionVBox.getChildren().size();
        if (rfVBoxSize > 2) {
            reflectionVBox.getChildren().remove(2, rfVBoxSize);
        }
    }

    /**
     * Returns true if the popup window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the popup window.
     */
    public void hide() {
        getRoot().hide();
    }


    /**
     * Focuses on the popup window.
     */
    public void focus() {
        getRoot().requestFocus();
    }


    /**
     * Validate user input.
     */
    private boolean isValid() {
        if (companyName.getText().matches(ILLEGAL_REGEX)
            || jobTitle.getText().matches(ILLEGAL_REGEX)
            || place.getText().matches(ILLEGAL_REGEX)
            || salary.getText().matches(ILLEGAL_REGEX)
            || rating.getText().matches(ILLEGAL_REGEX)) {
            return false;
        }

        for (int i = 1; i < qualificationVBox.getChildren().size(); i += 1) {
            TextField q = (TextField) qualificationVBox.getChildren().get(i);
            if (q.getText().matches(ILLEGAL_REGEX)) {
                return false;
            }
        }

        for (int i = 1; i < programmingLanguageVBox.getChildren().size(); i += 1) {
            TextField pl = (TextField) programmingLanguageVBox.getChildren().get(i);
            if (pl.getText().matches(ILLEGAL_REGEX)) {
                return false;
            }
        }

        for (int i = 1; i < reviewVBox.getChildren().size(); i += 1) {
            TextField r = (TextField) reviewVBox.getChildren().get(i);
            if (r.getText().matches(ILLEGAL_REGEX)) {
                return false;
            }
        }

        for (int i = 1; i < noteVBox.getChildren().size(); i += 1) {
            TextField n = (TextField) noteVBox.getChildren().get(i);
            if (n.getText().matches(ILLEGAL_REGEX)) {
                return false;
            }
        }

        for (int i = 1; i < reflectionVBox.getChildren().size(); i += 1) {
            TextField rf = (TextField) reflectionVBox.getChildren().get(i);
            if (rf.getText().matches(ILLEGAL_REGEX)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Constructing command text.
     */
    private String makeCommandText() {
        String commandText = AddCommand.COMMAND_WORD
                 + " " + CliSyntax.PREFIX_COMPANY_NAME.getPrefix()
                 + companyName.getText()
                 + " " + CliSyntax.PREFIX_JOB_TITLE.getPrefix()
                 + jobTitle.getText();

        if (!place.getText().isBlank()) {
            commandText += " " + CliSyntax.PREFIX_LOCATION.getPrefix() + place.getText();
        }

        if (!salary.getText().isBlank()) {
            commandText += " " + CliSyntax.PREFIX_SALARY.getPrefix() + salary.getText();
        }

        if (!rating.getText().isBlank()) {
            commandText += " " + CliSyntax.PREFIX_RATING.getPrefix() + rating.getText();
        }

        for (int i = 1; i < qualificationVBox.getChildren().size(); i += 1) {
            TextField q = (TextField) qualificationVBox.getChildren().get(i);
            if (!q.getText().isBlank()) {
                commandText += " " + CliSyntax.PREFIX_QUALIFICATION.getPrefix() + q.getText();
            }
        }

        for (int i = 1; i < programmingLanguageVBox.getChildren().size(); i += 1) {
            TextField pl = (TextField) programmingLanguageVBox.getChildren().get(i);
            if (!pl.getText().isBlank()) {
                commandText += " " + CliSyntax.PREFIX_PROGRAMMING_LANGUAGE.getPrefix() + pl.getText();
            }
        }

        for (int i = 1; i < reviewVBox.getChildren().size(); i += 1) {
            TextField r = (TextField) reviewVBox.getChildren().get(i);
            if (!r.getText().isBlank()) {
                commandText += " " + CliSyntax.PREFIX_REVIEW.getPrefix() + r.getText();
            }
        }

        for (int i = 1; i < noteVBox.getChildren().size(); i += 1) {
            TextField n = (TextField) noteVBox.getChildren().get(i);
            if (!n.getText().isBlank()) {
                commandText += " " + CliSyntax.PREFIX_NOTE.getPrefix() + n.getText();
            }
        }

        for (int i = 1; i < reflectionVBox.getChildren().size(); i += 1) {
            TextField rf = (TextField) reflectionVBox.getChildren().get(i);
            if (!rf.getText().isBlank()) {
                commandText += " " + CliSyntax.PREFIX_REFLECTION.getPrefix() + rf.getText();
            }
        }
        return commandText;
    }

    /**
    * Handles the add internship button clicked event.
    */
    @FXML
    private void handleAddInternship() {
        if (!isValid()) {
            return;
        }

        String commandText = makeCommandText();

        try {
            mainWindow.executeCommand(commandText);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            hide();
        }
    }

    /**
     * Handles the delete qualification button clicked event.
     */
    @FXML
    private void handleDelQualification() {
        if (qualificationVBox.getChildren().size() > 2) {
            qualificationVBox.getChildren().remove(qualificationVBox.getChildren().size() - 1);
        }
    }

    /**
     * Handles the delete programming language button clicked event.
     */
    @FXML
    private void handleDelProgrammingLanguage() {
        if (programmingLanguageVBox.getChildren().size() > 2) {
            programmingLanguageVBox.getChildren().remove(programmingLanguageVBox.getChildren().size() - 1);
        }
    }

    /**
     * Handles the delete review button clicked event.
     */
    @FXML
    private void handleDelReview() {
        if (reviewVBox.getChildren().size() > 2) {
            reviewVBox.getChildren().remove(reviewVBox.getChildren().size() - 1);
        }
    }

    /**
     * Handles the delete note button clicked event.
     */
    @FXML
    private void handleDelNote() {
        if (noteVBox.getChildren().size() > 2) {
            noteVBox.getChildren().remove(noteVBox.getChildren().size() - 1);
        }
    }

    /**
     * Handles the delete reflection button clicked event.
     */
    @FXML
    private void handleDelReflection() {
        if (reflectionVBox.getChildren().size() > 2) {
            reflectionVBox.getChildren().remove(reflectionVBox.getChildren().size() - 1);
        }
    }

    /**
     * Handles the add qualification button clicked event.
     */
    @FXML
    private void handleAddQualification() {
        TextField moreQualification = new TextField();
        moreQualification.setPromptText("More qualification...");
        qualificationVBox.getChildren().add(moreQualification);
    }

    /**
     * Handles the add programming language button clicked event.
     */
    @FXML
    private void handleAddProgrammingLanguage() {
        TextField morePL = new TextField();
        morePL.setPromptText("More programming language...");
        programmingLanguageVBox.getChildren().add(morePL);
    }

    /**
     * Handles the add review button clicked event.
     */
    @FXML
    private void handleAddReview() {
        TextField moreReview = new TextField();
        moreReview.setPromptText("More review...");
        reviewVBox.getChildren().add(moreReview);
    }

    /**
     * Handles the add note button clicked event.
     */
    @FXML
    private void handleAddNote() {
        TextField moreNote = new TextField();
        moreNote.setPromptText("More note...");
        noteVBox.getChildren().add(moreNote);
    }

    /**
     * Handles the add reflection button clicked event.
     */
    @FXML
    private void handleAddReflection() {
        TextField moreReflection = new TextField();
        moreReflection.setPromptText("More reflection...");
        reflectionVBox.getChildren().add(moreReflection);
    }
}

package seedu.address.ui.popups;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.InternshipApplication;
import seedu.address.ui.MainWindow;
import seedu.address.ui.UiPart;

/**
 * Pop up for add internship
 */

public class PopupEditInternship extends UiPart<Stage> {
    private static MainWindow mainWindow;
    private static final Logger logger = LogsCenter.getLogger(PopupEditInternship.class);
    private static final String FXML = "PopupEditInternship.fxml";

    private static final String ILLEGAL_REGEX = ".*[a-z]/.*";
    private int index;
    private InternshipApplication internship;

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
    public PopupEditInternship(Stage root) {
        super(FXML, root);
    }
    /**
     * Creates a new popup add internship.
     */
    public PopupEditInternship(MainWindow mainWindow) {
        this(new Stage());
        this.mainWindow = mainWindow;
        mainWindow.setPopupEditInternships(this);
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
    public void show(int index, InternshipApplication internship) {
        logger.fine("Showing add internship popup");
        this.index = index;
        this.internship = internship;
        cleanNode();
        populateNode();
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
     * Populate the text field and child of javaFX node with initial internship values.
     */
    private void populateNode() {
        if (internship.getCompanyName().fullName != null) {
            companyName.setText(internship.getCompanyName().fullName);
        }
        if (internship.getJobTitle().fullName != null) {
            jobTitle.setText(internship.getJobTitle().fullName);
        }
        if (internship.getLocation().value != null) {
            place.setText(internship.getLocation().value);
        }
        if (internship.getSalary().value != null) {
            salary.setText(internship.getSalary().value);
        }
        if (internship.getRating().value != null) {
            rating.setText(internship.getRating().value);
        }

        if (!internship.getQualifications().isEmpty()) {
            qualificationVBox.getChildren().remove(1);
            internship.getQualifications().stream()
                    .forEach(q -> qualificationVBox.getChildren().add(new TextField(q.value)));
        }

        if (!internship.getProgrammingLanguages().isEmpty()) {
            programmingLanguageVBox.getChildren().remove(1);
            internship.getProgrammingLanguages().stream()
                    .forEach(plg -> programmingLanguageVBox.getChildren().add(new TextField(plg.value)));
        }

        if (!internship.getReviews().isEmpty()) {
            reviewVBox.getChildren().remove(1);
            internship.getReviews().stream()
                    .forEach(r -> reviewVBox.getChildren().add(new TextField(r.value)));
        }

        if (!internship.getNotes().isEmpty()) {
            noteVBox.getChildren().remove(1);
            internship.getNotes().stream()
                    .forEach(n -> noteVBox.getChildren().add(new TextField(n.value)));
        }

        if (!internship.getReflections().isEmpty()) {
            reflectionVBox.getChildren().remove(1);
            internship.getReflections().stream()
                    .forEach(rf -> reflectionVBox.getChildren().add(new TextField(rf.value)));
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
        String commandText = EditCommand.COMMAND_WORD + " " + index;

        if (!companyName.getText().isBlank()) {
            commandText += " " + CliSyntax.PREFIX_COMPANY_NAME.getPrefix() + companyName.getText();
        }

        if (!jobTitle.getText().isBlank()) {
            commandText += " " + CliSyntax.PREFIX_JOB_TITLE.getPrefix() + jobTitle.getText();
        }

        commandText += " " + CliSyntax.PREFIX_LOCATION.getPrefix() + place.getText();
        commandText += " " + CliSyntax.PREFIX_SALARY.getPrefix() + salary.getText();
        commandText += " " + CliSyntax.PREFIX_RATING.getPrefix() + rating.getText();


        int validQualification = 0;
        for (int i = 1; i < qualificationVBox.getChildren().size(); i += 1) {
            TextField q = (TextField) qualificationVBox.getChildren().get(i);
            if (!q.getText().isBlank()) {
                validQualification += 1;
                commandText += " " + CliSyntax.PREFIX_QUALIFICATION.getPrefix() + q.getText();
            }
        }

        if (validQualification == 0) {
            commandText += " " + CliSyntax.PREFIX_QUALIFICATION.getPrefix();
        } else {
            for (int i = 1; i < qualificationVBox.getChildren().size(); i += 1) {
                TextField q = (TextField) qualificationVBox.getChildren().get(i);
                if (!q.getText().isBlank()) {
                    commandText += " " + CliSyntax.PREFIX_QUALIFICATION.getPrefix() + q.getText();
                }
            }
        }

        int validProgrammingLanguage = 0;
        for (int i = 1; i < programmingLanguageVBox.getChildren().size(); i += 1) {
            TextField p = (TextField) programmingLanguageVBox.getChildren().get(i);
            if (!p.getText().isBlank()) {
                validProgrammingLanguage += 1;
                commandText += " " + CliSyntax.PREFIX_PROGRAMMING_LANGUAGE.getPrefix() + p.getText();
            }
        }

        if (validProgrammingLanguage == 0) {
            commandText += " " + CliSyntax.PREFIX_PROGRAMMING_LANGUAGE.getPrefix();
        } else {
            for (int i = 1; i < programmingLanguageVBox.getChildren().size(); i += 1) {
                TextField p = (TextField) programmingLanguageVBox.getChildren().get(i);
                if (!p.getText().isBlank()) {
                    commandText += " " + CliSyntax.PREFIX_PROGRAMMING_LANGUAGE.getPrefix() + p.getText();
                }
            }
        }

        int validReview = 0;
        for (int i = 1; i < reviewVBox.getChildren().size(); i += 1) {
            TextField r = (TextField) reviewVBox.getChildren().get(i);
            if (!r.getText().isBlank()) {
                validReview += 1;
                commandText += " " + CliSyntax.PREFIX_REVIEW.getPrefix() + r.getText();
            }
        }

        if (validReview == 0) {
            commandText += " " + CliSyntax.PREFIX_REVIEW.getPrefix();
        } else {
            for (int i = 1; i < reviewVBox.getChildren().size(); i += 1) {
                TextField r = (TextField) reviewVBox.getChildren().get(i);
                if (!r.getText().isBlank()) {
                    commandText += " " + CliSyntax.PREFIX_REVIEW.getPrefix() + r.getText();
                }
            }
        }

        int validNote = 0;
        for (int i = 1; i < noteVBox.getChildren().size(); i += 1) {
            TextField n = (TextField) noteVBox.getChildren().get(i);
            if (!n.getText().isBlank()) {
                validNote += 1;
                commandText += " " + CliSyntax.PREFIX_NOTE.getPrefix() + n.getText();
            }
        }

        if (validNote == 0) {
            commandText += " " + CliSyntax.PREFIX_NOTE.getPrefix();
        } else {
            for (int i = 1; i < noteVBox.getChildren().size(); i += 1) {
                TextField n = (TextField) noteVBox.getChildren().get(i);
                if (!n.getText().isBlank()) {
                    commandText += " " + CliSyntax.PREFIX_NOTE.getPrefix() + n.getText();
                }
            }
        }

        int validReflection = 0;
        for (int i = 1; i < reflectionVBox.getChildren().size(); i += 1) {
            TextField rf = (TextField) reflectionVBox.getChildren().get(i);
            if (!rf.getText().isBlank()) {
                validReflection += 1;
                commandText += " " + CliSyntax.PREFIX_REFLECTION.getPrefix() + rf.getText();
            }
        }

        if (validReflection == 0) {
            commandText += " " + CliSyntax.PREFIX_REFLECTION.getPrefix();
        } else {
            for (int i = 1; i < reflectionVBox.getChildren().size(); i += 1) {
                TextField rf = (TextField) reflectionVBox.getChildren().get(i);
                if (!rf.getText().isBlank()) {
                    commandText += " " + CliSyntax.PREFIX_REFLECTION.getPrefix() + rf.getText();
                }
            }
        }

        return commandText;
    }

    /**
     * Handles the add internship button clicked event.
     */
    @FXML
    private void handleEditInternship() {
        if (!isValid()) {
            return;
        }

        String commandText = makeCommandText();

        try {
            System.out.println(commandText);
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
}

package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.ArchiveCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.UnarchiveCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.InterviewDate;
import seedu.address.ui.popups.PopupEditInternship;

/**
 * A UI component that displays information of a {@code InternshipApplication}.
 */
public class ApplicationCard extends UiPart<Region> {
    private static final String FXML = "ApplicationListCard.fxml";
    public final InternshipApplication application;
    private PopupEditInternship popupEditInternship;
    private int index;
    private final MainWindow mainWindow;

    /**
     * NoteList: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    @FXML
    private HBox cardPane;
    @FXML
    private Label companyName;
    @FXML
    private Label jobTitle;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label internshipStatus;
    @FXML
    private Label interviewDate;
    @FXML
    private Button editButton;

    /**
     * Creates a {@code InternshipApplicationCode} with the given {@code InternshipApplication} and index to display.
     */
    public ApplicationCard(InternshipApplication application, int displayedIndex, MainWindow mainWindow) {
        super(FXML);
        this.application = application;
        this.mainWindow = mainWindow;
        this.index = displayedIndex;
        this.popupEditInternship = new PopupEditInternship(mainWindow);
        id.setText(displayedIndex + ". ");
        companyName.setText(application.getCompanyName().fullName);
        jobTitle.setText(application.getJobTitle().fullName);
        internshipStatus.setText(application.getStatus().name());
        InterviewDate interviewDateStr = application.getInterviewDate();
        if (interviewDateStr != null) {
            interviewDate.setText(interviewDateStr.toString());
            interviewDate.setVisible(true);
            interviewDate.setManaged(true);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicationCard)) {
            return false;
        }

        // state check
        ApplicationCard card = (ApplicationCard) other;
        return id.getText().equals(card.id.getText())
                && application.equals(card.application);
    }

    /**
     * Handles the edit internship button clicked event.
     */
    @FXML
    private void handleEditInternshipClicked() {
        if (!popupEditInternship.isShowing()) {
            popupEditInternship.show(index, application);
        } else {
            popupEditInternship.focus();
        }
    }

    /**
     * Handles the delete internship button clicked event.
     */
    @FXML
    private void handleDeleteInternshipClicked() {
        try {
            mainWindow.executeCommand(makeDeleteCommand());
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Make delete command.
     */
    private String makeDeleteCommand() {
        String commandText = DeleteCommand.COMMAND_WORD + " " + index;
        return commandText;
    }

    /**
     * Handles the archive internship button clicked event.
     */
    @FXML
    private void handleArchiveInternshipClicked() {
        try {
            mainWindow.executeCommand(makeArchiveCommand());
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Makes archive command.
     */
    private String makeArchiveCommand() {
        String commandText;
        if (application.isArchived()) {
            commandText = UnarchiveCommand.COMMAND_WORD + " " + index;
        } else {
            commandText = ArchiveCommand.COMMAND_WORD + " " + index;
        }
        return commandText;
    }
}

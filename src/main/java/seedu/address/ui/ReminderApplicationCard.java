package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.InterviewDate;
import seedu.address.model.contact.Contact;
import seedu.address.ui.popups.PopupEditInternship;

/**
 * A UI component that displays information of a {@code InternshipApplication} for ReminderWindow.
 */
public class ReminderApplicationCard extends UiPart<Region> {
    private static final String FXML = "ReminderApplicationCard.fxml";
    public final InternshipApplication application;
    private PopupEditInternship popupEditInternship;
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
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label internshipStatus;
    @FXML
    private Label interviewDate;


    /**
     * Creates a {@code ApplicationCard} with the given {@code InternshipApplication}.
     */
    public ReminderApplicationCard(InternshipApplication application, MainWindow mainWindow) {
        super(FXML);
        this.application = application;
        this.mainWindow = mainWindow;
        this.popupEditInternship = new PopupEditInternship(mainWindow);
        companyName.setText(application.getCompanyName().fullName);
        jobTitle.setText(application.getJobTitle().fullName);
        internshipStatus.setText(application.getStatus().name());
        Contact companyContact = application.getContact();
        if (companyContact != null) {
            email.setText(companyContact.getEmail().value);
            phone.setText(companyContact.getPhone().value);
            email.setVisible(true);
            phone.setVisible(true);
            email.setManaged(true);
            phone.setManaged(true);
        }
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
        return application.equals(card.application);
    }

}

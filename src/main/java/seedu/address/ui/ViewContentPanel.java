package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.contact.Contact;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.InterviewDate;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.Note;

/**
 * Panel containing the detailed content of selected internship application.
 */
public class ViewContentPanel extends UiPart<Region> {

    private static final String FXML = "ViewContentPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ViewContentPanel.class);

    @FXML
    private VBox container;
    @FXML
    private Pane contentContainer;

    @FXML
    private AnchorPane emptyAnchorPane;
    @FXML
    private Label emptyLabel;

    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label sideLabel;
    @FXML
    private ListView<Region> pane;

    /**
     * Creates a {@code ViewContentPanel}.
     */
    public ViewContentPanel() {
        super(FXML);
        emptyLabel.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(emptyLabel, 0.0);
        AnchorPane.setRightAnchor(emptyLabel, 0.0);
        AnchorPane.setTopAnchor(emptyLabel, 0.0);
        AnchorPane.setBottomAnchor(emptyLabel, 0.0);
        emptyLabel.setAlignment(Pos.CENTER);
    }

    public VBox getContainer() {
        return container;
    }

    private void addReviews(InternshipApplication internshipApplication) {
        if (internshipApplication.getReviews().size() > 0) {
            VBox reviewsContainer = new VBox();
            reviewsContainer.setSpacing(3);
            Label reviewLabel = new Label("Reviews:");
            reviewLabel.getStyleClass().add("cell_small_label");
            FlowPane reviews = new FlowPane();
            reviews.getStyleClass().add("reviews");
            reviews.getChildren().clear();
            internshipApplication.getReviews().stream()
                    .sorted(Comparator.comparing(review -> review.value))
                    .forEach(review -> reviews.getChildren().add(new Label(review.value)));
            reviewsContainer.getChildren().addAll(reviewLabel, reviews);
            pane.getItems().add(reviewsContainer);
        }
    }

    private void addContact(InternshipApplication internshipApplication) {
        Contact companyContact = internshipApplication.getContact();
        if (companyContact != null) {
            Label companyPhone = new Label("Company phone: " + companyContact.getPhone().value);
            companyPhone.getStyleClass().add("cell_small_label");
            Label companyEmail = new Label("Company email: " + companyContact.getEmail().value);
            companyEmail.getStyleClass().add("cell_small_label");
            pane.getItems().addAll(companyPhone, companyEmail);
        }
    }

    private void addInterviewDate(InternshipApplication internshipApplication) {
        InterviewDate interviewDateStr = internshipApplication.getInterviewDate();
        if (interviewDateStr != null) {
            Label interviewDate = new Label("Company phone: " + interviewDateStr);
            interviewDate.getStyleClass().add("cell_small_label");
            pane.getItems().add(interviewDate);
        }
    }

    private void displayEmptyPanel() {
        emptyAnchorPane.setVisible(true);
        emptyAnchorPane.setManaged(true);
        contentContainer.setVisible(false);
        contentContainer.setManaged(false);
    }

    private void showActualPanel() {
        emptyAnchorPane.setVisible(false);
        emptyAnchorPane.setManaged(false);
        contentContainer.setVisible(true);
        contentContainer.setManaged(true);
    }

    private void addInternshipTodoDetails(InternshipTodo internshipTodo) {
        Label deadline = new Label("Deadline: " + internshipTodo.getDeadline());
        deadline.getStyleClass().add("cell_small_label");

        Label note = new Label("Note: " + internshipTodo.getNote());
        note.getStyleClass().add("cell_small_label");

        Label createdOn = new Label("Created on: " + internshipTodo.getDateString());
        createdOn.getStyleClass().add("cell_small_label");

        pane.getItems().addAll(deadline, note, createdOn);
    }

    public void clearPanel() {
        displayEmptyPanel();
    }

    public void setInternshipApplication(InternshipApplication internshipApplication) {
        if (internshipApplication != null) {
            showActualPanel();
            title.setText(internshipApplication.getCompanyName().fullName);
            description.setText(internshipApplication.getJobTitle().fullName);
            sideLabel.setText(internshipApplication.getStatus().label);
            sideLabel.setStyle("-fx-background-color: " + internshipApplication.getStatus().labelColour);
            pane.getItems().clear();
            addContact(internshipApplication);
            addReviews(internshipApplication);
            addInterviewDate(internshipApplication);
        } else {
            displayEmptyPanel();
        }
    }

    public void setInternshipTodo(InternshipTodo internshipTodo) {
        if (internshipTodo != null) {
            showActualPanel();
            title.setText(internshipTodo.getInternshipTitle().fullName);
            description.setText(internshipTodo.getJobTitle().fullName);
            sideLabel.setText(internshipTodo.getType().name());
            sideLabel.setStyle("-fx-background-color: #53A3D8");
            pane.getItems().clear();
            addInternshipTodoDetails(internshipTodo);
        } else {
            displayEmptyPanel();
        }
    }

    public void setNote(Note note) {
        if (note != null) {
            showActualPanel();
            title.setText(note.getNote().content);
            description.setText(note.getDateString());
            sideLabel.setText(note.getType().name());
            sideLabel.setStyle("-fx-background-color: #588157");
            pane.getItems().clear();
        } else {
            displayEmptyPanel();
        }
    }
}

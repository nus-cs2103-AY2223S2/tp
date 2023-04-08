package seedu.sprint.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.sprint.model.application.Application;
import seedu.sprint.model.application.Status;

/**
 * A UI component that displays information of a {@code Application}.
 */
public class ApplicationCard extends UiPart<Region> {

    private static final String FXML = "ApplicationCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Application application;

    @FXML
    private HBox cardPane;
    @FXML
    private Label companyName;
    @FXML
    private Label id;
    @FXML
    private Label status;
    @FXML
    private Label role;
    @FXML
    private Label companyEmail;
    @FXML
    private VBox taskDetails;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ApplicationCard} with the given {@code Application} and index to display.
     */
    public ApplicationCard(Application application, int displayedIndex) {
        super(FXML);
        this.application = application;
        id.setText(displayedIndex + ". ");
        companyName.setText(application.getCompanyName().name);
        status.setText(application.getStatus().toString());
        setStatusColour();
        role.setText(application.getRole().roleApplied);
        companyEmail.setText(application.getCompanyEmail().value);
        taskDetails.setPrefHeight(Region.USE_PREF_SIZE);
        if (application.hasTask()) {
            Label taskHeader = new Label("Outstanding Task");
            taskHeader.setId("taskHeader");
            taskDetails.getChildren().add(taskHeader);
            initialiseTaskDetails(application);
        }
        application.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Initialises JavaFX nodes for displaying task description and deadline.
     * @param application Application to initialise task details for.
     */
    public void initialiseTaskDetails(Application application) {
        assert application.hasTask();
        HBox deadlineContainer = new HBox();
        HBox descriptionContainer = new HBox();

        Label descriptionLabel = new Label("Description: ");
        descriptionLabel.getStyleClass().add("task-label");

        Label deadlineLabel = new Label("Deadline: ");
        deadlineLabel.getStyleClass().add("task-label");

        Label description = new Label(application.getTask().getDescription().value);
        Label deadline = new Label(application.getTask().getDeadline().toDisplayString());
        descriptionContainer.getChildren().addAll(descriptionLabel, description);
        descriptionContainer.setAlignment(Pos.CENTER_LEFT);
        deadlineContainer.getChildren().addAll(deadlineLabel, deadline);
        deadlineContainer.setAlignment(Pos.CENTER_LEFT);
        taskDetails.getChildren().addAll(descriptionContainer, deadlineContainer);
    }

    /**
     * Configures background colour of status labels depending on its value.
     */
    public void setStatusColour() {
        if (status.getText().equals(Status.StatusType.INTERESTED.toString())) {
            status.setStyle("-fx-background-color:#91d7ff");
        } else if (status.getText().equals(Status.StatusType.APPLIED.toString())) {
            status.setStyle("-fx-background-color:#ffbd59");
        } else if (status.getText().equals(Status.StatusType.OFFERED.toString())) {
            status.setStyle("-fx-background-color:#5bb288;");
        } else {
            status.setStyle("-fx-background-color:#ff8787");
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
}

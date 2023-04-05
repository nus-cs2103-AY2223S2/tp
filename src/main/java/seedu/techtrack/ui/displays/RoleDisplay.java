package seedu.techtrack.ui.displays;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Comparator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.techtrack.model.role.Role;

/**
 * A customizable display for Roles. Will be displayed in ResultDisplay.
 */
public final class RoleDisplay {

    private static Image detailsIcon = new Image(RoleDisplay.class.getResourceAsStream("/images/more-details-96.png"));
    private static Image contactsIcon = new Image(RoleDisplay.class.getResourceAsStream("/images/contact-us-96.png"));
    private RoleDisplay() {}

    /**
     * Creates a custom RoleDisplay based on the provided Role.
     * @param roleToDisplay The role to be displayed in the UI.
     * @return A VBox Node to be displayed in the UI.
     */
    public static Node of(Role roleToDisplay) {
        requireNonNull(roleToDisplay);
        // Header
        Label role = new Label(roleToDisplay.getName().fullName + " @ ");
        role.getStyleClass().add("role-display-headers");

        Label company = new Label(roleToDisplay.getCompany().value);
        company.getStyleClass().add("role-display-headers");

        HBox title = new HBox(role, company);
        title.setAlignment(Pos.CENTER);

        // First Card
        ImageView detailsView = new ImageView(detailsIcon);
        detailsView.setFitWidth(32);
        detailsView.setFitHeight(32);
        Label detailsHeader = new Label("Details:");
        detailsHeader.getStyleClass().add("role-display-headers");
        detailsHeader.setAlignment(Pos.CENTER);
        HBox detailCardHeader = new HBox(10, detailsView, detailsHeader);

        FlowPane tags = new FlowPane();
        roleToDisplay.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        tags.getStyleClass().add("role-display-tags");

        Label salary = new Label("Salary: $" + roleToDisplay.getSalary().salary);
        salary.getStyleClass().add("role-display-body");

        Label deadlineDesc = new Label("Deadline: ");
        deadlineDesc.getStyleClass().add("role-display-body");
        Label deadline = new Label();
        LocalDate currDeadline = LocalDate.parse(roleToDisplay.getDeadline().deadline);
        if (currDeadline.isBefore(LocalDate.now())) {
            deadline.setText(" EXPIRED ");
            deadline.getStyleClass().add("role-display-body-highlight");
        } else {
            deadline.setText(roleToDisplay.getDeadline().deadline);
            deadline.getStyleClass().add("role-display-body");
        }
        HBox deadlineContainer = new HBox(deadlineDesc, deadline);

        Label experience = new Label("Experience: " + roleToDisplay.getExperience().value);
        experience.getStyleClass().add("role-display-body");

        Label jobDesc = new Label("Job Description: \n" + roleToDisplay.getJobDescription().value);
        jobDesc.getStyleClass().add("role-display-body");

        // Second card
        ImageView contactView = new ImageView(contactsIcon);
        contactView.setFitWidth(32);
        contactView.setFitHeight(32);
        Label contactHeader = new Label("Contacts:");
        contactHeader.setAlignment(Pos.CENTER);
        contactHeader.getStyleClass().add("role-display-headers");
        HBox contactCardHeader = new HBox(10, contactView, contactHeader);

        Label email = new Label("Email: " + roleToDisplay.getEmail());
        email.getStyleClass().add("role-display-body");

        Label phone = new Label("Contact No.: " + roleToDisplay.getContact());
        phone.getStyleClass().add("role-display-body");

        Label website = new Label("Website: " + roleToDisplay.getWebsite());
        website.getStyleClass().add("role-display-body");

        VBox detailsContainer = new VBox(detailCardHeader, salary, deadlineContainer, experience, jobDesc);
        detailsContainer.setPadding(new Insets(5, 5, 5, 10));
        detailsContainer.setSpacing(10);
        detailsContainer.getStyleClass().add("role-display-card");

        VBox contactsContainer = new VBox(contactCardHeader, email, phone, website);
        contactsContainer.setPadding(new Insets(5, 5, 5, 10));
        contactsContainer.setSpacing(10);
        contactsContainer.getStyleClass().add("role-display-card");

        VBox allContainer = new VBox(title, new VBox(detailsContainer, contactsContainer));
        allContainer.setSpacing(10);

        return allContainer;
    }
}


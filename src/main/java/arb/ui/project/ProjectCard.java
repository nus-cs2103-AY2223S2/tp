package arb.ui.project;

import java.util.Comparator;

import arb.model.project.Project;
import arb.ui.UiPart;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * An UI component that displays information of a {@code Project}.
 */
public class ProjectCard extends UiPart<Region> {

    private static final String FXML = "project/ProjectListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Project project;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox contentsPane;
    @FXML
    private Label title;
    @FXML
    private Label deadline;
    @FXML
    private Label status;
    @FXML
    private Label price;
    @FXML
    private Label forClient;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ProjectCard} with the given {@code Project} and index to display.
     */
    public ProjectCard(Project project, int displayedIndex) {
        super(FXML);
        this.project = project;
        id.setText(displayedIndex + ". ");
        title.setText(project.getTitle().fullTitle);

        if (project.isDeadlinePresent()) {
            deadline.setText(project.getDeadline().toString());
        } else {
            contentsPane.getChildren().remove(deadline);
        }
        if (project.isPricePresent()) {
            price.setText(project.getPrice().toString());
        } else {
            contentsPane.getChildren().remove(price);
        }
        if (project.isClientPresent()) {
            forClient.setText("For Client: " + project.getClientName());
        } else {
            contentsPane.getChildren().remove(forClient);
        }

        status.setText("Status: " + (project.isOverdue() ? "OVERDUE" : project.getStatus().toString()));

        project.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProjectCard)) {
            return false;
        }

        // state check
        ProjectCard card = (ProjectCard) other;
        return id.getText().equals(card.id.getText())
                && project.equals(card.project);
    }
}

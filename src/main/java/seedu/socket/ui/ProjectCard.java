package seedu.socket.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.socket.model.project.Project;

import java.util.Comparator;

public class ProjectCard extends UiPart<Region> {
    private static final String FXML = "ProjectListCard.fxml";
    private final Project project;

    @FXML
    private HBox projectCardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label repoName;
    @FXML
    private Label deadline;
    @FXML
    private FlowPane members;

    /**
     * Creates a {@code ProjectCode} with the given {@code Project} and index to display.
     */
    public ProjectCard(Project project, int displayedIndex) {
        super(FXML);
        this.project = project;
        id.setText(displayedIndex + ". ");
        name.setText(project.getName().projectName);
        if (!project.getRepoName().value.isEmpty()) {
            repoName.setText(project.getRepoName().value);
        } else {
            repoName.setText("Not available");
        }
        deadline.setText(project.getDeadline().deadline);
        project.getMembers().stream()
                .sorted(Comparator.comparing(member -> member.getName().fullName))
                .forEach(member -> members.getChildren().add(new Label(member.getName().fullName)));
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

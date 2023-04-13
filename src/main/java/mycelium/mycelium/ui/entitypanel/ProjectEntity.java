package mycelium.mycelium.ui.entitypanel;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.commons.util.DateUtil;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.util.NonEmptyString;
import mycelium.mycelium.ui.UiPart;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class ProjectEntity extends UiPart<Region> {
    private static final String FXML = "ProjectEntity.fxml";
    public final Project project;

    private Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label status;
    @FXML
    private Label email;
    @FXML
    private Label source;
    @FXML
    private Label description;
    @FXML
    private Label acceptedOn;
    @FXML
    private Label deadline;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ProjectEntity(Project p, int displayedIndex) {
        super(FXML);
        project = p;
        id.setText(displayedIndex + ". ");
        name.setText(p.getName().toString());
        status.setText(p.getStatus().toString());
        email.setText(p.getClientEmail().toString());
        source.setText(p.getSource().map(NonEmptyString::toString).orElse("Unknown"));
        description.setText(p.getDescription().orElse("No description given"));
        acceptedOn.setText(p.getAcceptedOn().format(DateUtil.DATE_FMT));
        deadline.setText(p.getDeadline().map(d -> d.format(DateUtil.DATE_FMT)).orElse("No Deadline"));
        logger.fine("Initialized ProjectEntity with project: " + p.getName());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProjectEntity)) {
            return false;
        }

        // state check
        ProjectEntity card = (ProjectEntity) other;
        return id.getText().equals(card.id.getText())
            && project.equals(card.project);
    }
}

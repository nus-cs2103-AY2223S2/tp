package mycelium.mycelium.ui.statisticsbox;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.ui.UiPart;

/**
 * An UI component that displays information of a {@code Due Project} or {@code Overdue Project}.
 */
public class SpecialProjectEntity extends UiPart<Region> {
    private static final String FXML = "SpecialProjectEntity.fxml";
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
    private Label deadline;

    /**
     * Creates a {@code DueProjectCode} with the given {@code Project} and index to display.
     */
    public SpecialProjectEntity(Project p, int displayedIndex) {
        super(FXML);
        project = p;
        id.setText(displayedIndex + ". ");
        name.setText(p.getName().getValue());
        status.setText(p.getStatus().toString());
        email.setText(p.getClientEmail().toString());
        deadline.setText(p.getDeadline().map(d -> d.format(Project.DATE_FMT)).orElse("No Deadline"));
        logger.fine("Initialized SpecialProjectEntity with project: " + p.getName());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SpecialProjectEntity)) {
            return false;
        }

        // state check
        SpecialProjectEntity card = (SpecialProjectEntity) other;
        return id.getText().equals(card.id.getText())
                && project.equals(card.project);
    }
}

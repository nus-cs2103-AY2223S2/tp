package seedu.techtrack.ui;

import java.time.LocalDate;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.techtrack.model.role.Role;


/**
 * An UI component that displays information of a {@code Role}.
 */
public class RoleCard extends UiPart<Region> {

    private static final String FXML = "RoleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Role role;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label company;
    @FXML
    private FlowPane tags;
    @FXML
    private Label salary;
    @FXML
    private Label deadline;
    @FXML
    private Label experience;

    /**
     * Creates a {@code RoleCode} with the given {@code Role} and index to display.
     */
    public RoleCard(Role role, int displayedIndex) {
        super(FXML);
        this.role = role;
        id.setText(displayedIndex + ". ");
        name.setText(role.getName().fullName);
        company.setText(role.getCompany().value);
        role.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        salary.setText("$" + role.getSalary().salary);
        LocalDate currDeadline = LocalDate.parse(role.getDeadline().deadline);
        if (currDeadline.isBefore(LocalDate.now())) {
            deadline.setText(" EXPIRED ");
            deadline.setStyle("-fx-background-color: yellow; -fx-background-radius: 5px; -fx-text-fill: black");
        } else {
            deadline.setText(role.getDeadline().deadline);
        }
        experience.setText(role.getExperience().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RoleCard)) {
            return false;
        }

        // state check
        RoleCard card = (RoleCard) other;
        return id.getText().equals(card.id.getText())
                && role.equals(card.role);
    }
}

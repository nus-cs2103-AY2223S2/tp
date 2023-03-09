package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.internship.Internship;

/**
 * An UI component that displays information of a {@code Internship}.
 */
public class InternshipCard extends UiPart<Region> {

    private static final String FXML = "InternshipListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Internship internship;

    @FXML
    private HBox cardPane;
    @FXML
    private Label companyName;
    @FXML
    private Label id;
    @FXML
    private Label role;
    @FXML
    private Label status;
    @FXML
    private Label date;
    @FXML
    private FlowPane tags;

    private String dateLabel;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public InternshipCard(Internship internship, int displayedIndex) {
        super(FXML);
        this.internship = internship;
        id.setText(displayedIndex + ". ");
        companyName.setText(internship.getCompanyName().fullCompanyName);
        role.setText("Role: " + internship.getRole().fullRole);
        status.setText("Status: " + internship.getStatus().toString());
        String internshipStatus = internship.getStatus().toString();
        switch (internshipStatus) {
        case "New":
            dateLabel = "Date Added: ";
            break;
        case "Applied":
            dateLabel = "Date Applied: ";
            break;
        case "Assessment":
            dateLabel = "Date of Assessment: ";
            break;
        case "Interview":
            dateLabel = "Date of Interview: ";
            break;
        case "Offered":
            dateLabel = "Date of Notice of Offer: ";
            break;
        case "Rejected":
            dateLabel = "Date of Notice of Rejection: ";
            break;
        default:
            dateLabel = "Date: ";
        }
        date.setText(dateLabel + internship.getDate().fullDate);
        internship.getTags().stream()
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
        if (!(other instanceof InternshipCard)) {
            return false;
        }

        // state check
        InternshipCard card = (InternshipCard) other;
        return id.getText().equals(card.id.getText())
                && internship.equals(card.internship);
    }

    /**
     * Returns the date label shown in the UI.
     * @return the data label in the UI.
     */
    public String getDateLabel() {
        return this.dateLabel;
    }
}

package seedu.internship.ui;

import static seedu.internship.model.internship.Status.ACCEPTED;
import static seedu.internship.model.internship.Status.APPLIED;
import static seedu.internship.model.internship.Status.ASSESSMENT;
import static seedu.internship.model.internship.Status.INTERVIEW;
import static seedu.internship.model.internship.Status.NEW;
import static seedu.internship.model.internship.Status.OFFERED;
import static seedu.internship.model.internship.Status.REJECTED;

import java.util.Comparator;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.internship.model.internship.Internship;


/**
 * A UI component that displays information of a {@code Internship}.
 */
public class InternshipCard extends UiPart<Region> {
    public static final String ROLE_LABEL = "Role: ";
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
    private Label date;
    @FXML
    private FlowPane tags;
    @FXML
    private Label statusLabel;


    /**
     * Creates a {@code InternshipCode} with the given {@code Internship} and index to display.
     */
    public InternshipCard(Internship internship, int displayedIndex) {
        super(FXML);
        this.internship = internship;
        //Add Id
        id.setText(displayedIndex + ". ");

        //Add Company Name
        companyName.setText(internship.getCompanyName().fullCompanyName);

        //Add Role
        role.setText(ROLE_LABEL + internship.getRole().fullRole);

        //Add Date
        String dateLabel = getDateLabel(internship.getStatus().toString());
        date.setText(dateLabel + internship.getDate().fullDate);

        //Add Tags
        internship.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));


        //Set up status label
        String statusString = internship.getStatus().toString();
        HashMap<String, Color> colorMap = setupColours();
        Color statusColor = colorMap.get(statusString);
        statusLabel.setText(statusString.toUpperCase());
        statusLabel.setBackground(new Background(new BackgroundFill(
                statusColor, new CornerRadii(10), new Insets(-5))));
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
     * Initialises the colours associated with the status label.
     *
     * @return a hashmap containing the colors associated with each status type
     */
    public static HashMap<String, Color> setupColours() {
        //Hashmap that stores the colours associated with each status
        HashMap<String, Color> colorMap = new HashMap<String, Color>();
        colorMap.put(ACCEPTED, Color.rgb(42, 174, 79, 1.0));
        colorMap.put(REJECTED, Color.rgb(250, 68, 68, 1.0));
        colorMap.put(OFFERED, Color.rgb(42, 174, 166, 1.0));
        colorMap.put(INTERVIEW, Color.rgb(126, 68, 250, 1.0));
        colorMap.put(ASSESSMENT, Color.rgb(250, 68, 155, 1.0));
        colorMap.put(APPLIED, Color.rgb(68, 170, 250, 1.0));
        colorMap.put(NEW, Color.rgb(250, 155, 68, 1.0));
        return colorMap;
    }


    /**
     * Returns the label for the date field in Internship Card.
     *
     * @param statusString The current status of the associated Internship.
     *
     * @return the corresponding String as a label for the date.
     */
    public static String getDateLabel(String statusString) {
        String dateLabel;
        switch (statusString) {
        case ACCEPTED:
            dateLabel = "Date of Acceptance: ";
            break;
        case REJECTED:
            dateLabel = "Date of Rejection: ";
            break;
        case OFFERED:
            dateLabel = "Deadline of Offer Acceptance: ";
            break;
        case INTERVIEW:
            dateLabel = "Date of Interview: ";
            break;
        case APPLIED:
            dateLabel = "Date Applied: ";
            break;
        case ASSESSMENT:
            dateLabel = "Date of Assessment: ";
            break;

        default:
            dateLabel = "Deadline of Application: ";
        }
        return dateLabel;
    }
}
